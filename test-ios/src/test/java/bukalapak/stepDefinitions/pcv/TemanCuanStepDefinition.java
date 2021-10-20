package bukalapak.stepDefinitions.pcv;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TemanCuanStepDefinition extends TestInstrument implements En {

    public TemanCuanStepDefinition() {
        And("^user verify Teman Cuan page$", () -> {
            bukalapak.temanCuanPage().goToPage();
            bukalapak.temanCuanPage().userOnTemanCuanPage();
        });
    }
}
