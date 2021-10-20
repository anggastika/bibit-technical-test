package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PrepaidData;
import bukalapak.data.TransactionData;
import bukalapak.utils.UserHelper;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PrepaidPhoneCreditPage extends BasePage {

    public PrepaidPhoneCreditPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void isOnPrepaidPhoneCreditPage() {
        onBoardingPackage();
        assertTrue(isElementVisible("prepaid_phone_credit_landing_page_title_text"));
        HelperData.setLastActionPage(new PrepaidPhoneCreditPage(iosDriver));
    }

    public void enterPhoneNumber() {
        PrepaidData.setPhoneNumber(UserHelper.generatePhoneNumber());

        typeAndEnterValueWithTimeOut("prepaid_phone_credit_landing_page_phone_field", PrepaidData.getPhoneNumber());
//        hideKeyboard not working, the element is not defined
//        hideKeyboard();
//        waitForVisibilityOf("prepaid_phone_credit_landing_page_operator_logo_image");
    }

    public void choosePackage() {
        tapElement("prepaid_phone_credit_landing_page_product_list_button");

        TransactionData.setTotalPrice(getElementValue("prepaid_phone_credit_landing_page_first_product_price_text"));
        PrepaidData.setProductName(getElementValue("prepaid_phone_credit_landing_page_first_product_package_text"));
        tapElement("prepaid_phone_credit_landing_page_first_product_button");

        waitForVisibilityOf("prepaid_phone_credit_landing_page_pay_button");
        assertTrue(TransactionData.getTotalPrice().equals(getElementValue("prepaid_phone_credit_landing_page_total_price_text")));
    }

    public void chooseFirstPackage() {
        onBoardingPackage();
        waitForVisibilityOf("prepaid_phone_credit_landing_page_first_item_product_button", 10);
        tapElement("prepaid_phone_credit_landing_page_first_item_product_button");
        swipeUpToElement("prepaid_phone_credit_landing_page_pay_button");
    }

    public void submitForm() {
        tapElement("prepaid_phone_credit_landing_page_pay_button");
    }

    public void onBoardingPackage() {
        if (isElementVisible("prepaid_phone_credit_landing_page_next_button", 10)) {
            tapElement("prepaid_phone_credit_landing_page_next_button");
        }
    }

    public void goToHomePage() {
        backToHomePage();
    }

    public void tapOnPromoTerbaru() {
        tapElement("prepaid_phone_credit_promo_terbaru");
    }

    public void tapOnClosePromoTerbaru() {
        tapElement("prepaid_phone_credit_promo_terbaru_close");
    }

    public void validatePromoSalin() {
        validateExist("prepaid_phone_credit_salin", 10);
    }

    public void tapOnBayarButton() {
        if (isElementVisible("prepaid_phone_credit_bayar_button")) {
            tapElement("prepaid_phone_credit_bayar_button");
        } else {
            tapElement("prepaid_phone_credit_metode_lain_button");
        }
    }

    public void tapFirstDenom() {
        tapElement("prepaid_phone_credit_first_denom");
    }
    
    public void tapOnJumlahPulsaButton(String amount) {
        tapElement(constructLocator("prepaid_phone_credit_landing_page_indosat_button",amount));
    }
}
