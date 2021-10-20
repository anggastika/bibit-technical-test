package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class InformVoucherAfterRegisterPage extends BasePage {

    public InformVoucherAfterRegisterPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyVoucherSheet() {
        verifyElementExist("VOUCHER_SHEET_TITLE", 10, "Voucher sheet not exist");
    }

    public void tapOnVerifyPhone() {
        tapElement("verify_phone_button");
    }

    public void verifyCekVoucherButton() {
        verifyElementExist("CEK_VOUCHER_BUTTON", 5, "Voucher button not found");
    }

    public void tapOnCekVoucher() {
        tapElement("CEK_VOUCHER_BUTTON");
    }

    public void verifyOnVoucherkuPage() {
        verifyElementExist("voucherku_title_page", 10, "Element not found");
        HelperData.setLastActionPage(new InformVoucherAfterRegisterPage(iosDriver));
    }

    public void closeVoucherSheet() {
        tapElement("CLOSE_VOUCHER_SHEET", 5);
    }

    public void closeDanaPopUp() {
        if (isElementExist("popup_dana_v2_close_button", 3)) {
            tapElement("popup_dana_v2_close_button");
        }
    }

    public void isOnDanaPageAfterRegister() {
        changeContext().toWebview();
        try {
            validateExist("dana_binding_after_register_pin_baru", 10);
        }catch(Exception e) {
            validateExist("dana_binding_after_register_pin_kamu", 10);
        }
        changeContext().toNative();
    }

    public void cancelBindingDanaAfterRegister() {
        tapElement("dana_binding_after_register_back_button");
        validateExist("dana_container_cancel_binding");
        tapElement("dana_container_cancel_binding");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
