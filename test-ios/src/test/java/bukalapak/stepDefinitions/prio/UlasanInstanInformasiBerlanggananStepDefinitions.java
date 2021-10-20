package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanInformasiBerlanggananStepDefinitions extends TestInstrument implements En {

    public UlasanInstanInformasiBerlanggananStepDefinitions() {
        Given("user is in \"Ulasan Instan Informasi Berlangganan\" page", () -> {
            bukalapak.ulasanInstanInformasiBerlanggananPage().userOnInformasiBerlanggananPage();
        });

        Then("^user verify Automatic Review auto-extend is (not-)?active$", (String status) -> {
            bukalapak.ulasanInstanInformasiBerlanggananPage().checkAutoExtend(status != null);
        });

        And("^user check detail subscription for package (.*)$", (String automaticPackage) -> {
            bukalapak.ulasanInstanInformasiBerlanggananPage().checkAutomaticPackage(automaticPackage);
        });

        And("^user success (reactivate|stop) auto-extend automatic review$", (String statusActivate) -> {
            bukalapak.ulasanInstanInformasiBerlanggananPage().userSuccessExtend(statusActivate);
        });
    }
}
