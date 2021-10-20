package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ProdukDigitalStepDefinitions extends TestInstrument implements En  {
    public ProdukDigitalStepDefinitions(){

        When("user buys Produk Digital with a valid data", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://www.bukalapak.com/p/games/mobile-legend/3jiltd5-jual-diamond-mobile-legends-429");
            bukalapak.produkDigitalPage().validateOnPage();
            bukalapak.produkDigitalPage().tapOnBeliSekarangButton();
        });

        Then("the Produk Digital transaction data will have displayed", () -> {
            bukalapak.produkDigitalPage().validateProductName();
            bukalapak.produkDigitalPage().validateProductPrice();
        });

    }
}
