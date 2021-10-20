package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import bukalapak.data.vp.prepaid.UangElektronikData;
import cucumber.api.java8.En;

/**
 * Created by agustriawantiningtyas on 09/06/20.
 */
public class UangElektronikStepDefinitions extends TestInstrument implements En {
    public UangElektronikStepDefinitions(){

        Given("user know how to update Uang Elektronik balances", () -> {
            bukalapak.uangElektronikLandingPage().typeOnFieldNomorKartu(dotenv.get("UANG_ELEKTRONIK_TRANSACTION_CARD_BRI"));
            bukalapak.uangElektronikLandingPage().setOnFieldJenisKartu("bri");
            bukalapak.uangElektronikLandingPage().validateDenomination(true);
            bukalapak.uangElektronikLandingPage().tapOnDenomination(1);
            bukalapak.uangElektronikLandingPage().tapOnButtonCaraUpdate();
            bukalapak.uangElektronikLandingPage().validateRedirectedToWebview();
            bukalapak.uangElektronikLandingPage().tapBackButton();
        });

        When("user skip Uang Elektronik update information pop-up", () -> {
            bukalapak.uangElektronikLandingPage().skipUpdateBalanceInfo();
        });

        Then("the transaction card number will have pre-filled on the Uang Elektronik landing page", () -> {
            bukalapak.iOSBasePage().openDeepLink("/bl/uang-elektronik");
            bukalapak.uangElektronikLandingPage().validateOnPage();
            bukalapak.uangElektronikLandingPage().validateFieldJenisKartu(UangElektronikData.getCardNumber());
            bukalapak.uangElektronikLandingPage().validateFieldNomorKartu(UangElektronikData.getCardNumber());
        });

        Then("the Uang Elektronik landing page will have displayed", () -> {
            bukalapak.uangElektronikLandingPage().validateOnPage();
        });
    }
}
