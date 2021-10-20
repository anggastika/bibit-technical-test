package bukalapak.stepDefinitions.search;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;

public class FilterProductStepDefinitions extends TestInstrument implements En {
    public FilterProductStepDefinitions() {

        And("^user select filter \"([^\"]*)\"$", (String filterName) -> {
            bukalapak.filterProductPage().selectFilterProduct(filterName);
        });

        And("^user select filter group \"([^\"]*)\"$", (String groupName) -> {
            bukalapak.filterProductPage().selectFilterGroup(groupName);
        });

        And("^user should be able to click Aktifkan Filter$", () -> {
            bukalapak.filterProductPage().tapTerapkanFilter();
        });

        And("^user should be able to click Simpan Button$", () -> {
            bukalapak.filterProductPage().tapSimpanButton();
        });

        And("^user set (min|max) price to \"([^\"]*)\"$", (String range, String price) -> {
            bukalapak.filterProductPage().setPriceRange(range, price);
        });

        Then("^user is in filter page$", () -> {
            bukalapak.filterProductPage().isInFilterPage();
        });

        And("^user tap on Reset filter button$", () -> {
            bukalapak.filterProductPage().resetFilter();
        });

        And("^user verify \"([^\"]*)\" filter is( not)? applied$", (String filter, String active) -> {
            bukalapak.filterProductPage().verifyFilterSelected(filter, active == null);
        });

        And("user tap on \"([^\"]*)\" and choose \"([^\"]*)\" in filter Dikirim Dari", (String dropdown, String option) -> {
            bukalapak.filterProductPage().tapDikirimDariDropDown(dropdown, option);
        });

        And("^user choose country \"([^\"]*)\"$", (String country) -> {
            bukalapak.filterProductPage().chooseCountry(country);
        });

        And("^user validate service \"([^\"]*)\" is (available|not available) on filter jasa pengiriman$", (String courier, String availability) -> {
            bukalapak.filterProductPage().validateAvailabilityCourier(courier, availability);
        });

        And("^user search and select \"([^\"]*)\"$", (String category) -> {
            bukalapak.filterProductPage().searchAndTapCategory(category);
        });

        And("user close filter page", () -> {
            bukalapak.filterProductPage().closePage();
        });

        And("user tap back button in filter page", () -> {
            bukalapak.filterProductPage().tapBackButton();
        });

        And("^user select category \"([^\"]*)\"$", (String category) -> {
            bukalapak.filterProductPage().selectCategory(category);
        });
    }
}
