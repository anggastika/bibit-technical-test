package bukalapak.pageObject.vp.insurance;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.utils.APICall;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NoSuchElementException;
import java.util.ArrayList;

import static org.junit.Assert.fail;

/**
 * @Author: Ayu Musfita
 * @Date: 15/07/20, Wed
 **/
public class LeadGeneratorLandingPage extends VpBasePage {

    private APICall apiCall = new APICall();

    public LeadGeneratorLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage(String product) {
        changeContext().toWebview();
        verifyElementExist(constructLocator("LEAD_GENERATOR_LANDING_PRODUCT_TITLE_TEXT", product));
        verifyElementExist("LEAD_GENERATOR_LANDING_CONSULTATION_STEP_TEXT");
        verifyElementExist("LEAD_GENERATOR_LANDING_HEADER_TEXT");
        verifyElementExist("LEAD_GENERATOR_LANDING_SEE_DETAIL_BUTTON");
        verifyElementExist("LEAD_GENERATOR_LANDING_CLAIM_LIMIT_TEXT");
        verifyElementExist("LEAD_GENERATOR_LANDING_INSURED_TIME_TEXT");
        verifyElementExist("LEAD_GENERATOR_LANDING_PREMI_TEXT");
        verifyElementExist("LEAD_GENERATOR_LANDING_REGISTER_BUTTON");
        HelperData.setLastActionPage(new LeadGeneratorLandingPage(iosDriver));
    }

    public void tapOnDetailButton() {
        tapElement("LEAD_GENERATOR_LANDING_SEE_DETAIL_BUTTON");
    }

    private ArrayList<String> retrieveFormFields(String product) {
        String envProductID = TestInstrument.dotenv.get("LEAD_GENERATOR_PRODUCT_ID_" + product.toUpperCase()
                .replace(" ", "_"));
        int productID;

        if (envProductID == null || envProductID.isEmpty())
            productID = 1;
        else
            productID = Integer.parseInt(envProductID);

        return apiCall.retrieveLeadGeneratorForm("INSURANCE", productID);
    }

    public void fillContactData(String product) {
        webViewSwipeToElement("LEAD_GENERATOR_LANDING_SNK_CHECKBOX");
        ArrayList<String> fields = retrieveFormFields(product);

        for (String field : fields) {
            String fieldItem = field.toLowerCase();
            if (fieldItem.contains("nama")) {
               inputContactName();
            } else if (fieldItem.contains("handphone")) {
               inputContactPhone();
            } else if (fieldItem.contains("email")) {
               inputContactEmail();
            } else if (fieldItem.contains("detail")) {
                inputDetail();
            }
            else {
                fail(String.format("%s field not implemented yet", fieldItem));
            }
            // Delay animation
            delay(2000);
        }
    }

    private void inputContactName() {
        try {
            typeValue(constructLocator("LEAD_GENERATOR_LANDING_NAME_FIELD"),
                    generateFullName());
        } catch (NoSuchElementException e) {
            LogUtil.error(e.getMessage());
        }
    }

    private void inputContactEmail() {
        try {
            typeValue(constructLocator("LEAD_GENERATOR_LANDING_EMAIL_FIELD"),
                    generateFullName().toLowerCase().replace(" ", ".") + "@gmail.com");
        } catch (NoSuchElementException e) {
            LogUtil.error(e.getMessage());
        }
    }

    private void inputContactPhone() {
        try {
            typeValue(constructLocator("LEAD_GENERATOR_LANDING_PHONE_FIELD"),
                    "08" + generatePhoneNumber());
        } catch (NoSuchElementException e) {
            LogUtil.error(e.getMessage());
        }
    }

    private void inputDetail() {
        try {
            typeValue(constructLocator("LEAD_GENERATOR_LANDING_DETAIL_FIELD"),
                    "Ini testing Bukalapak, mohon diabaikan");
        } catch (NoSuchElementException e) {
            LogUtil.error(e.getMessage());
        }
    }

    public void tickOnSNKCheckbox() {
        tapElement("LEAD_GENERATOR_LANDING_SNK_CHECKBOX");
    }

    public void tapOnRegisterButton() {
        tapElement("LEAD_GENERATOR_LANDING_REGISTER_BUTTON");
    }

    public void tapOnHistoryButton() {
        tapElement("LEAD_GENERATOR_LANDING_HISTORY_BUTTON");
    }

    public void validateOnHistoryPage() {
        verifyElementExist("LEAD_GENERATOR_LANDING_CONSULTATION_STEP_TEXT");
    }

    public void validateErrorMessageRequiredInput(String field) {
        try {
            assertTextContains("Wajib diisi",getTextFromElement(constructLocator("LEAD_GENERATOR_REGISTRATION_PAGE_ERROR_MESSAGE_TEXT", field)));
        } catch (java.util.NoSuchElementException e) {
            fail("Please check the field " +field+ " , make sure the format is correct (lowercase/uppercase)");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
