package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PantauBarangSainganPage extends BasePage {

    public PantauBarangSainganPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPantauBarangSainganDashboard() {
        doPullRefresh(1);
        waitForVisibilityOf("pantau_barang_saingan_tambah_pantauan_btn", 20);
        verifyElementExist("pantau_barang_saingan_title");
        HelperData.setLastActionPage(new PantauBarangSainganPage(iosDriver));
    }

    public void clickTambahPantauan() {
        verifyElementDisplayed("pantau_barang_saingan_tambah_pantauan_btn");
        tapElement("pantau_barang_saingan_tambah_pantauan_btn");
    }

    public void verifyEmptyStatePantauSaingan() {
        doPullRefresh(1);
        verifyElementExist("pantau_saingan_empty_header_text");
        verifyElementExist("pantau_saingan_empty_desc_text");
        verifyElementExist("pantau_barang_saingan_tambah_pantauan_btn");
    }

    public void verifyMonitoringInfo(String keyword, String sorter) {
        waitForVisibilityOf(constructLocator("pantau_saingan_monitoring_title", keyword), 15);
        waitForVisibilityOf(constructLocator("pantau_saingan_monitoring_sort_by", sorter));
    }

    public void tapOnMonitoringWithKeyword(String keyword) {
        tapElement(constructLocator("pantau_saingan_monitoring_title", keyword));
    }

    public void removeAllMonitorings() {
        while (!isElementVisible("pantau_saingan_empty_header_text", 5)) {
            waitForVisibilityOf("pantau_saingan_monitoring_text", 20);
            tapElement("pantau_saingan_monitoring_text");
            waitForVisibilityOf("pantauan_saingan_setting_button", 20);
            tapElement("pantauan_saingan_setting_button");
            waitForVisibilityOf("pantauan_saingan_hapus_pantauan_text");
            tapElement("pantauan_saingan_hapus_pantauan_text");
            waitForVisibilityOf("pantauan_saingan_hapus_text");
            tapElement("pantauan_saingan_hapus_text");
            waitForVisibilityOf("pantau_barang_saingan_title");
            doPullRefresh(1);
        }
    }

    public void verifyMaximumState() {
        validateDisabled("pantau_barang_saingan_tambah_pantauan_btn");
        verifyTotalMonitoring(5);
        validateDisplayed("pantau_barang_saingan_desc_txt");
        HelperData.setLastActionPage(new PantauBarangSainganPage(iosDriver));
    }

    public void verifyTotalMonitoring(int total) {
        validateValue().equals(total, getElementsPresent("pantau_saingan_monitoring_section").size(), "Total pantauan bukan " + total);
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
