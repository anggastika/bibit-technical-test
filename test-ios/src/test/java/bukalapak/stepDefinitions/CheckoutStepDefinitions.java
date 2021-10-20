package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class CheckoutStepDefinitions extends TestInstrument implements En {

    public CheckoutStepDefinitions() {

        When("user using \"([^\"]*)\" payment to continue transaction", (String payment) -> {
            bukalapak.checkoutPage().choosePaymentMethod(payment);
            bukalapak.checkoutPage().submitForm();
        });

        And("^user tap Lanjut ke Pembayaran button$", () -> {
            bukalapak.checkoutPage().chooseAnotherPaymentMethod();
        });

        And("^user using \"([^\"]*)\" as payment method$", (String payment) -> {
            bukalapak.checkoutPage().choosePaymentMethod(payment);
        });

        And("^user \"([^\"]*)\" mix Buka DANA payment with BukaDompet$", (String mixPayment) -> {
            bukalapak.checkoutPage().tapOnPayHalfWithBukaDompet(mixPayment);
        });

        And("^user tap Bayar button$", () -> {
            bukalapak.checkoutPage().tapPayButton();
        });

        And("^user tap \"([^\"]*)\" button in Checkout page$", (String arg0) -> {
            bukalapak.checkoutPage().topUpDana();
        });

        And("^user memorizes the total payment on non alchemy checkout page$", () -> {
            bukalapak.checkoutPage().setTotalPayment();
        });

        And("^user shown Hubungkan akun$", () -> {
            bukalapak.checkoutPage().checkingDANAUnbindCheckout();
        });

        And("^user set quantity to \"([^\"]*)\" on checkout alchemy", (String qty) -> {
            bukalapak.checkoutPage().editQtyItems(qty);
        });

        When("user choose \"([^\"]*)\" payment method for voucher", (String payment) -> {
            bukalapak.checkoutPage().choosePaymentMethod(payment);
        });
    }
}
