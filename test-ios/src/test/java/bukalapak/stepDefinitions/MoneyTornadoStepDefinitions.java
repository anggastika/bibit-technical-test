package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MoneyTornadoStepDefinitions extends TestInstrument implements En {

    public MoneyTornadoStepDefinitions() {

        Given("user is in \"money_tornado\" page", () -> {
            bukalapak.moneyTornadoPage().userOnMoneyTornadoPage();
        });
    }
}
