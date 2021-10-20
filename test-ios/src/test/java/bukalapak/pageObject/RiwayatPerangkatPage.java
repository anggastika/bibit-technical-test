package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;
import java.util.List;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class RiwayatPerangkatPage extends BasePage {
    public RiwayatPerangkatPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnRiwayatPerangkatPage() {
        assertTrue(isElementVisible("riwayat_perangkat_title", 10));
        HelperData.setLastActionPage(new RiwayatPerangkatPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapBatalButton() {
        waitForElementClickable("riwayat_perangkat_batal_button", 5);
        tapElement("riwayat_perangkat_batal_button");
    }

    public void tapLanjutButton() {
        waitForElementClickable("riwayat_perangkat_lanjut_logout_button", 5);
        tapElement("riwayat_perangkat_lanjut_logout_button");
    }

    public void verifyMainDeviceName(String deviceName) {
        validateElementContainsText("riwayat_perangkat_main_device_text", deviceName);
    }

    public void verifyDeviceDetailModal() {
        waitForVisibilityOf("riwayat_perangkat_detail_title_label");
        verifyElementExist("riwayat_perangkat_detail_title_label");
        verifyElementExist("riwayat_perangkat_detail_perangkat_label");
        verifyElementExist("riwayat_perangkat_detail_akses_melalui_label");
        verifyElementExist("riwayat_perangkat_detail_location_label");
        verifyElementExist("riwayat_perangkat_detail_ip_label");
    }

    public void verifyMainDeviceDetailData(DataTable deviceData) {
        List<String> mainDeviceDetailData = deviceData.asList(String.class);

        verifyElementExist("riwayat_perangkat_detail_main_device_label");
        validateElementContainsText("riwayat_perangkat_detail_device_name_text", mainDeviceDetailData.get(0));
        validateElementContainsText("riwayat_perangkat_detail_device_os_text", mainDeviceDetailData.get(1));
    }

    public int getTotalOtherDevices() {
        return getElementsPresent("riwayat_perangkat_perangkat_lain_cell").size();
    }

    public void validateOtherDeviceCount(String condition) {
        swipeUpToElement("riwayat_perangkat_see_more_button");

        if (condition.equals("5")) {
            verifyElementExist("riwayat_perangkat_see_more_button");
            validateValue().equals(5, getTotalOtherDevices());
        } else {
            tapElement("riwayat_perangkat_see_more_button");
            assertTrue(getTotalOtherDevices() >= 5);
        }
    }

    public void verifyOtherDeviceName(String deviceName) {
        verifyElementExist(constructLocator("riwayat_perangkat_other_device_name_text", deviceName));
    }

    public void verifyOtherDeviceTime(String deviceName) {
        verifyElementExist(constructLocator("riwayat_perangkat_other_device_time_text", deviceName));
    }

    public void clickOtherDeviceName(String deviceName) {
        tapElement(constructLocator("riwayat_perangkat_other_device_name_text", deviceName));
    }

    public void verifyOtherDeviceDetailData(DataTable deviceData) {
        List<String> otherDeviceDetailData = deviceData.asList(String.class);

        validateElementContainsText("riwayat_perangkat_detail_other_device_name_text", otherDeviceDetailData.get(0));
        validateElementContainsText("riwayat_perangkat_detail_device_os_text", otherDeviceDetailData.get(1));
    }
}
