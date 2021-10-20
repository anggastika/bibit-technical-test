package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PushPageStepDefinitions extends TestInstrument implements En {

    public PushPageStepDefinitions() {
        Given("user is in \"Push\" page", () -> {
            bukalapak.pushPage().userOnPushPage();
        });

        Given("user is in \"Buy Push Package\" page", () -> {
            bukalapak.pushPage().userOnBuyPushPackagePage();
        });

        Given("user is in \"Buy Push Package - Checkout\" page", () -> {
            bukalapak.pushPage().userOnPushCheckoutPage();
        });

        Given("user is in \"Buy Push Package - Success Invoice\" page", () -> {
            bukalapak.pushPage().userOnPushSuccessInvoicePage();
        });

        Given("user is in \"Buy Push Package - Success Invoice Detail\" page", () -> {
            bukalapak.pushPage().userOnPushInvoiceDetailPage();
        });

        Then("user is in onboarding push \"([^\"]*)\" page", (String page) -> {
            bukalapak.pushPage().userOnOnboardingPushPage(page);
        });

        Then("user click push package with (.*) Push", (String pushPackage) -> {
            bukalapak.pushPage().storePushDetailInfo(pushPackage);
            bukalapak.pushPage().clickPushPackage(pushPackage);
        });

        Then("^user select auto-extend otomatis with \"([^\"]*)\" for Push Package$", (String paymentMethod) -> {
            bukalapak.pushPage().selectAutoExtend(paymentMethod);
        });

        Then("^user check payment info for Push Package$", () -> {
            bukalapak.pushPage().checkPushPaymentInfo();
        });

        And("^user verify sisa push balance(.*)?$", (String actionType) -> {
            bukalapak.pushPage().checkPushBalance(actionType);
        });

        And("^user search \"([^\"]*)\" on Push landing page$", (String productName) -> {
            bukalapak.promBasePage().searchProduct(productName);
        });

        And("^user click Push button for \"([^\"]*)\"$", (String productName) -> {
            bukalapak.pushPage().clickPushButton(productName);
        });

        And("^user verify success push info text for product \"([^\"]*)\"$", (String productName) -> {
            bukalapak.pushPage().checkPushSuccessInfo(productName);
        });

        And("^user click the first product that contains \"([^\"]*)\"$", (String productName) -> {
            bukalapak.pushPage().clickTheFirstPushCheckbox(productName);
        });

        And("^user click Select All checkbox$", () -> {
            bukalapak.pushPage().clickSelectAllCheckbox();
        });

        And("^user click Push All button$", () -> {
            bukalapak.pushPage().clickPushAllButton();
        });

        And("user will see Buy Push or Loan modal", () -> {
            bukalapak.pushPage().checkBuyPushOrLoanModal();
        });

        And("^user click (Bayar dengan saldo|other payment options) button$", (String selectedPayment) -> {
            bukalapak.pushPage().selectPaymentMethod(selectedPayment);
        });

        And("user is on Push confirmation payment using BukaDompet page", () -> {
            bukalapak.promBasePage().hideLeakInfo();
            bukalapak.pushPage().checkPaymentConfirmationPage();
        });

        And("^user is in \"Atur isi push otomatis\" page$", () -> {
            bukalapak.pushPage().verifyAturPromosiOtomatisPage();
        });

        And("^user turn (on|off) button auto extend$", (String option) -> {
            bukalapak.pushPage().selectPushAutoExtend(option);
        });

        And("^user validate Push auto extend off as \"Tidak aktif\"$", () -> {
            bukalapak.pushPage().validatePushAutoExtendOffText();
        });

        And("^user validate Push auto extend on as \"([^\"]*)\"$", (String text) -> {
            bukalapak.pushPage().validatePushAutoExtendOnText(text);
        });

        And("^user tick isi otomatis with loan$", () -> {
            bukalapak.pushPage().checkIsiUlangOtomatis();
            bukalapak.pushPage().checkLoanOptionIsiUlangOtomatis();
        });

        And("^user validate Push auto extend saldo pinjaman on as \"([^\"]*)\"$", (String loanStatus) -> {
            bukalapak.pushPage().validatePushAutoExtendLoan(loanStatus);
        });

        When("user taps close button from Push Invoice page", () -> {
            bukalapak.pushPage().tapPushSuccessInvoiceCloseButton();
        });

        And("user taps on lihat tagihan pembayaran Push Package", () -> {
            bukalapak.pushPage().tapOnLihatDetailPesananButton();
        });

        And("user selects Transfer Bank as payment method for Push Package", () -> {
            bukalapak.pushPage().tapOnTransferBankPayment();
        });
    }
}
