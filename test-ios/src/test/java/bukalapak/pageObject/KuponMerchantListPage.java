package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.KuponData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static org.junit.Assert.fail;

/**
 * @Author: Ayu Musfita
 * @Date: 28/05/20, Thu
 **/
public class KuponMerchantListPage extends VpBasePage {

    public KuponMerchantListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("KUPON_MERCHANT_SEARCH_BAR");
        verifyElementExist("KUPON_MERCHANT_LIST_NAME");
    }

    public void validateAllMerchant() {
        verifyElementExist("KUPON_MERCHANT_LIST_NAME");
        HelperData.setLastActionPage(new KuponMerchantListPage(iosDriver));
    }
    public void typeOnSearchMerchant(String state) {
        if (state.equals("valid")) {
            typeOnSearchValidMerchant(TestInstrument.dotenv.get("MERCHANT_NAME"));
        } else if (state.equals("invalid")) {
            typeOnSearchInvalidMerchant("Invalid Merchant");
        } else {
            fail(String.format("%s state not implemented yet", state));
        }
        HelperData.setLastActionPage(new KuponMerchantListPage(iosDriver));
    }

    public void typeOnSearchValidMerchant(String merchant) {
        typeAndEnterValue("KUPON_MERCHANT_SEARCH_BAR", merchant);
    }

    public void typeOnSearchInvalidMerchant(String invalidMerchant) {
        typeAndEnterValue("KUPON_MERCHANT_SEARCH_BAR", invalidMerchant);
    }

    public void validateSearchedMerchant(String flag) {
        if (flag == null) {
            validateMerchantDisplayed(TestInstrument.dotenv.get("MERCHANT_NAME"));
        } else {
            validateMerchantNotFoundDisplayed();
        }
        HelperData.setLastActionPage(new KuponMerchantListPage(iosDriver));
    }

    public void validateMerchantDisplayed(String merchant) {
        validateElementContainsText("KUPON_MERCHANT_LIST_NAME", merchant);
        HelperData.setLastActionPage(new KuponMerchantListPage(iosDriver));
    }

    public void validateMerchantNotFoundDisplayed() {
        validateElementWithText("KUPON_GENERAL_TEXT", KuponData.COUPON_MERCHANT_TITLE_NOT_FOUND);
        validateElementWithText("KUPON_GENERAL_TEXT", KuponData.COUPON_MERCHANT_CAPTION_NOT_FOUND);
        HelperData.setLastActionPage(new KuponMerchantListPage(iosDriver));
    }

    public void setMerchantName() {
        KuponData.setMerchant(getText("KUPON_MERCHANT_LIST_NAME", 0));
    }

    public void tapOnMerchant() {
        tapElements("KUPON_MERCHANT_LIST_NAME", 0);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
