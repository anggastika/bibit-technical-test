package bukalapak.stepDefinitions.vp.travel;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import static junit.framework.TestCase.fail;

public class HotelSearchStepDefinitions extends TestInstrument implements En {
    public HotelSearchStepDefinitions() {
        And("^hotel search result is displayed with correct information$", () -> {
            bukalapak.travelHotelSearchResultPage().validateHotelListData();
        });

        When("^user sort hotel by \"([^\"]*)\"$", (String sortType) -> {
            bukalapak.travelHotelSearchResultPage().tapOnUrutkan();
            bukalapak.travelHotelSearchResultPage().tapOnButton(sortType.toLowerCase());
            bukalapak.travelHotelSearchResultPage().tapOnTerapkan();
        });

        When("^When user filter hotel by \"([^\"]*)\" with filter criteria \"([^\"]*)\"$",
                (String filterType, String criteria) -> {
            bukalapak.travelHotelSearchResultPage().tapOnFilter();
            if (filterType.toLowerCase().equals("bintang hotel")) {
                bukalapak.travelHotelSearchResultPage().tapOnBintangHotel();
                bukalapak.travelHotelSearchResultPage().tapOnFilterCriteria(filterType, criteria);
            } else {
                fail(String.format("filter type is not supported yet"));
            }
            bukalapak.travelHotelSearchResultPage().tapOnTerapkan();
        });

        And("^user select the hotel$", () -> {
            bukalapak.travelHotelSearchResultPage().validateHotelListData();
            bukalapak.travelHotelSearchResultPage().tapOnChooseHotel();
        });
    }
}
