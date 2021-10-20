package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class KaryawanLapakStepDefinitions extends TestInstrument implements En {
    public KaryawanLapakStepDefinitions() {
        Given("user is in \"Karyawan Lapak\" page", () -> {
            bukalapak.karyawanLapakPage().userOnKaryawanLapak();
        });

        Then("user is in Karyawan Lapak page for non super seller$", () -> {
            bukalapak.karyawanLapakPage().userOnKaryawanLapakNonSuperSeller();
        });

        Then("^user is shown empty state Karyawan Lapak$", () -> {
            bukalapak.karyawanLapakPage().verifyEmptyStateKaryawanLapak();
        });

        When("^user removes all staff with password \"([^\"]*)\"$", (String passwordEnv) -> {
            bukalapak.karyawanLapakPage().removeAllStaff(passwordEnv);
        });

        Then("^user is shown unconfirmed staff with name \"([^\"]*)\" and email \"([^\"]*)\"$", (String staffName, String staffEmail) -> {
            bukalapak.karyawanLapakPage().verifyUnconfirmedStaffInfo(staffName, staffEmail);
        });

        Then("^user is shown staff status Menunggu Konfirmasi$", () -> {
            bukalapak.karyawanLapakPage().verifyStaffPendingStatus();
        });

        Then("^user is shown staff privileges \"([^\"]*)\"$", (String privilege) -> {
            bukalapak.karyawanLapakPage().verifyStaffPrivilege(privilege);
        });

        Then("^user is shown Premium (Professional|Platinum) offering on Karyawan Lapak page$", (String packageName) -> {
            bukalapak.karyawanLapakPage().verifyPremiumOffering(packageName);
        });

        When("^user taps to expand staff info$", () -> {
            bukalapak.karyawanLapakPage().expandStaffInfo();
        });

        When("^user is shown message to add (.+) more staff$", (String staffQuota) -> {
            bukalapak.karyawanLapakPage().verifyStaffQuotaFooter(staffQuota);
        });

        When("^user taps staff info with name \"([^\"]*)\"$", (String staffName) -> {
            bukalapak.karyawanLapakPage().tapConfirmedStaffInfo(staffName);
        });

        And("^user confirms to update staff with password \"([^\"]*)\"$", (String passwordEnv) -> {
            bukalapak.karyawanLapakPage().enterPasswordTextField(passwordEnv);
        });

        When("^user adds a staff on Karyawan Lapak page$", () -> {
            bukalapak.karyawanLapakPage().createRandomStaff();
        });

        When("^user confirms to remove staff with password \"([^\"]*)\"$", (String passwordEnv) -> {
            bukalapak.karyawanLapakPage().confirmToRemoveStaff(passwordEnv);
        });

        When("^user tap pelajari super seller button in karyawan lapak page$", () -> {
            bukalapak.karyawanLapakPage().tapDaftarSuperSeller();
        });

        Then("^user is shown info full quota for staff$", () -> {
            bukalapak.karyawanLapakPage().verifyFullQuotaStaff();
        });

        When("^user (activated|deactivated) karyawan in karyawan lapak page with password \"([^\"]*)\"$", (String actionStaff, String passwordEnv) -> {
            bukalapak.karyawanLapakPage().tapToggleStaff(actionStaff, passwordEnv);
        });
    }
}
