package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanInvoiceStepDefinitions extends TestInstrument implements En {

    public UlasanInstanInvoiceStepDefinitions() {
        Then("^user check the Automatic Review package (.*)$", (String automaticPackage) -> {
            bukalapak.ulasanInstanInvoicePage().checkInvoiceDetailForAutomaticReview(Integer.parseInt(automaticPackage));
        });

        Then("^user is in Automatic Review invoice with (pending|instant) payment$", (String paymentType) -> {
            bukalapak.ulasanInstanInvoicePage().userOnUlasanInstanInvoicePage(paymentType);
        });
    }
}
