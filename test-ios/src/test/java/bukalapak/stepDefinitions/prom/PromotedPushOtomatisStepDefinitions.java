package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushOtomatisStepDefinitions extends TestInstrument implements En {

    public PromotedPushOtomatisStepDefinitions() {
        Given("user is in \"Tambah Promoted Push\" page", () -> {
            bukalapak.promotedPushOtomatisPage().userOnTambahPromotedPush();
        });

        When("^user click tambah on promoted push otomatis tab", () -> {
            bukalapak.promotedPushOtomatisPage().tambahPromotedOtomatis();
        });

        When("user fill nama promosi with \"([^\"]*)\"", (String promotionName) -> {
            bukalapak.promotedPushOtomatisPage().inputPromotionName(promotionName);
        });

        When("user fill harga per klik with \"([^\"]*)\"", (String bid) -> {
            bukalapak.promotedPushOtomatisPage().inputPricePerClick(bid);
        });

        When("user fill periode with \"([^\"]*)\"", (String periode) -> {
            bukalapak.promotedPushOtomatisPage().choosePeriode(periode);
        });

        And("user selects date until (\\d+) date from now", (Integer periode) -> {
            bukalapak.promotedPushOtomatisPage().chooseEndDate(periode);
        });

        When("user fill maksimal budget with \"([^\"]*)\"", (String maxBudget) -> {
            bukalapak.promotedPushOtomatisPage().chooseMaxBudget(maxBudget);
        });

        Then("user will see promotion \"([^\"]*)\"", (String promotionName) -> {
            bukalapak.promotedPushOtomatisPage().checkCreatedPromotion(promotionName);
        });

        And("^user tap on promotion \"([^\"]*)\"$", (String title) -> {
            bukalapak.promotedPushOtomatisPage().tapOnPromotionList(title);
        });

        And("^user will not see promotion \"([^\"]*)\"$", (String title) -> {
            bukalapak.promotedPushOtomatisPage().promotionSuccesfullyDeleted(title);
        });

        And("^user is on empty state Promosi Otomatis tab$", () -> {
            bukalapak.promotedPushOtomatisPage().userOnEmptyStatePromosiOtomatis();
        });

        And("^user update harga per klik from \"([^\"]*)\" to \"([^\"]*)\"$", (String oldBid, String newBid) -> {
            bukalapak.promotedPushOtomatisPage().inputUpdateBidValue(newBid);
        });

        And("^user update maksimal budget from \"([^\"]*)\" to \"([^\"]*)\"$", (String oldMaxBudget, String newMaxBudget) -> {
            bukalapak.promotedPushOtomatisPage().inputUpdateMaxBudget(newMaxBudget);
        });

        Then("^user will see the price per click change to \"([^\"]*)\"$", (String newBidValue) -> {
            bukalapak.promotedPushOtomatisPage().verifyBidValueUpdated(newBidValue);
        });

        Then("^user will see the maksimal budget change to \"([^\"]*)\"$", (String newMaxBudget) -> {
            bukalapak.promotedPushOtomatisPage().verifyMaxBudgetUpdated(newMaxBudget);
        });

        And("^user will see promotion \"([^\"]*)\" is \"([^\"]*)\"$", (String title, String status) -> {
            bukalapak.promotedPushOtomatisPage().verifyPromosiOtomatisStatus(title, status);
        });

        And("^user verify \"([^\"]*)\" as nama barang promosi$", (String name) -> {
            bukalapak.promotedPushOtomatisPage().verifyNamaBarang(name);
        });

        When("^user click tambah promosi otomatis button element$", () -> {
            bukalapak.promotedPushOtomatisPage().clickTambahPromosiButton();
        });

        And("^user click tambah button element$", () -> {
            bukalapak.promotedPushOtomatisPage().clickTambahButton();
        });

        Then("^user verify popup modal upgrade to premium seller$", () -> {
            bukalapak.promotedPushOtomatisPage().verifyPopupModalUpgradePremiumSeller();
        });

        And("^user deleted Promoted Push campaign$", () -> {
            bukalapak.promotedPushOtomatisPage().deletePromotedPushCampaign();
        });

        And("^user selects \"([^\"]*)\" for Promoted Push Otomatis campaign$", (String product) -> {
            bukalapak.promotedPushOtomatisPage().selectProduct(product);
        });

        And("^user click Tambah Promosi button for campaign$", () -> {
            bukalapak.promotedPushOtomatisPage().clickTambahPromosiButton();
        });
    }
}
