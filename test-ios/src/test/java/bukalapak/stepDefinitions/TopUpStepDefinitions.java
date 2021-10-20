package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TopUpStepDefinitions extends TestInstrument implements En {

    public TopUpStepDefinitions() {

        When("user choose \"([^\"]*)\" in Transfer Bank section", (String arg0) -> {
            /*
            Please avoid using BasePage method directly. The method can be invoked from inherited class (related page object class).
             */
            bukalapak.iOSBasePage().tapElement(arg0 + "_bank_text_view");
        });

        When("virtual account number must be \"([^\"]*)\"", (String arg0) -> {
            bukalapak.topUpPage().validateVANumber(arg0);
        });

        When("user tap TUTUP button", () -> {
            /*
            Please avoid using BasePage method directly. The method can be invoked from inherited class (related page object class).
             */
            bukalapak.iOSBasePage().swipeDownToElement("close_button");
            bukalapak.topUpPage().tapCloseButton();
        });

        When("user choose Saldo payment method", () -> {
            bukalapak.topUpPage().chooseSaldo();
            bukalapak.topUpPage().tapBayar();
        });

        When("user choose Indomaret payment method", () -> {
            bukalapak.topUpPage().chooseIndomaret();
            bukalapak.topUpPage().tapBayar();
        });

        And("user tap on topup DANA page back button", () -> {
            bukalapak.topUpPage().tapOnBackButton();
        });

        // region topup DANA with investment product

        Then("^user see DANA balance in topup page", () -> {
            bukalapak.topUpPage().cekDANABalanceText();
        });

        Then("user select BukaEmas as topup to DANA method", () -> {
            bukalapak.topUpPage().selectTopUpDANAWithBukaEmas();
        });

        Then("user verify BukaReksa section displayed", () -> {
            bukalapak.topUpPage().verifyBukareksaSectionDisplayed();
        });

        Then("user verify BukaReksa section not displayed", () -> {
            bukalapak.topUpPage().verifyBukareksaSectionNotDisplayed();
        });

        And("user select BukaReksa as topup to DANA method", () -> {
            bukalapak.topUpPage().tapOnBukaReksaSection();
        });

        And("user verify top up to DANA with \"([^\"]*)\" sheet dragable displayed", (String product) -> {
            bukalapak.topUpPage().verifyDANATopUpSheetDragableDisplayed(product);
        });

        And("user input \"([^\"]*)\" as topup DANA with investment product amount", (String nominal) -> {
            bukalapak.topUpPage().inputNominaltopUpDANAWithInvestmentProduct(nominal);
        });

        Then("user verify \"([^\"]*)\" topup validation error message displayed", (String method) -> {
            bukalapak.topUpPage().verifyErrorMessageDisplayed(method);
        });

        Then("user tap on pindahin ke DANA button", () -> {
            bukalapak.topUpPage().tapOnPindahinKeDANABtn();
        });

        And("user confirms redeem to DANA process", () -> {
            bukalapak.topUpPage().confirmRedeemBukaReksaToDANAForm();
        });

        And("user verify waiting processed banner \"([^\"]*)\" displayed", (String product) -> {
            bukalapak.topUpPage().verifyWaitingProcessedBannerDisplayed(product);
        });

        And("user wait until waiting processed banner \"([^\"]*)\" disappear", (String product) -> {
            bukalapak.topUpPage().verifyWaitingProcessedBannerNotDisplayed(product);
        });

        Then("user verify topup to DANA success message displayed", () -> {
            bukalapak.topUpPage().verifyTopUpSuccessMessageDisplayed();
        });

        And("user select topup to DANA tnc checkbox", () -> {
            bukalapak.topUpPage().tapOnBukaReksaTncCheckbox();
        });

        And("user verify DANA balance after topup DANA", () -> {
            bukalapak.topUpPage().verifyDANABalanceUpdated();
        });

        When("user tap on Jual Sekarang button", () -> {
            bukalapak.topUpPage().tapOnJualSekarangBtn();
        });

        Then("user verify topup confirmation detail", () -> {
            bukalapak.topUpPage().verifyInvestasikuConfirmationModalDisplayed();
        });

        Then("user should not be able to see topup confirmation detail", () -> {
            bukalapak.topUpPage().verifyInvestasikuConfirmationModalNotDisplayed();
        });

    }
}
