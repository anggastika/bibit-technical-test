package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import bukalapak.data.PostpaidData;
import cucumber.api.java8.En;
import org.openqa.selenium.TimeoutException;

public class MultifinanceStepDefinitions extends TestInstrument implements En {

    public MultifinanceStepDefinitions() {

        When("user pays multifinance", () -> {
            bukalapak.multifinancePage().goToPage();
            bukalapak.multifinancePage().isDisplayed();
            bukalapak.multifinancePage().pickBiller(PostpaidData.getBiller());
            bukalapak.multifinancePage().typeCustomerNumber("valid");
            bukalapak.multifinancePage().tapOnBayarButton();
        });

        Then("contract number field on the Multifinance will have prefilled", () -> {
            bukalapak.multifinancePage().verifyCustomerNumberField();
        });

        When("user pays Multifinance bill with a valid contract number", () -> {
            bukalapak.multifinancePage().pickBiller("Kredivo");
            bukalapak.multifinancePage().typeCustomerNumber("valid");
            bukalapak.multifinancePage().tapOnBayarButton();
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
        });

        Then("login page will have displayed after do Multifinance non-login transaction", () -> {
            try {
                bukalapak.loginPage().userOnLoginPage();
            } catch (AssertionError e) {
                bukalapak.multifinancePage().verifyMultifinanceNumberHasBeenPaid();
            }
        });

        When("user choose service provider", () -> {
            bukalapak.multifinancePage().pickBiller("Kredivo");
        });

        And("user copies promo code on the Multifinance landing page", () -> {
            try {
                bukalapak.multifinancePage().tapOnPromoTerbaru();
                bukalapak.multifinancePage().tapSalin();
                bukalapak.multifinancePage().tapOnClosePromoTerbaru();
            } catch (TimeoutException e) {
                bukalapak.multifinancePage().verifyPromoCodeCondition();
            }
        });

        Then("the selected promo will have copied", () -> {
            bukalapak.multifinancePage().tapOnBayarButton();
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
            try {
                bukalapak.checkoutNonMarketplacePage().tapOnVoucherField();
                bukalapak.checkoutNonMarketplacePage().pasteVoucherCode();
                bukalapak.checkoutNonMarketplacePage().verifyTersalin();
            } catch (Exception e) {
                bukalapak.multifinancePage().verifyPromoCodeCondition();
            }
        });

        And("^user inputs (valid|invalid) contract number in the contract number field on the Multifinance landing page$", (String numberType) -> {
            bukalapak.multifinancePage().typeCustomerNumber(numberType);
        });

        Then("show error message \"([^\"]*)\"", (String errorMessage) -> {
            bukalapak.multifinancePage().verifyMultifinanceErrorIsDisplayed(errorMessage);
        });

        Then("Multifinance transaction will have continued", () -> {
            bukalapak.multifinancePage().tapOnBayarButton();
            bukalapak.apiCall().sendLogInfoToSlack(dotenv.get("WEBHOOK_CHANNEL"));
            try {
                bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            } catch (TimeoutException e) {
                bukalapak.multifinancePage().isDisplayed();
            }
        });
    }
}
