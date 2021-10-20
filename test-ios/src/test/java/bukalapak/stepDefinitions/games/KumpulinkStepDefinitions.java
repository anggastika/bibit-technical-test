package bukalapak.stepDefinitions.games;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class KumpulinkStepDefinitions extends TestInstrument implements En {
    public KumpulinkStepDefinitions() {

        When("user visit kumpulink page of seller who have active product but doesn't set highligt product",() -> {
            bukalapak.iOSBasePage().openDeeplinkFromEnv("KUMPULINK_NO_HIGHLIGHT_DEEPLINK");
        });

        When("user visit kumpulink page of seller", () -> {
            bukalapak.iOSBasePage().openDeeplinkFromEnv("KUMPULINK_DEEPLINK_SELLER");
        });

        When("user is on seller kumpulink page", () -> {
            bukalapak.kumpulinkPage().isOnKumpulinkPage();
        });

        When("user click lihat semua barang button", () -> {
            bukalapak.kumpulinkPage().tapOnSemuaBarangButton();
        });

        Then("user will see button lihat semua product", () -> {
            bukalapak.kumpulinkPage().validateSeeAllProductButton();
        });

        Then("user will not see highlight product section", () -> {
            bukalapak.kumpulinkPage().validateNoHighlightProduct();
        });

        Then("user will see highlight product section", () -> {
            bukalapak.kumpulinkPage().validateHighlightSection();
        });

        Then("user is on catalogue page", () -> {
            bukalapak.kumpulinkPage().isOnKumpulinkCataloguePage();
        });

    }
}
