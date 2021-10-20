package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DiscoverStepDefinitions extends TestInstrument implements En {
    public DiscoverStepDefinitions() {
        And("user allow all discover onboarding", () -> {
            bukalapak.discoverPage().clickOnboardingDiscover();
        });

        And("user select \"([^\"]*)\" as location", (String location) -> {
            bukalapak.discoverPage().searchLocationDiscover(location);
        });

        And("^user able to see panel \"([^\"]*)\"$", (String panel) -> {
            bukalapak.discoverPage().isPanelExist(panel);
        });

        And("user click on recommendation by \"([^\"]*)\" subtitle", (String recoType) -> {
            bukalapak.discoverPage().clickRecoSubtitle(recoType);
        });

        And("user click on product at \"([^\"]*)\" panel", (String panel) -> {
            bukalapak.discoverPage().clickProduct(panel);
        });

        And("user click view more \"([^\"]*)\"", (String panel) -> {
            bukalapak.discoverPage().clickViewMoreButton(panel);
        });

        And("user validate view more \"([^\"]*)\" page", (String panel) -> {
            bukalapak.discoverPage().isOnViewMore(panel);
        });

        When("user navigate to \"discover\" page", () -> {
            bukalapak.discoverPage().clickDiscoverMenu();
        });
    }
}
