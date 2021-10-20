package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.APIData;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import org.openqa.selenium.WebDriverException;

public class MultifinancePage extends PostpaidBasePage {

    public MultifinancePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        try {
            openDeepLink("/angsuran-kredit");
        } catch (WebDriverException ex) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public void isBillerScreenDisplayed() {
        assertFalse(isElementVisible("postpaid_multifinance_title_text"), "Still on landing page, biller screen is not opened yet!");
        assertTrue(isElementVisible("postpaid_multifinance_biller_search_field"), "Biller search field is not existed!");
    }

    public void isDisplayed() {
        skipOnboarding("postpaid_multifinance_onboarding_button_next", "postpaid_multifinance_onboarding_button_done");
        isPageDisplayed("postpaid_multifinance_title_text");
        skipScenarioIfToggledOff("postpaid_multifinance_biller_field");
        HelperData.setLastActionPage(new MultifinancePage(iosDriver));
    }

    public void pickBiller(String biller) {
        tapElement("postpaid_multifinance_biller_field");

        isBillerScreenDisplayed();
        typeAndEnterValueWithTimeOut("postpaid_multifinance_biller_search_field", biller);
        tapElement("postpaid_multifinance_biller_option");
        PostpaidData.setBiller(biller);

        isDisplayed();
    }

    public void tapOnBayarButton() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getPostpaidNumber() + " sudah dibayar. Saatnya mencari nomor Multifinance testing yang lain";

        if (isElementExist("postpaid_multifinance_paid_number", 5)) {
            APIData.setLogMessage(info);
            APIData.setLogUserID(TestInstrument.dotenv.get("WEBHOOK_USER"));
        } else {
            swipeUpToElement("postpaid_multifinance_pay_button");
            tapElement("postpaid_multifinance_pay_button");
        }
    }

    public void tapOnPromoTerbaru() {
        tapElement("postpaid_multifinance_promo_terbaru");
    }

    public void tapOnClosePromoTerbaru() {
        tapElement("postpaid_multifinance_promo_terbaru_close");
    }

    public void tapSalin() {
        PostpaidData.setVoucherCode(getElementValue("postpaid_multifinance_voucher_code"));
        tapElement("postpaid_multifinance_salin");
    }

    public void verifyPromoCodeCondition() {
        if (isElementVisible("postpaid_multifinance_paid_number")) {
            TransactionData.setPaymentMethod("");
        } else {
            verifyElementNotExist("postpaid_multifinance_promo_terbaru");
        }

        HelperData.setLastActionPage(new MultifinancePage(iosDriver));
    }

    public void typeCustomerNumber(String customerNumber) {
        String number;

        if (customerNumber.equals("valid")) {
            number = generatePostpaidNumber("MULTIFINANCE_NUMBER_LIST");
        } else {
            number = fakerPhoneNumber("0812", 8);
        }

        PostpaidData.setPostpaidNumber(number);
        typeAndEnterValueWithTimeOut("postpaid_multifinance_customer_number_field", number);
    }

    public void verifyCustomerNumberField() {
        String info = "Yaaaahhh tagihan " + PostpaidData.getPostpaidNumber() + " sudah dibayar. Saatnya mencari nomor Multifinance testing yang lain";
        String element = constructLocator("postpaid_multifinance_customer_number_prefilled", PostpaidData.getPostpaidNumber());

        if (!isElementVisible(element)) {
            APIData.setLogMessage(info);
        } else {
            verifyElementDisplayed(element);
        }
        HelperData.setLastActionPage(new MultifinancePage(iosDriver));
    }

    public void verifyMultifinanceNumberHasBeenPaid() {
        verifyElementDisplayed("postpaid_multifinance_paid_number");
        TransactionData.setPaymentMethod("");
        HelperData.setLastActionPage(new MultifinancePage(iosDriver));
    }

    public void verifyMultifinanceErrorIsDisplayed(String errorMessage) {
        verifyElementDisplayed(constructLocator("postpaid_multifinance_invalid_number_error_message", errorMessage));
        HelperData.setLastActionPage(new MultifinancePage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
