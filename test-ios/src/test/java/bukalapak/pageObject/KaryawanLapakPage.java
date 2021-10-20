package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.Random;

import static bukalapak.TestInstrument.dotenv;

public class KaryawanLapakPage extends BasePage {

    public KaryawanLapakPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKaryawanLapak() {
        waitForVisibilityOf("karyawan_lapak_title", 60);
        verifyElementExist("karyawan_lapak_title");
        verifyElementExist("base_back_button");
        HelperData.setLastActionPage(new KaryawanLapakPage(iosDriver));
    }

    public void userOnKaryawanLapakNonSuperSeller() {
        waitForVisibilityOf("karyawan_lapak_title", 20);
        waitForVisibilityOf("karyawan_lapak_non_super_seller_title", 20);
        waitForVisibilityOf("karyawan_lapak_non_super_seller_desc", 5);
        HelperData.setLastActionPage(new KaryawanLapakPage(iosDriver));
    }

    public void tapDaftarSuperSeller() {
        waitForVisibilityOf("karyawan_lapak_non_super_seller_pelajari_super_seller_btn", 10);
        tapElement("karyawan_lapak_non_super_seller_pelajari_super_seller_btn");
    }

    public void verifyEmptyStateKaryawanLapak() {
        verifyElementExist("karyawan_lapak_empty_text");
        verifyElementExist("karyawan_lapak_tambah_karyawan_button");
        HelperData.setLastActionPage(new KaryawanLapakPage(iosDriver));
    }

    public void removeAllStaff(String passwordEnv) {
        String password = dotenv.get(passwordEnv);
        while (!isElementVisible("karyawan_lapak_empty_text")) {
            tapElement("karyawan_lapak_staff_chevron");
            waitForVisibilityOf("karyawan_lapak_staff_remove_button");
            tapElement("karyawan_lapak_staff_remove_button");
            waitForVisibilityOf("karyawan_lapak_password_field");
            typeAndEnterValueWithTimeOut("karyawan_lapak_password_field", password);
            waitForVisibilityOf("karyawan_lapak_konfirmasi_button");
            tapElement("karyawan_lapak_konfirmasi_button");
            waitForVisibilityOf("karyawan_lapak_title", 20);
        }
    }

    public void verifyUnconfirmedStaffInfo(String staffName, String staffEmail) {
        verifyElementExist(constructLocator("karyawan_lapak_staff_email_text", staffEmail));
        verifyElementExist("karyawan_lapak_staff_pending_label");
        verifyElementExist("karyawan_lapak_staff_pending_text");
    }

    public void verifyStaffPendingStatus() {
        waitForVisibilityOf("karyawan_lapak_staff_pending_status_text");
        verifyElementExist("karyawan_lapak_staff_pending_status_text");
    }
    
    public void tapConfirmedStaffInfo(String staffName) {
        verifyElementExist(constructLocator("karyawan_lapak_conf_info", staffName));
        tapElement(constructLocator("karyawan_lapak_conf_info", staffName));
    }

    public void tapToggleStaff(String actionStaff, String passwordEnv){
        if ((actionStaff.equalsIgnoreCase("deactivated")) && (isElementVisible("karyawan_lapak_nonaktifkan_button", 7))) {
            tapElement("karyawan_lapak_nonaktifkan_button");
            enterPasswordTextField(passwordEnv);
            tapElement("karyawan_lapak_konfirmasi_button");
        } else if ((actionStaff.equalsIgnoreCase("activated")) && (isElementVisible("karyawan_lapak_aktifkan_button", 7))) {
            tapElement("karyawan_lapak_aktifkan_button");
            enterPasswordTextField(passwordEnv);
            tapElement("karyawan_lapak_konfirmasi_button");
        }
    }

    public void verifyStaffPrivilege(String privilege) {
        verifyElementExist(constructLocator("karyawan_lapak_staff_privilege_info_text", privilege));
        HelperData.setLastActionPage(new KaryawanLapakPage(iosDriver));
    }

    public void verifyPremiumOffering(String packageName) {
        verifyElementExist("karyawan_lapak_offering_premium_image");
        verifyElementExist(constructLocator("karyawan_lapak_offering_premium_text", packageName));
        verifyElementExist(constructLocator("karyawan_lapak_upgrade_premium_button", packageName));
    }

    public void expandStaffInfo() {
        if (!isElementVisible("karyawan_lapak_staff_remove_button", 5)) {
            tapElement("karyawan_lapak_staff_chevron");
        }
    }

    public void verifyStaffQuotaFooter(String staffQuota) {
        verifyElementDisplayed(constructLocator("karyawan_lapak_quota_message_text", staffQuota));
    }

    public void enterPasswordTextField(String passwordEnv) {
        String password = dotenv.get(passwordEnv);
        waitForVisibilityOf("karyawan_lapak_password_field", 20);
        typeAndEnterValue("karyawan_lapak_password_field", password);
    }

    public void createRandomStaff() {
        tapElement("karyawan_lapak_tambah_karyawan_button");
        int randomNumber = + new Random().nextInt(1000);
        typeAndEnterValueWithTimeOut("tambah_karyawan_nama_field", "Karyawan " + randomNumber);
        typeAndEnterValueWithTimeOut("tambah_karyawan_email_field", "karyawan" + randomNumber + "@karyawanlap.ak");
        tapElement("tambah_karyawan_simpan_button");
    }

    public void confirmToRemoveStaff(String passwordEnv) {
        String password = dotenv.get(passwordEnv);
        waitForVisibilityOf("karyawan_lapak_password_field");
        typeAndEnterValueWithTimeOut("karyawan_lapak_password_field", password);
        waitForVisibilityOf("karyawan_lapak_konfirmasi_button");
        tapElement("karyawan_lapak_konfirmasi_button");
    }

    public void verifyFullQuotaStaff() {
        validateDisplayed("karyawan_lapak_quota_full_text");
        validateDisabled("karyawan_lapak_tambah_button");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
