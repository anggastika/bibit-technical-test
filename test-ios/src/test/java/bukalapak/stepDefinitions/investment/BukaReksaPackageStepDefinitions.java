package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaPackageStepDefinitions extends TestInstrument implements En {

    public BukaReksaPackageStepDefinitions() {

        Then("user should be able to see BukaReksa package", () -> {
            bukalapak.bukaReksaPackagePage().validateReksaPackage();
        });

        When("^user tap on Package \"([^\"]*)\"$", (String packageName) -> {
            bukalapak.bukaReksaPackagePage().tapOnPackageName(packageName);
        });

        Then("^user should be able to see package detail page displayed$", () -> {
            bukalapak.bukaReksaPackagePage().verifyPackageDetailPage();
        });

        When("user tap on bukaemas product package detail", () -> {
            bukalapak.bukaReksaPackagePage().tapOnBukaemasProduct();
        });

        When("^user tap on Lanjutkan button in package detail page$", () -> {
            bukalapak.bukaReksaPackagePage().tapOnLanjutkanButton();
        });

        Then("user validate bukaemas view only page displayed", () -> {
            bukalapak.bukaReksaPackagePage().validateBukaemasWebviewOnlyPage();
        });

        And("user back from bukaemas webview only to detail package", () -> {
            bukalapak.bukaReksaPackagePage().backFromBukaemasWebviewOnly();
        });

        And("^user taps on bukaemas link in transaction detail page$", () -> {
            bukalapak.bukaReksaPackagePage().tapOnBukaEmasTransactionDetailLink();
        });

    }

}
