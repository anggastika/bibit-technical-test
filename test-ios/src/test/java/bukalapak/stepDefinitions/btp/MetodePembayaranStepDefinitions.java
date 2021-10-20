package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;

public class MetodePembayaranStepDefinitions extends TestInstrument implements En {
    public MetodePembayaranStepDefinitions() {
        Given("user is in \"metode_pembayaran\" page", () -> {
            bukalapak.metodePembayaranPage().userOnMetodePembayaranPage();
        });

        When("^user select (.*) as payment method for Marketplace transaction$", (String paymentMethod) -> {
            ChoiceSelector.of(paymentMethod)
                    .when("Cicilan Tanpa Kartu Kredit", () ->
                            bukalapak.metodePembayaranPage().selectPaymentMethod("metode_pembayaran_cicilan_tanpa_kartu_kredit_text"));
        });

        And("user change payment method to BCAOneklik", () -> {
            bukalapak.iOSBasePage().tapElement("checkout_metode_bayar_text");
            bukalapak.metodePembayaranPage().userOnMetodePembayaranPage();
            bukalapak.iOSBasePage().swipeUpToElement("metode_pembayaran_oneklik_text");
            bukalapak.iOSBasePage().tapElement("metode_pembayaran_oneklik_text");
        });

        When("user select payment method \"([^\"]*)\" on recurring payment page", (String method) -> {
            bukalapak.metodePembayaranPage().selectPaymentMethodRecurringPayment(method);
        });

        Then("user verify Saldo or BukaDompet freeze", () -> {
            bukalapak.metodePembayaranPage().verifySaldoFreeze();
        });

    }
}
