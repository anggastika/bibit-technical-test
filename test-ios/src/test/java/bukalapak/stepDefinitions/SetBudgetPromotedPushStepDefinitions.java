package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SetBudgetPromotedPushStepDefinitions extends TestInstrument implements En {

    public SetBudgetPromotedPushStepDefinitions() {
        When("user input nominal budget \"([^\"]*)\" on atur budget promoted push page", (String nominalBudget) -> {
            bukalapak.setBudgetPromotedPushPage().inputNominalBudget(nominalBudget);
        });
    }
}
