package bukalapak.stepDefinitions.px;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CreateEtalaseStepDefinition extends TestInstrument implements En {

    public CreateEtalaseStepDefinition(){
        Then("^user is in \"Etalase\" page$", () -> {
            bukalapak.createEtalasePage().verifyPage();
        });

        When("user start set etalase for lapak", () -> {
            bukalapak.createEtalasePage().startSetEtalase();
        });

        When("user create new etalase", () -> {
            bukalapak.createEtalasePage().createNewEtalase();
        });

        Then("verify new etalase created successfully", () -> {
            bukalapak.createEtalasePage().verifyNewEtalase();
        });

        When("user update exist etalase", () -> {
            bukalapak.createEtalasePage().updateExistEtalase();
        });

        Then("verify etalase updated successfully", () -> {
            bukalapak.createEtalasePage().verifyUpdatedEtalase();
        });

        When("user delete new etalase from list", () -> {
            bukalapak.createEtalasePage().deleteEtalase();
        });

        Then("verify etalase deleted successfully and etalase empty", () -> {
            bukalapak.createEtalasePage().verifyListEtalaseEmpty();
        });

        Then("user should see pop up offering super seller", () -> {
            bukalapak.createEtalasePage().offeringSuperSellerPopUp();
        });
    }
}
