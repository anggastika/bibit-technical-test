package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchPelapakStepDefinitions extends TestInstrument implements En {
    private final static Logger LOGGER = LogManager.getLogger(SearchPelapakStepDefinitions.class);

    public SearchPelapakStepDefinitions() {

        And("user search \"([^\"]*)\" pelapak in \"([^\"]*)\" page", (String arg0, String arg1) -> {
            ChoiceSelector.of(arg1)
                    .when("home", () -> bukalapak.homePage().searchPelapakInHomePage(arg0))
                    .orElse(() -> LOGGER.info("Your screen is not implemented yet: " + arg1));
            bukalapak.searchPage().goToPelapakTab();
        });

        Given("user add 1st item from seller to cart", () -> {
            bukalapak.searchPage().addToCart();
        });

        And("user click \"([^\"]*)\" for pelapak", (String arg0) -> {
            bukalapak.searchPage().clickPelapak(arg0);
        });

        And("^user should be redirect to pelapak page$", () -> {
            bukalapak.searchPage().checkPelapakName();
        });

        When("^user search merchant \"([^\"]*)\" from homepage$", (String sellerName) -> {
            bukalapak.searchPage().searchPelapakFromHome(sellerName);
        });

        Then("^user should redirect to seller listing page$", () -> {
            bukalapak.sellerListingPage().verifyInSellerListingPage();
        });

        And("^user tap (filter|sort) button in seller listing page$", (String type) -> {
            bukalapak.sellerListingPage().tapFilterBtn(type);
        });

        And("^user tap seller card position \"([^\"]*)\"$", (Integer position) -> {
            bukalapak.sellerListingPage().tapSellerCard(position);
        });

        And("^user tap \"([^\"]*)\" button on seller card position \"([^\"]*)\"$", (String element, Integer position) -> {
            bukalapak.sellerListingPage().tapCardButton(element, position);
        });

        And("^user select filter \"([^\"]*)\" in seller listing$", (String filter) -> {
            bukalapak.sellerListingPage().selectFilter(filter);
        });

        And("^user verify seller card position \"([^\"]*)\" has \"([^\"]*)\" (badge|label)$", (Integer index, String value, String type) -> {
            bukalapak.sellerListingPage().verifyCardComponent(index, value, type);
        });

        Then("^user verify filter \"([^\"]*)\" is applied in seller listing page$", (String filter) -> {
            bukalapak.sellerListingPage().verifyFilterActive(filter);
        });

        And("^user tap on \"([^\"]*)\" and choose \"([^\"]*)\" in seller listing page$", (String dropdown, String option) -> {
            bukalapak.filterProductPage().tapDikirimDariDropDown(dropdown, option);
        });

        And("^user sort seller by (Terlaris|Relevansi|Terdekat|Lokasi saat ini)$", (String sortBy) -> {
            bukalapak.sellerListingPage().sortBy(sortBy);
        });

        Then("^user verify sort by (Terlaris|Relevansi|Terdekat|Lokasi saat ini) is active$", (String sortBy) -> {
            bukalapak.sellerListingPage().verifySortActive(sortBy);
        });
    }
}