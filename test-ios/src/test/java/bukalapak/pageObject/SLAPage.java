package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.STRIPEData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.Random;
import org.openqa.selenium.TimeoutException;

import static bukalapak.TestInstrument.dotenv;

public class SLAPage extends BasePage {

    public SLAPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSLAPage() {
        verifyElementExist("sla_page_title");
        HelperData.setLastActionPage(new SLAPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnSLAPageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnSLAPage();
    }

    public void setSLA(String slaType) {
        int rand;

        switch (slaType) {
            case "custom":
                rand = new Random().nextInt(4);
                STRIPEData.setSLADuration(rand + 3);
                choosePreorderSLA(STRIPEData.getSLADuration());
                LogUtil.info(String.format("Custom SLA Duration : %d", rand));
                break;
            case "instant":
                STRIPEData.setSLADuration(6);
                chooseInstantSLA(STRIPEData.getSLADuration());
                break;
            case "preorder":
                rand = new Random().nextInt(24);
                STRIPEData.setSLADuration(rand + 7);
                choosePreorderSLA(STRIPEData.getSLADuration());
                LogUtil.info(String.format("Preorder SLA Duration : %d", rand));
                break;
            case "reguler":
                chooseRegularSLA();
                break;
            default:
                break;
        }
        STRIPEData.setSLAType(slaType);
    }

    public void choosePreorderSLA(int days) {
        if (!isElementVisible("sla_page_preorder_button")) {
            swipeUpToElement("sla_page_preorder_button");
        }
        tapElement("sla_page_preorder_button");

        // handle flaky when the input field hidden under the navigation bar
        try {
            typeAndEnterValueWithTimeOut("sla_page_value_text_field", String.valueOf(days));
        } catch (TimeoutException e) {
            swipeDownToElement("sla_page_value_text_field");
            typeAndEnterValueWithTimeOut("sla_page_value_text_field", String.valueOf(days));
        }
    }

    public void chooseInstantSLA(int hours) {
        tapElement("sla_page_instant_button");
        typeAndEnterValueWithTimeOut("sla_page_value_text_field", String.valueOf(hours));
    }

    public void chooseRegularSLA() {
        if (!isElementVisible("sla_page_reguler_button")) {
            swipeUpToElement("sla_page_reguler_button");
        }
        tapElement("sla_page_reguler_button");
    }

    public void saveSLA() {
        waitForElementClickable("sla_page_save_button", 10);
        tapElement("sla_page_save_button");
    }

    public void typeOnWaktuProsesPesananText(int input) {
        swipeToLocator("waktu_proses_pesanan_text");
        if (isElementVisible("sla_page_value_text_field")) {
            typeAndEnterValueWithTimeOut("sla_page_value_text_field", input + "");
        } else {
            typeAndEnterValueWithTimeOut("waktu_proses_pesanan_text_field_jumlah", input + "");
        }
        STRIPEData.setSLADuration(input);
        saveSLA();
    }

    public void saveChanged() {
        waitFor(10);
        tapElement("back_icon");
    }

    public void clickBackButton() {
        waitForVisibilityOf("menulain_back_button", 10);
        tapElement("menulain_back_button");
    }

    public void verifySaveSLASuccess() {
        verifyElementNotExist("sla_page_instant_button");
        verifyElementNotExist("sla_page_reguler_button");
        verifyElementNotExist("sla_page_preorder_button");
    }
}
