package bukalapak.stepDefinitions.us;

import bukalapak.TestInstrument;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;

/**
 * maintain by Core User [Prasetyo Nugroho, Nurmisari Namy]
 */
public class RiwayatPerangkatStepDefinitions extends TestInstrument implements En {
    public RiwayatPerangkatStepDefinitions() {
        When("^user is in \"Riwayat Perangkat\" page$", () -> {
            bukalapak.riwayatPerangkatPage().userOnRiwayatPerangkatPage();
        });

        When("^user click Batal button on logout all confirmation$", () -> {
            bukalapak.riwayatPerangkatPage().tapBatalButton();
        });

        When("^Perangkat yang sedang digunakan displays valid device name \"([^\"]*)\"$", (String deviceName) -> {
            bukalapak.riwayatPerangkatPage().verifyMainDeviceName(deviceName);
        });

        When("^user should see device history log detail displays valid login device data$", (DataTable deviceData) -> {
            bukalapak.riwayatPerangkatPage().verifyDeviceDetailModal();
            bukalapak.riwayatPerangkatPage().verifyMainDeviceDetailData(deviceData);
        });

        When("^user should see (5|all) login device details in perangkat lain section$", (String condition) -> {
            bukalapak.riwayatPerangkatPage().validateOtherDeviceCount(condition);
        });

        When("^user click Lanjut button on logout all confirmation$", () -> {
           bukalapak.riwayatPerangkatPage().tapLanjutButton();
        });

        When("user login again using credential \"([^\"]*)\" after logout all$", (String password) -> {
            bukalapak.loginPage().loginAfterLogoutAll(password);
        });

        When("^Perangkat lain contains valid device name \"([^\"]*)\"$", (String deviceName) -> {
            bukalapak.riwayatPerangkatPage().verifyOtherDeviceName(deviceName);
            bukalapak.riwayatPerangkatPage().verifyOtherDeviceTime(deviceName);
        });

        When("^user click at device name \"([^\"]*)\" in Perangkat lain$", (String deviceName) -> {
            bukalapak.riwayatPerangkatPage().clickOtherDeviceName(deviceName);
        });

        When("^Detail Perangkat of Perangkat lain displays valid login device data :$", (DataTable deviceData) -> {
            bukalapak.riwayatPerangkatPage().verifyDeviceDetailModal();
            bukalapak.riwayatPerangkatPage().verifyOtherDeviceDetailData(deviceData);
        });

        When("^User go back to akun page$", () -> {
           bukalapak.akunPage().backToAkunPage();
        });
    }
}
