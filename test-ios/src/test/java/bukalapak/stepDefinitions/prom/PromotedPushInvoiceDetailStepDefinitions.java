package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushInvoiceDetailStepDefinitions extends TestInstrument implements En {

    public PromotedPushInvoiceDetailStepDefinitions() {
        Given("user is in \"Promoted Push - Add Budget Success Invoice Detail\" page", () -> {
            bukalapak.promotedPushInvoiceDetailPage().userOnInvoiceDetailPage();
        });

        When("^user check Promoted Push Add Budget (.*) Invoice$", (String type) -> {
            bukalapak.promotedPushInvoiceDetailPage().checkInformasiTagihan();
            bukalapak.promotedPushInvoiceDetailPage().checkPromotedPushBudgetSection(type);
        });
    }
}
