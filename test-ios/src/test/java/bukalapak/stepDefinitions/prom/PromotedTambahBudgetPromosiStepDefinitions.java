package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedTambahBudgetPromosiStepDefinitions extends TestInstrument implements En {

    public PromotedTambahBudgetPromosiStepDefinitions() {
        When("^user input (.*) as Promoted budget$", (String budget) -> {
            bukalapak.promotedTambahBudgetPromosiPage().inputBudget(budget);
        });

        When("^user check Promoted Add Budget (.*) Invoice$", (String type) -> {
            bukalapak.promotedTambahBudgetPromosiPage().checkInformasiTagihan();
            bukalapak.promotedTambahBudgetPromosiPage().checkPromotedBudgetSection(type);
        });

        When("^user is in \"Tambah Budget Promosi\" page$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().userOnTambahBudgetPromosiPage();
        });

        When("^user is in \"Add Budget Success Invoice\" page$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().userOnSuccessPaymentPage();
        });

        When("^user is in \"Add Budget Success Invoice Detail\" page$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().userOnInvoiceDetailPage();
        });

        And("^user( non)? eligible loan verify information about daily budget$", (String status) -> {
            bukalapak.promotedTambahBudgetPromosiPage().verifyDailyBudgetInformation(status);
        });

        And("^user tap on lihat tagihan pembayaran top up budget promoted$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().tapOnLihatTagihanPembayaran();
        });

        When("^user is in \"Pending Payment Promoted Instant Invoice\" page$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().verifyPendingPromotedInstantPayment();
        });

        And("^user validate error message below minimum topup$", () -> {
            bukalapak.promotedTambahBudgetPromosiPage().validateErrorMessage();
        });
    }
}
