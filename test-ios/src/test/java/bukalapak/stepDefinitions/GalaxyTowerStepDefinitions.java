package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class GalaxyTowerStepDefinitions extends TestInstrument implements En {

    public GalaxyTowerStepDefinitions() {

        Given("user is in \"galaxy_tower\" page", () -> {
            bukalapak.galaxyTowerPage().userOnGalaxyTowerPage();
        });
    }
}
