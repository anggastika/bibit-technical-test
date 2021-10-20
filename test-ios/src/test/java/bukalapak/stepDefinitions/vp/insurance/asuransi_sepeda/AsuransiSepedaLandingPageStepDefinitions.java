package bukalapak.stepDefinitions.vp.insurance.asuransi_sepeda;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AsuransiSepedaLandingPageStepDefinitions extends TestInstrument implements En {

    public AsuransiSepedaLandingPageStepDefinitions() {

        And("user validate Asuransi Sepeda product detail page", () -> {
            bukalapak.insuranceProductDetailPage().tapOnManfaatTab();
            bukalapak.insuranceProductDetailPage().tapOnCaraBeliTab();
            bukalapak.insuranceProductDetailPage().tapOnKetentuanTab();
            bukalapak.insuranceProductDetailPage().tapOnCaraKlaimTab();
            bukalapak.insuranceProductDetailPage().tapOnTanyaJawabTab();
            bukalapak.insuranceProductDetailPage().tapOnKontakTab();
        });

        And("user buys a bicycle from Asuransi Sepeda product detail page", () -> {
            bukalapak.asuransiSepedaLandingPage().tapOnBeliSepedaButton();
        });
    }
}
