package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanCheckoutStepDefinitions extends TestInstrument implements En {

    public UlasanInstanCheckoutStepDefinitions() {
        Then("^user is on Automatic Review Checkout page - (.*) for package (\\d+)$", (String page, Integer automaticPackage) -> {
            bukalapak.ulasanInstanCheckoutPage().userOnAutomaticReviewCheckoutPackagePage(page, automaticPackage);
        });

        Then("^user verify the detail payment of Automatic Review package (.*)$", (String packageNumber) -> {
            bukalapak.ulasanInstanCheckoutPage().userVerifyDetailPayment(Integer.parseInt(packageNumber));
        });

        And("^user click Bayar dengan Bukadompet button$", () -> {
            bukalapak.ulasanInstanCheckoutPage().payWithBukaDompet();
        });
    }
}
