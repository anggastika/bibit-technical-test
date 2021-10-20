package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class KaryawanLapakLogAktivitasPage extends BasePage {
    public KaryawanLapakLogAktivitasPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnLogAktivitas() {
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_title", 15);
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_search_textfield", 10);
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_filter_btn", 10);
        HelperData.setLastActionPage(new KaryawanLapakLogAktivitasPage(iosDriver));
    }

    public void verifyLogActivityInTheList() {
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_karyawan_name", 10);
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_activity_date", 5);
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_activity_name", 5);
        HelperData.setLastActionPage(new KaryawanLapakLogAktivitasPage(iosDriver));
    }

    public void tapFilterBtn() {
        tapElement("karyawan_lapak_log_aktivitas_filter_btn", 3);
    }

    public void selectFilterTypeMenu(String filterCategory, String subFilterCategory) {
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_filter_title", 10);
        tapElement(constructLocator("karyawan_lapak_log_aktivitas_filter_type", filterCategory));
        tapElement(constructLocator("karyawan_lapak_log_aktivitas_sub_filter_radio_button", subFilterCategory), 3);
    }

    public void tapTerapkanFilterBtn() {
        tapElement("karyawan_lapak_log_aktivitas_filter_terapkan_button", 3);
    }

    public void verifyFilterResult(String filterResult) {
        waitForVisibilityOf("karyawan_lapak_log_aktivitas_activity_name", 5);
        verifyElementExist(constructLocator("karyawan_lapak_log_aktivitas_activity_name_list", filterResult));
        validateDisplayed("karyawan_lapak_log_aktivitas_activity_date");
        HelperData.setLastActionPage(new KaryawanLapakLogAktivitasPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
