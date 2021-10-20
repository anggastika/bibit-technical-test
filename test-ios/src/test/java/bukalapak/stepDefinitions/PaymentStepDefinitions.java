package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentStepDefinitions extends TestInstrument implements En {

    private final static Logger LOGGER = LogManager.getLogger(BaseStepDefinitions.class);

    public PaymentStepDefinitions() {

        Given("user is in \"payment\" page", () -> {
            bukalapak.paymentPage().userOnPaymentPage();
        });

        When("user choose \"([^\"]*)\" to continue the payment", (String arg0) -> {
            ChoiceSelector.of(arg0)
                    .when("virtual account", () -> bukalapak.paymentPage().choosePayment("payment_va_radio_button"))
                    .when("transfer", () -> bukalapak.paymentPage().choosePayment("payment_transfer_radio_button"))
                    .when("bca klikpay", () -> bukalapak.paymentPage().choosePayment("payment_bca_klikpay_radio_button"))
                    .when("mandiri e-cash", () -> bukalapak.paymentPage().choosePayment("payment_mandiri_ecash_radio_button"))
                    .when("cimb", () -> bukalapak.paymentPage().choosePayment("payment_cimb_radio_button"))
                    .when("indomaret", () -> bukalapak.paymentPage().choosePayment("payment_indomaret_radio_button"))
                    .when("alfamart", () -> bukalapak.paymentPage().choosePayment("payment_alfamart_radio_button"))
                    .when("pos", () -> bukalapak.paymentPage().choosePayment("payment_pos_radio_button"))
                    .orElse(() -> LOGGER.info("Your page is not implemented yet: " + arg0));
            bukalapak.paymentPage().clickOnBayarButton();
        });

        Then("the payment can not be processed", () -> {
            /*
            Please avoid using BasePage method directly. The method can be invoked from inherited class (related page object class).
             */
            bukalapak.iOSBasePage().verifyElementExist("payment_bayar_button");
            bukalapak.iOSBasePage().verifyElementNotExist("payment_confirmation_lihat_tagihan_button");
        });

        Then("^user should be redirect to scan qr page$", () -> {
            bukalapak.paymentPage().checkIsPageBayarAjaExist();
        });

        //subsidies squad
        And("^user choose credit card on payment method$", () -> {
            bukalapak.paymentPage().selectPayment();
        });

        And("^user fill credit card information with \"([^\"]*)\"$", (String cardNumber) -> {
            bukalapak.paymentPage().userTapOnInputCC();
            bukalapak.paymentPage().typeCardNumber(cardNumber);
            bukalapak.paymentPage().fillDetailCreditCard();
        });
    }
}
