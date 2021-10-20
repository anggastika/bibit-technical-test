package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerStatusProgramPage extends BasePage {
    public SuperSellerStatusProgramPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnSuperSellerStatusProgram() {
        waitForVisibilityOf("status_program_title_text", 20);
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void verifyKunjungiDashboardButton() {
        waitForVisibilityOf("super_seller_status_program_kunjungi_dashboard_button", 10);
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void verifyStatusProgram() {
        verifyElementDisplayed("super_seller_status_program_text");
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void clickGantiPaket() {
        waitForVisibilityOf("super_seller_ganti_paket_btn");
        tapElement("super_seller_ganti_paket_btn");
    }

    public void verifyInfoBadgeSuperSeller(String status) {
        if (status == null) {
            waitForVisibilityOf("super_seller_status_badge_info_txt", 15);
            validateDisplayed("super_seller_status_badge_info_tutup_btn");
        } else {
            validateNotDisplayed("super_seller_status_badge_info_txt");
            validateNotDisplayed("super_seller_status_badge_info_tutup_btn");
        }
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void tapTutupInfoBadgeBtn() {
        waitForVisibilityOf("super_seller_status_badge_info_tutup_btn", 15);
        tapElement("super_seller_status_badge_info_tutup_btn");
    }

    public void verifyOptOutPopUp() {
        validateDisplayed("super_seller_opt_out_pop_up_title");
        validateDisplayed("super_seller_opt_out_pop_up_continue_button");
        validateDisplayed("super_seller_opt_out_pop_up_cancel_button");
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void verifyBadgeBaru(String state) {
        if (state == null) {
            waitForVisibilityOf("super_seller_status_badge_baru_txt", 5);
        } else {
            validateNotDisplayed("super_seller_status_badge_baru_txt");
        }
        HelperData.setLastActionPage(new SuperSellerStatusProgramPage(iosDriver));
    }

    public void tapMutasiSuperSeller() {
        tapElement("super_seller_status_mutasi_super_seller_btn", 5);
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
