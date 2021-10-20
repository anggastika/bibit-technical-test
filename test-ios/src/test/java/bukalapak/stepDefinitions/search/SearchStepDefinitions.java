package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class SearchStepDefinitions extends TestInstrument implements En {

    public SearchStepDefinitions() {
        And("user search product with \"([^\"]*)\" keyword", (String arg0) -> {
            bukalapak.searchPage().searchProduct(arg0);
        });

        And("user tap pelapak tab", () -> {
            bukalapak.searchPage().tapSellerTab();
        });

        And("user tap filter button", () -> {
            bukalapak.searchPage().tapFilterButton();
        });

        And("user tap top seller filter button", () -> {
            bukalapak.searchPage().tapTopSellerFilterButton();
        });

        And("user tap premium seller filter button", () -> {
            bukalapak.searchPage().tapPremiumSellerFilterButton();
        });

        And("user tap terapkan filter button", () -> {
            bukalapak.searchPage().tapTerapkanFilterButton();
        });

        And("user should see seller on seller listing", () -> {
            bukalapak.searchPage().checkSellerOnSellerListing();
        });

        Then("^user is shown seller result (.+) as Super Seller$", (String index) -> {
            bukalapak.searchPage().verifyBadgeSuperSellerOnSellerResult(index);
        });

        Then("^user is shown seller result (.+) as Lapak Terbaik$", (String index) -> {
            bukalapak.searchPage().verifyBadgeLapakTerbaikOnSellerResult(index);
        });

        Then("^user is shown seller result (.+) as Premium$", (String index) -> {
            bukalapak.searchPage().verifyBadgePremiumOnSellerResult(index);
        });

        Then("^user tap pelapak filter button$", () -> {
            bukalapak.searchPage().tapPelapakFilterButton();
        });

        When("^user tap list view mode for search product$", () -> {
            bukalapak.searchPage().tapListViewButton();
        });

        Then("^user is shown product result (\\d+) as product of Super Seller on (grid|list) view$", (Integer index, String viewType) -> {
            bukalapak.searchPage().verifyBadgeSuperSellerOnProductResult(index, viewType);
        });

        Then("^user is shown product result (\\d+) as product of Lapak Terbaik on (grid|list) view$", (Integer index, String viewType) -> {
            bukalapak.searchPage().verifyBadgeLapakTerbaikOnProductResult(index, viewType);
        });

        When("^user tap simpan button on filter product$", () -> {
            bukalapak.searchPage().tapSimpanButton();
        });

        When("^user tap terapkan button$", () -> {
            bukalapak.searchPage().tapTerapkanButton();
        });

        Then("^user is shown (\\d+) seller results as Lapak Terbaik$", (Integer total) -> {
            for (int i = 1; i <= total; i++) {
                bukalapak.searchPage().verifyBadgeLapakTerbaikOnSellerResult(Integer.toString(i));
            }
        });

        Then("^user is shown (\\d+) seller results as Super Seller$", (Integer total) -> {
            for (int i = 1; i <= total; i++) {
                bukalapak.searchPage().verifyBadgeSuperSellerOnSellerResult(Integer.toString(i));
            }
        });

        Given("user is in \"search\" page", () -> {
            bukalapak.searchPage().userOnSearchPage();
        });

        Then("^user is in search result page$", () -> {
            bukalapak.searchPage().validateQuickFilterTab();
            bukalapak.searchPage().validateProductResult();
        });

        When("^user tap \"([^\"]*)\" quickfilter$", (String filter) -> {
            bukalapak.searchPage().tapQuickFilter(filter);
        });

        Then("^user should (not )?see select address modal$", (String flag) -> {
            bukalapak.searchPage().validateAddressModal(flag);
        });

        And("^user should (not )?see saved address option$", (String flag) -> {
            bukalapak.searchPage().validateSavedAddressOption(flag);
        });

        And("^user should see lokasi sekarang option$", () -> {
            bukalapak.searchPage().validateLokasiSekarangOption();
        });

        And("^user tap saved address on select location modal$", () -> {
            bukalapak.searchPage().tapSavedAddress();
        });

        And("^user should see product list from bukamart seller in selected location$", () -> {
            bukalapak.searchPage().validateLocationSeller();
        });

        And("^user close select address modal$", () -> {
            bukalapak.searchPage().tapCloseAddressModal();
        });

        And("^user should see bukamart product list based on search keyword$", () -> {
            bukalapak.searchPage().validateProductList();
        });

        And("^user should (not )?see value on kirim dari filter$", (String arg0) -> {
            bukalapak.searchPage().validateKirimDariFilter(arg0 == null);
        });

        And("^user should see super seller icon in the product list$", () -> {
            bukalapak.searchPage().verifySuperSellerIcon();
        });

        Then("^user verify onboarding section on top of product listing$", () -> {
            bukalapak.searchPage().verifyOnboardingModal();
        });

        Then("^user verify onboarding highlight on \"([^\"]*)\"$", (String onboardingStep) -> {
            bukalapak.searchPage().verifyOnboardingTextModal(onboardingStep);
        });

        When("^user tap \"([^\"]*)\" button at onboarding modal$", (String buttonName) -> {
            bukalapak.searchPage().tapOnboardingButton(buttonName);
        });

    }
}
