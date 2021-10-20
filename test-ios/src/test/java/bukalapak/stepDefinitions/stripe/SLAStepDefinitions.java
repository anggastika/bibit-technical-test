package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import bukalapak.data.STRIPEData;
import cucumber.api.java8.En;

public class SLAStepDefinitions extends TestInstrument implements En {

    /**
     * NOTE : Please simplify the steps below which can be made as one line only in each Page Object class.
     * Since those steps doesn't need parameter(s) from steps.
     */
    public SLAStepDefinitions() {

        Given("user is in SLA page", () -> {
            bukalapak.homePage().isOnHomePage();
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickWaktuProses();
            bukalapak.slaPage().userOnSLAPage();
        });

        Given("user is in SLA page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.slaPage().userOnSLAPageWithDeeplink(deeplink);
        });

        When("user set SLA for \"([^\"]*)\"", (String slaType) -> {
            bukalapak.slaPage().setSLA(slaType);
            bukalapak.slaPage().saveSLA();
            bukalapak.slaPage().verifySaveSLASuccess();
        });

        And("user tap on Ubah Produk on product detail", () -> {
            bukalapak.barangDijualPage().clickOpsiProduk();
            bukalapak.barangDijualPage().goToUbahProduk();
        });

        And("user set SLA for Custom", () -> {
            bukalapak.slaPage().choosePreorderSLA(STRIPEData.getSLADuration());
            bukalapak.slaPage().typeOnWaktuProsesPesananText(5);
        });

        And("user set SLA for Pre-Order", () -> {
            bukalapak.slaPage().choosePreorderSLA(STRIPEData.getSLADuration());
            bukalapak.slaPage().typeOnWaktuProsesPesananText(17);
        });

        Then("SLA custom match with input user", () -> {
            bukalapak.productDetailsPage().validationBatasWaktuPengirimanCustom(STRIPEData.getSLADuration());
            bukalapak.productDetailsPage().userOnProductDetailPage();
        });

        Then("SLA instan match with input user", () -> {
            bukalapak.productDetailsPage().validationBatasWaktuPengirimanInstan(STRIPEData.getSLADuration());
            bukalapak.productDetailsPage().userOnProductDetailPage();
        });

        Then("SLA match with input user as true", () -> {
            bukalapak.productDetailsPage().validationBatasWaktuPengirimanPreorder();
            bukalapak.productDetailsPage().userOnProductDetailPage();
        });

        Then("SLA pre-order match with input user", () -> {
            bukalapak.productDetailsPage().validationBatasWaktuPengirimanPreorder();
            bukalapak.productDetailsPage().userOnProductDetailPage();
        });

        And("user set SLA for Instan", () -> {
            bukalapak.slaPage().chooseInstantSLA(STRIPEData.getSLADuration());
            bukalapak.slaPage().typeOnWaktuProsesPesananText(3);
        });

        And("user set SLA for Reguler", () -> {
            bukalapak.slaPage().chooseRegularSLA();
            bukalapak.slaPage().saveSLA();
        });

        And("user click Simpan Perubahan", () -> {
            bukalapak.slaPage().saveSLA();
            bukalapak.slaPage().saveChanged();
        });

        And("user click Back in page", () -> {
            bukalapak.slaPage().clickBackButton();
        });

    }
}
