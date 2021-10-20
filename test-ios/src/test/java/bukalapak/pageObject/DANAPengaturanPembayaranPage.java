package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DANAPengaturanPembayaranPage extends BasePage {

    public DANAPengaturanPembayaranPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnDANAPengaturanPembayaranPage() {
        verifyElementExist("dana_pengaturan_pembayaran_title_text");
        verifyElementExist("dana_pengaturan_pembayaran_saldo_dana_text");
        verifyElementExist("dana_pengaturan_pembayaran_mini_dana");
        verifyElementExist("dana_pengaturan_pembayaran_unbind_button");
    }

    public void tapUnbindButton() {
        waitForVisibilityOf("dana_pengaturan_pembayaran_unbind_button");
        tapElement("dana_pengaturan_pembayaran_unbind_button");
    }

    public void verifyCannotUnbind() {
        verifyElementExist("dana_pengaturan_pembayaran_modal_putuskan_akun");
        verifyElementExist("dana_pengaturan_pembayaran_modal_bukabantuan");
    }

    public void tapBukaBantuanButton() {
        waitForVisibilityOf("dana_pengaturan_pembayaran_modal_bukabantuan");
        tapElement("dana_pengaturan_pembayaran_modal_bukabantuan");
    }

    public void userOnBukaBantuanPage() {
        waitForVisibilityOf("dana_pengaturan_pembayaran_bukabantuan_header", 5);
    }

    public void tapMyDANAButton() {
        waitForVisibilityOf("dana_pengaturan_pembayaran_my_dana_button");
        tapElement("dana_pengaturan_pembayaran_my_dana_button");
    }

    public void verifyDanaConfirmChangePN() {
        String[] element = {"dana_change_pn_header", "dana_change_pn_logo", "dana_change_pn_balance",
                "dana_change_pn_desc", "dana_change_pn_desc_point1", "dana_change_pn_desc_point2",
                "dana_change_pn_close_btn", "dana_change_pn_checkbox_txt", "dana_change_pn_save_btn"};

        verifyElementListExist(element);
        if (!isElementExist("dana_change_pn_checkbox",3)) {
            verifyElementExist("dana_change_pn_checkbox_alt");
        }
        HelperData.setLastActionPage(new EditPhonePage(iosDriver));
    }

    public void verifyDanaConfirmChangePNnotAppear() {
        verifyElementNotExist("dana_change_pn_header");
        verifyElementNotExist("dana_change_pn_desc");
        waitForVisibilityOf("verification_akun_title");
        HelperData.setLastActionPage(new EditPhonePage(iosDriver));
    }

    public void tapOnChangePNButton() {
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_button");
        tapElement("dana_pengaturan_pembayaran_change_pn_button");
    }

    public void validateChangePNLandingPage() {
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_landing_header");
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_landing_back_btn");
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_landing_title");
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_landing_next_btn");
        validateDisplayed("dana_pengaturan_pembayaran_change_pn_landing_cancel_btn");
    }

    public void tapOnNextBtn() {
        tapElement("dana_pengaturan_pembayaran_change_pn_landing_next_btn");
    }

    public void tapOnCancelBtn() {
        tapElement("dana_pengaturan_pembayaran_change_pn_landing_cancel_btn");
    }

    public void goToHomePage() {
        while (isElementVisible("base_back_button")) {
            tapElement("base_back_button");
        }

        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
