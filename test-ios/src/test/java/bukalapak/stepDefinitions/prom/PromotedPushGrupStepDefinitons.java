package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushGrupStepDefinitons extends TestInstrument implements En {

    public PromotedPushGrupStepDefinitons() {
        And("^user is in \"Promoted Push Grup\" page$", () -> {
            bukalapak.promotedPushBarangGrupPage().userOnPromotedGrupPage();
        });

        And("^user input (.*) as harga per klik barang$", (String harga) -> {
            bukalapak.promotedPushBarangGrupPage().inputHargaPerKlikBarang(harga);
        });

        And("^user input (.*) as batas budget on promoted push grup$", (String budget) -> {
            bukalapak.promotedPushBarangGrupPage().inputBatasBudget(budget);
        });

        And("user validate error message below minimum harga per klik on Promoted Push Group", () -> {
            bukalapak.promotedPushBarangGrupPage().validateErrorMessageTopupGrup();
        });

        And("user validate error message below minimum batas budget on Promoted Push Group", () -> {
            bukalapak.promotedPushBarangGrupPage().validateErrorMessageTopupBatasBudgetGrup();
        });

        When("user input \"([^\"]*)\" as the title of Promoted Group", (String promotedGrupTitle) -> {
            bukalapak.promotedPushBarangGrupPage().inputPromotedGroupTitle(promotedGrupTitle);
        });

        When("user select option \"([^\"]*)\" as input \"([^\"]*)\" for campaign limit", (String optionType, String option) -> {
            bukalapak.promotedPushBarangGrupPage().selectPromotedPushLimit(optionType, option);
        });

        When("user click Simpan Pengaturan button for Promoted Group", () -> {
            bukalapak.promotedPushBarangGrupPage().tapSimpanPengaturanButton();
        });

        Then("max error campaign message on Promoted Push Grup page is displayed", () -> {
            bukalapak.promotedPushBarangGrupPage().verifyMaxCampaignError();
        });
    }
}
