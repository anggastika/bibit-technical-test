package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class HistoryPageStepDefinitions extends TestInstrument implements En {
    public HistoryPageStepDefinitions() {
        And("^user validate history page view from \"([^\"]*)\"", (String page) -> {
            bukalapak.historyPage().checkUserOnHistoryPage(page);
        });

        And("^user click on history product from \"([^\"]*)\"", (String page) -> {
            bukalapak.historyPage().clickProductHistory(page);
        });

    }
}
