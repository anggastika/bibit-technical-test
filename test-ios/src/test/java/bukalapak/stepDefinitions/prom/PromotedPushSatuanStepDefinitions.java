package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushSatuanStepDefinitions extends TestInstrument implements En {

    public PromotedPushSatuanStepDefinitions() {
        Given("user is in \"Promoted Push Barang\" page", () -> {
            bukalapak.promotedPushSatuanPage().userOnPromotedPushBarangPage();
        });

        When("user input bid Promoted \"([^\"]*)\"", (String bid) -> {
            bukalapak.promotedPushSatuanPage().inputBidPerClick(bid);
        });

        When("user set Periode Promoted Push starting today", () -> {
            bukalapak.promotedPushSatuanPage().setPromotedPushStartingDate();
        });

        And("^user (disable|enable) Promoted Product toggle button$", (String state) -> {
            bukalapak.promotedPushSatuanPage().setPromotedPushToggleButton(state);
        });

        Then("^promoted push coachmark is displayed$", () -> {
            bukalapak.promotedPushSatuanPage().verifyCoachmarkDisplayed();
        });

        And("^user continue Promoted Push coachmark$", () -> {
            bukalapak.promotedPushSatuanPage().continueCoachmark();
        });

        And("^user should see \"Tips promosi\" in tab statistic$", () -> {
            bukalapak.promotedPushPage().verifyTipsPromosi();
        });

        When("user click Simpan pengaturan button on Promoted Push Satuan page", () -> {
            bukalapak.promotedPushSatuanPage().tapSimpanPengaturanButton();
        });
    }
}
