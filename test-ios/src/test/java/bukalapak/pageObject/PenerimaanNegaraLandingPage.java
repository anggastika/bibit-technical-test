package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PenerimaanNegaraLandingPage extends PostpaidBasePage {
    public PenerimaanNegaraLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPenerimaanNegaraLandingPage() {
        verifyElementExist("penerimaan_negara_landing_page_type_dropdown", 15, "Landing page can't to loaded");
        verifyElementExist("penerimaan_negara_landing_page_billing_code_type");
        verifyElementExist("penerimaan_negara_landing_page_mpng_section");
        HelperData.setLastActionPage(new PenerimaanNegaraLandingPage(iosDriver));
    }

    public void tapOnGeneralButton(String buttonName) {
        tapElement(constructLocator("penerimaan_negara_landing_page_general_button", buttonName));
    }

    public void tapOnMPNGSection() {
        tapElement("penerimaan_negara_landing_page_mpng_section");
    }

    public void validateMPNGPage() {
        waitForVisibilityOf("penerimaan_negara_landing_page_mpng_header_text", 25);
        verifyElementExist("penerimaan_negara_landing_page_mpng_validate_contains_text");
        HelperData.setLastActionPage(new PenerimaanNegaraLandingPage(iosDriver));
    }

    public void tapOnJenisPenerimaanNegaraDropdown() {
        tapElement("penerimaan_negara_landing_page_type_dropdown");
    }

    public void chooseJenisPenerimaanNegara(String type) {
        waitForVisibilityOf("penerimaan_negara_landing_page_search_type", 10);
        typeAndEnterValueWithTimeOut("penerimaan_negara_landing_page_search_type", type);
        verifyElementDisplayed(constructLocator("penerimaan_negara_landing_page_general_label_contains", type));
        tapOnPenerimaanNegaraList();
    }

    public void tapOnPenerimaanNegaraList() {
        tapElements("penerimaan_negara_landing_page_type_list", 0);
    }

    public void typeOnBillingCodeField(String billingCodeType) {
        if (billingCodeType.equals("valid")) {
            PostpaidData.setCustomerNumber(TestInstrument.dotenv.get("PENERIMAAN_NEGARA_VALID_BILLING_CODE"));
            typeAndEnterValueWithTimeOut("penerimaan_negara_landing_page_billing_code_type", TestInstrument.dotenv.get("PENERIMAAN_NEGARA_VALID_BILLING_CODE"));
        } else if (billingCodeType.equals("invalid")) {
            typeAndEnterValueWithTimeOut("penerimaan_negara_landing_page_billing_code_type", fakerPhoneNumber("1234", 11));
        }
    }

    public void validateOverlayInvalidBillCode() {
        verifyElementExist("penerimaan_negara_landing_page_overlay_header_text", 15, "Overlay Invalid Bill can't to loaded");
        validateElementWithText("penerimaan_negara_landing_page_overlay_validate_text", PostpaidData.billing_code_not_registered);
        HelperData.setLastActionPage(new PenerimaanNegaraLandingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
