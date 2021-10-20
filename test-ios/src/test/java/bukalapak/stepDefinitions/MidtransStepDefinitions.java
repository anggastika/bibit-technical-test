package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class MidtransStepDefinitions extends TestInstrument implements En {

    public MidtransStepDefinitions() {

        Given("user is in \"Midtrans\" page", () -> {
            bukalapak.midtransPage().userOnMidtransPage();
        });

        When("^user input otp to add new credit card as (.*)$", (String numberCC) -> {
            bukalapak.midtransPage().inputOTP(numberCC);
        });
    }
}
