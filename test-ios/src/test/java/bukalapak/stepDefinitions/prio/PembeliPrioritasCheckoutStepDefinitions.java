package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PembeliPrioritasCheckoutStepDefinitions extends TestInstrument implements En {

    public PembeliPrioritasCheckoutStepDefinitions() {

        Given("user is in \"Pembeli Prioritas Checkout Detail\" page", () -> {
            bukalapak.pembeliPrioritasCheckoutPage().userOnDetailPembelianPage();
        });

        Given("user is in \"Pembeli Prioritas Checkout Pembayaran\" page", () -> {
            bukalapak.pembeliPrioritasCheckoutPage().userOnPembayaranPage();
        });

        When("user check t&c agreement", () -> {
            bukalapak.pembeliPrioritasCheckoutPage().tickTnCAgreement();
        });

        When("user select \"([^\"]*)\" as payment method", (String paymentMethod) -> {
            bukalapak.pembeliPrioritasCheckoutPage().selectPaymentMethod(paymentMethod);
        });
    }
}
