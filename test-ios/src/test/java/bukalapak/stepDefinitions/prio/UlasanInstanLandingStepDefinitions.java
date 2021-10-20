package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class UlasanInstanLandingStepDefinitions extends TestInstrument implements En {

    public UlasanInstanLandingStepDefinitions() {
        Then("^(non-)?subscribed user is in Ulasan Instan Dashboard page$", (String status) -> {
            bukalapak.ulasanInstanLandingPage().userOnDashboardPage(status != null);
        });

        Then("^user is in Automatic Review - Dashboard page with pending payment$", () -> {
            bukalapak.ulasanInstanLandingPage().userOnUlasanInstanPendingPaymentDashboardPage();
        });

        Then("^user is in Automatic Review - Riwayat page with pending payment for package (.*)$", (String automaticPackage) -> {
            bukalapak.ulasanInstanLandingPage().userOnUlasanInstanPendingPaymentRiwayatPage(Integer.parseInt(automaticPackage));
        });

        And("^user check the registered package (.*)$", (String automaticPackage) -> {
            bukalapak.ulasanInstanLandingPage().checkRegisteredAutomaticPackage(Integer.parseInt(automaticPackage));
        });
    }
}
