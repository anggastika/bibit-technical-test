package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PromotedPushStepDefinitions extends TestInstrument implements En {

    public PromotedPushStepDefinitions() {
        Then("user is in onboarding promoted push \"([^\"]*)\" page", (String page) -> {
            bukalapak.promotedPushPage().userOnOnboardingPromotedPushPage(page);
        });

        When("user taps on Promoted Push onboarding icon", () -> {
            bukalapak.promotedPushPage().tapPromotedPushIcon();
        });

        When("^user tap \"([^\"]*)\" tab on Promoted Push page$", (String tabName) -> {
            bukalapak.promotedPushPage().tapPromotedPushTab(tabName);
        });

        When("user see \"([^\"]*)\" on Promoted Push page", (String nominalType) -> {
            bukalapak.promotedPushPage().setPromotedValue(nominalType);
        });

        Then("user validate \"([^\"]*)\" on Promoted Push page, after add budget", (String field) -> {
            bukalapak.promotedPushPage().userValidateNominal(field);
        });

        Then("^user validate Promoted Push Budget Harian Status as (.*)$", (String state) -> {
            bukalapak.promotedPushPage().validatePromotedPushBudgetHarian(state);
        });

        Then("^user see Promoted Push Budget Saldo Pinjaman (Tidak )?Aktif$", (String state) -> {
            bukalapak.promotedPushPage().validatePromotedPushBudgetHarianLoan(state != null);
        });

        And("^user search product as \"([^\"]*)\" on Promoted Push page$", (String productName) -> {
            bukalapak.promotedPushPage().searchProduct(productName);
        });

        And("^user click (Aktifkan|Atur) button on product \"([^\"]*)\"$", (String button, String productName) -> {
            bukalapak.promotedPushPage().clickPromotedProductActionButton(button, productName);
        });

        Given("user is in \"Promoted Push\" page", () -> {
            bukalapak.promotedPushEndFreeTrialPage().skipEndFreeTrialPage();
            bukalapak.promotedPushPage().userOnPromotedPushPage();
        });

        Then("^user see \"([^\"]*)\" as (not )?active promoted push product$", (String productName, String status) -> {
            bukalapak.promotedPushPage().checkProductStatus(productName, status != null);
        });

        And("user see \"([^\"]*)\" with active bid value \"([^\"]*)\"", (String productName, String bidValue) -> {
            bukalapak.promotedPushPage().checkBidValue(productName, bidValue);
        });

        And("^\"([^\"]*)\" checks the budget after \"([^\"]*)\" by (\\d+)$", (String credentialUser, String action, Integer cost) -> {
            bukalapak.promotedPushPage().validatePromotedPushBudget(credentialUser, action, cost);
        });

        Then("promoted push onboarding is displayed in Promoted Push Landing page", () -> {
            bukalapak.promotedPushPage().verifyOnboardingDisplayed();
        });

        Then("promoted push onboarding is disappeared on landing page", () -> {
            bukalapak.promotedPushPage().verifyPromotedPushOnboardingDisappeared();
        });

        //Promoted satuan
        And("user click tambah promosi button element", () -> {
            bukalapak.promotedPushPage().clickTambahPromosiButton();
        });

        And("^user is in \"Promoted push satuan\" page$", () -> {
            bukalapak.promotedPushSatuanPage().userOnPromotedPushSatuanPage();
        });

        And("^user input (.*) as harga per klik$", (String nominal) -> {
            bukalapak.promotedPushSatuanPage().inputHargaPerKlik(nominal);
        });

        And("user validate error message below minimum harga per klik", () -> {
            bukalapak.promotedPushSatuanPage().validateErrorMessageTopup();
        });

        Then("user should be able to see \"([^\"]*)\" with \"([^\"]*)\" state and tagged as \"([^\"]*)\"", (String title, String state, String totalProducts) -> {
            bukalapak.promotedPushPage().verifyPromotedGroupCampaign(title, state, totalProducts);
        });
        // End Promoted Satuan

        // Promoted Group
        Then("user taps Promoted Push Grup Campaign with title \"([^\"]*)\"", (String title) -> {
            bukalapak.promotedPushPage().tapPromotedPushGroup(title);
        });

        Then("Promoted Grup with title \"([^\"]*)\" is deleted", (String title) -> {
            bukalapak.promotedPushPage().verifyDeletedCampaign(title);
        });
        // End Promoted Group

        // Statistic section
        And("^user tap on (7|30) hari period statistic$", (String days) -> {
            bukalapak.promotedPushPage().tapOnPeriodStatistic(days);
        });

        And("^user verify empty data statistic (7|30) hari$", (String period) -> {
            bukalapak.promotedPushPage().validateEmptyValueStatistic(period);
        });

        Then("^user is able to see promoted push statistic data for last (7|30) days$", (String period) -> {
            bukalapak.promotedPushPage().checkPromotedStatisticBasedOnPeriode(period);
        });

        Then("user taps Riwayat Statistic Promoted Push button", () -> {
            bukalapak.promotedPushPage().tapRiwayatStatisticButton();
        });
        // End Statistic section

        // Error region
        Then("max error campaign message is displayed", () -> {
            bukalapak.promotedPushPage().verifyErrorMaxCampaign();
        });

        Then("promoted product can not be found state is displayed", () -> {
            bukalapak.promotedPushPage().verifyProductNotFoundState();
        });
        // End of error region
    }
}
