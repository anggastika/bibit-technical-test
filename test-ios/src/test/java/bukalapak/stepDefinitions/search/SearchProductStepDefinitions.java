package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SearchProductStepDefinitions extends TestInstrument implements En {
    public SearchProductStepDefinitions() {
        When("^user search \"([^\"]*)\" on Home page$", (String keyword) -> {
            bukalapak.homePage().searchByKeyword(keyword);
        });

        And("^user buy the search result product$", () -> {
            try {
                bukalapak.iOSBasePage().tapElement("buy_button_search_result");
                bukalapak.iOSBasePage().tapElement("search_lanjut_bayar_button");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        And("^user click product (.*)$", (String productName) -> {
            bukalapak.searchPage().clickOnProduct(productName);
        });

        When("^user search product as \"([^\"]*)\" on Home page$", (String productName) -> {
            bukalapak.homePage().inputProductName(productName);
        });

        When("^product \"([^\"]*)\" is displayed$", (String productName) -> {
            bukalapak.searchPage().checkDisplayedProduct(productName);
        });

        When("user skip onboarding search result page", () -> {
            bukalapak.searchPage().skipOnboardingSearchResult();
        });

        // PROM section
        When("^user click promoted product \"([^\"]*)\"$", (String productName) -> {
            bukalapak.searchPage().clickOnPromotedProduct(productName);
        });

        Then("^user will see iklan lapak section$", () -> {
            bukalapak.searchPage().verifyIklanLapakAppear();
        });

        Then("^user will see iklan lapak from \"([^\"]*)\" with product \"([^\"]*)\"$", (String username, String productName) -> {
            bukalapak.searchPage().checkIklanLapakSection(username, productName);
        });
        // End PROM section
    }
}
