package bukalapak.stepDefinitions.vp.insurance.bukaAsuransi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.junit.Assert;

public class BukaAsuransiStepsDefinitions extends TestInstrument implements En {
    public BukaAsuransiStepsDefinitions() {
        Then("the BukaAsuransi landing page will have displayed", () -> {
            bukalapak.bukaAsuransiPage().validateOnPage();
        });

        Then("^the \"([^\"]*)\" section BukaAsuransi landing page will have displayed$", (String section) -> {
            bukalapak.bukaAsuransiPage().validateSection(section);
        });

        When("user taps promo banner on the BukaAsuransi page", () -> {
            bukalapak.bukaAsuransiPage().tapOnPromoBanner();
        });

        When("^user (?:had been on|validate) menu \"([^\"]*)\" on pilihan asuransi section$", (String insuranceName) -> {
            bukalapak.bukaAsuransiPage().validateAsuransiListMenu(insuranceName);
        });

        When("^user taps menu \"([^\"]*)\" on pilihan asuransi section$", (String insuranceName) -> {
            bukalapak.bukaAsuransiPage().tapInsuranceListMenu(insuranceName);
        });

        Then("^the \"([^\"]*)\" menu link will be displayed$", (String insuranceName) -> {
            switch (insuranceName) {
                case "Asuransi Motor":
                    bukalapak.asuransiMotorPage().validateOnPage();
                    break;
                case "Asuransi Pengiriman":
                case "Asuransi Produk":
                case "Asuransi Retur":
                case "Asuransi Perjalanan":
                    bukalapak.bukaAsuransiPage().validateInsuranceProductDetailPage(insuranceName);
                    break;
                case "Asuransi Bepergian":
                    bukalapak.asuransiBepergianLandingPage().validateOnPage();
                    break;
                case "Asuransi Kesehatan":
                case "Asuransi Tambahan":
                    break;
                case "Asuransi Mobil":
                    bukalapak.leadGeneratorLandingPage().validateOnPage("Asuransi Mobil");
                    break;
                case "Asuransi Rawat Jalan":
                    bukalapak.leadGeneratorLandingPage().validateOnPage("Asuransi Rawat Jalan");
                    break;
                case "Asuransi Sepeda":
                    bukalapak.asuransiSepedaLandingPage().validateOnPage();
                    break;
                default:
                    Assert.fail(insuranceName + "Wrong insurance name");
                    break;
            }
        });

        When("user taps on panduan cerdas asuransi button", () -> {
            bukalapak.bukaAsuransiPage().tapOnPanduanCerdasAsuransi();
        });

        Then("the faq BukaAsuransi page will have displayed", () -> {
            bukalapak.bukaAsuransiPage().validateFaqPage();
        });

        Then("the promo link page will have displayed", () -> {
            bukalapak.bukaAsuransiPage().validatePromoLink();
        });
    }
}
