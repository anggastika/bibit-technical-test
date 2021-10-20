package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class InternetBankingStepDefinitions extends TestInstrument implements En {

    public InternetBankingStepDefinitions() {

        Given("user is in \"internet_banking\" page", () -> {
            bukalapak.internetBankingPage().userOnInternetBankingPage();
        });

        Given("user is in \"bca_klikpay\" page", () -> {
            bukalapak.bcaKlikPayPage().userOnBCAKlikPayPage();
        });

        Given("user is in \"linkaja\" page", () -> {
            bukalapak.linkAjaPage().userOnLinkAjaPage();
        });

        Given("user is in \"cimb_click\" page", () -> {
            bukalapak.cimbClickPage().userOnCimbClickPage();
        });

        When("user tap on back button on internet banking webview", () -> {
            bukalapak.linkAjaPage().tapOnBackButton();
        });
    }
}
