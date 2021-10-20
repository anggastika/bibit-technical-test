package bukalapak.stepDefinitions.vp.travel.tiketpesawat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TiketPesawatSearchStepDefinitions extends TestInstrument implements En {
    public TiketPesawatSearchStepDefinitions() {
        Then("^user see list of flight schedule$", () -> {
            bukalapak.tiketPesawatSearchPage().validateOnSearchPage();
        });

        And("^user able to filter flight schedule$", () -> {
            bukalapak.tiketPesawatSearchPage().tapOnFilter();
            bukalapak.tiketPesawatSearchPage().getAirlineFilterData();
            bukalapak.tiketPesawatSearchPage().tapOnFirstAirlineFilter();
            bukalapak.tiketPesawatSearchPage().tapOnFilterTerapkanButton();
            bukalapak.tiketPesawatSearchPage().validateOnSearchPage();
            bukalapak.tiketPesawatSearchPage().validateFilterResult();
        });

        And("user filter schedule based on range price between ([^\"]*) and ([^\"]*)", (String minimumPrice, String maximumPrice) -> {
            bukalapak.tiketPesawatSearchPage().tapOnFilter();
            bukalapak.tiketPesawatSearchPage().tapRentangHargaFilter();
            bukalapak.tiketPesawatSearchPage().typeOnMinPriceFilter(minimumPrice);
            bukalapak.tiketPesawatSearchPage().typeOnMaxPriceFilter(maximumPrice);
            bukalapak.tiketPesawatSearchPage().tapOnFilterTerapkanButton();
        });

        And("^user able to sort flight schedule$", () -> {
            bukalapak.tiketPesawatSearchPage().tapOnSort();
            bukalapak.tiketPesawatSearchPage().tapOnCriteriaOrder("termurah");
            bukalapak.tiketPesawatSearchPage().tapOnSortingTerapkanButton();
            bukalapak.tiketPesawatSearchPage().validateSchedulePriceSorted();
        });

        When("user choose departure flight schedule", () -> {
            bukalapak.tiketPesawatSearchPage().validateSearchDone("departure");
            bukalapak.tiketPesawatSearchPage().tapOnFirstSchedule("departure");
        });

        When("^user choose return flight schedule$", () -> {
            bukalapak.tiketPesawatSearchPage().validateSearchDone("return");
            bukalapak.tiketPesawatSearchPage().tapOnFirstSchedule("return");
        });

        Then("^pop up verification account will have displayed$", () -> {
            bukalapak.tiketPesawatSearchPage().validatePopupNotConfirmEmail();
        });

        When("^user goes back from flight search result page to landing page$", () -> {
            bukalapak.tiketPesawatSearchPage().tapOnBackButton();
        });

        Then("^the login page from tiket pesawat will have displayed$", () -> {
            bukalapak.iOSBasePage().tapElement("akun_onboarding_login_button");
            bukalapak.loginPage().userOnLoginPage();
        });

        Then("the flight schedule will have displayed based on range price", () -> {
            bukalapak.tiketPesawatSearchPage().validateOnFilterResultPage();
            bukalapak.tiketPesawatSearchPage().validateFilterResultPrice();
        });
    }
}
