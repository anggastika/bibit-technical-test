package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;

/**
 *
 * IMPORTANT NOTES:
 * Please simplify the test steps (from each page object class) into one line only in each features steps.
 */

public class CheckoutNonMarketplaceStepDefinitions extends TestInstrument implements En {

    public CheckoutNonMarketplaceStepDefinitions() {
        Given("user is in \"checkout_non_marketplace\" page", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
        });

        //region Deprecated
        When("user choose \"([^\"]*)\" non marketplace payment", (String payment) -> {
            bukalapak.checkoutNonMarketplacePage().choosePaymentMethod(payment);
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
            ChoiceSelector.of(payment)
                    .when("bcaklikpay", () -> bukalapak.checkoutNonMarketplacePage().verifyBCAKlikpayWebView())
                    .when("virtual_account", () -> bukalapak.checkoutNonMarketplacePage().continuePopUpVirtualAccountPayment());
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
        });

        When("user choose \"([^\"]*)\" non marketplace payment with voucher \"([^\"]*)\" applied", (String payment, String voucher) -> {
            bukalapak.checkoutNonMarketplacePage().choosePaymentMethod(payment);
            bukalapak.checkoutNonMarketplacePage().enterVoucherCode(voucher);
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
            ChoiceSelector.of(payment)
                    .when("bcaklikpay", () -> bukalapak.checkoutNonMarketplacePage().verifyBCAKlikpayWebView())
                    .when("virtual_account", () -> bukalapak.checkoutNonMarketplacePage().continuePopUpVirtualAccountPayment());
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
        });

        And("user choose \"([^\"]*)\" as payment method", (String arg0) -> {
            bukalapak.checkoutNonMarketplacePage().choosePaymentMethod(arg0);
            TransactionData.setPaymentMethod(arg0);
        });

        When("user choose force bukadompet for non marketplace product", () -> {
            bukalapak.checkoutNonMarketplacePage().choosePaymentForceBukaDompet();
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
        });

        And("user click Bayar Sekarang Button in Checkout Non Marketplace Page", () -> {
            bukalapak.checkoutNonMarketplacePage().tapBayarSekarangButton();
        });

        And("user validate voucher code to continue transaction", () -> {
            bukalapak.checkoutNonMarketplacePage().validateVoucherCodeStatus();
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });
        And("user click Bayar with VA payment method", () -> {
            TransactionData.setPaymentMethod("VA-BCA");
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });

        And("user memorizes the total payment on checkout non marketplace page", () -> {
            bukalapak.checkoutNonMarketplacePage().setTotalPayment();
        });

        //endregion

        And("user tap DANA as payment method on algebra VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnAlgebraVPCheckoutPage();
            bukalapak.checkoutNonMarketplacePage().tapDANAAlgebraVPCheckout();
        });

        And("user tap Tambah for Top Up DANA on algebra VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().tapTambahDANAAlgebraVPCheckout();
        });

        And("user go to DANA payment method page on Alchemy VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().goToMetodePembayaranPageAlchemyVPCheckout();
            bukalapak.checkoutNonMarketplacePage().goToDANAAlchemyVPCheckout();
        });

        And("user is in DANA payment method page on Alchemy VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnDANAAlchemyVPCheckout();
        });

        And("user input top up amount Rp(.*) using Saldo BukaDompet on Alchemy VP Checkout", (String amount) -> {
            bukalapak.checkoutNonMarketplacePage().tapChooseTopUpBDAlchemyVPCheckout();
            bukalapak.danaPaymentPage().inputTopupBDAmount(amount);
        });

        When("user tap Pindahin ke DANA button on Alchemy VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().tapConfirmTopUpBDAlchemyVPCheckout();
        });

        Then("user see top up success message and redirected back to DANA payment method page of Alchemy VP Checkout", () -> {
                    bukalapak.checkoutNonMarketplacePage().verifyTopUpBDAlchemyVPCheckoutSuccess();
                    bukalapak.checkoutNonMarketplacePage().userOnDANAAlchemyVPCheckout();
        });

        And("user input top up amount Rp(.*) using Saldo BukaDompet on Algebra VP Checkout", (String amount) -> {
            bukalapak.checkoutNonMarketplacePage().tapTopUpBDAlgebraVPCheckout();
            bukalapak.danaPaymentPage().inputTopupBDAmount(amount);
        });

        When("user tap Pindahin ke DANA button on Algebra VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().tapConfirmTopUpBDAlgebraVPCheckout();
        });
        
        Then("user see top up success message and redirected back to DANA payment method page of Algebra VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().verifyTopUpBDAlgebraVPCheckoutSuccess();
            bukalapak.checkoutNonMarketplacePage().userOnAlgebraVPCheckoutPage();
        });

        And("user tap Tambah for Top Up DANA on alchemy VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().tapTambahDANAAlchemyVPCheckout();
        });

        And("user choose Credits payment method page on Alchemy VP Checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().goToMetodePembayaranPageAlchemyVPCheckout();
            bukalapak.checkoutNonMarketplacePage().tapCreditsAlchemyVPCheckout();
        });

        And("^user binding DANA on Alchemy VP Checkout page's BukaCredits Binding Entry Point with \"([^\"]*)\"", (String pinFromENV) ->{
            bukalapak.danaBindingPage().inputPINDANA(pinFromENV,false);
        });

        And("user redirected to Credits Alchemy VP Checkout page with updated Credits balance", () -> {
            bukalapak.checkoutNonMarketplacePage().verifyDANABoundCreditsAlchemyVPCheckoutPage();
        });

        And("^user binding DANA on Algebra VP Checkout page with \"([^\"]*)\"", (String pinFromENV) ->{
            bukalapak.checkoutNonMarketplacePage().tapAktifkanButtonVPAlgebraCheckout();
            bukalapak.danaBindingPage().inputPINDANA(pinFromENV,false);
        });

        Then("user see success binding message on Algebra VP Checkout page", () -> {
            bukalapak.checkoutNonMarketplacePage().verifyBindAlgebraVPCheckoutSuccess();
        });

        Then("user redirected to DANA Algebra VP Checkout page with updated DANA balance", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnAlgebraVPCheckoutPage();
            bukalapak.checkoutNonMarketplacePage().verifyDANABoundAlgebraVPCheckoutPage();
        });

        Then("^user choose \"([^\"]*)\" Algebra VP Checkout$", (String payment) -> {
            bukalapak.checkoutNonMarketplacePage().choosePaymentOldCheckout(payment);
        });

        Then("user memorizes total payment on vp checkout page", () -> {
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
        });

        Then("user use \"([^\"]*)\" and mix with ([^\"]*) balance Rp \"([^\"]*)\" as payment method", (String payment, String mix, String nominal) -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutNonMarketplacePage().selectPaymentMethod(payment);
            bukalapak.checkoutNonMarketplacePage().setMixPayment(mix, nominal);
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });

        Then("user use \"([^\"]*)\" as payment method", (String payment) -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutNonMarketplacePage().selectPaymentMethod(payment);
            bukalapak.checkoutNonMarketplacePage().setTotalTagihan();
        });

        Then("user tap on bayar button on vp checkout page", () -> {
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });

        Then("user clear DANA voucher on vp checkout page", () -> {
            bukalapak.checkoutNonMarketplacePage().clearDANAVoucher();
        });

        And("user go to metode pembayaran on alchemy VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().goToMetodePembayaranPageAlchemyVPCheckout();
        });

        Then("user verify Saldo or BukaDompet freeze on alchemy VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().verifySaldoFreezeVPCheckout();
        });

        And("user select DANA and get Freeze Information Because Saldo Freeze on alchemy VP checkout", () -> {
            bukalapak.checkoutNonMarketplacePage().goToDANAAlchemyVPCheckout();
            bukalapak.checkoutNonMarketplacePage().verifyInfoDANAFreezeBySaldoFreezeAlchemyVPCheckout();
        });

        Then("user is in \"checkout algebra\" page", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnAlgebraVPCheckoutPage();
        });
    }
}
