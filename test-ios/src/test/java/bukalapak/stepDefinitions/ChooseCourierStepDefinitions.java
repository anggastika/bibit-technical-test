package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ChooseCourierStepDefinitions extends TestInstrument implements En {

    public ChooseCourierStepDefinitions() {
        Given("user is in \"Pilih Kurir\" page", () -> {
            bukalapak.chooseCourierPage().userOnChooseCourierPage();
        });

        And("user select a \"([^\"]*)\" and \"([^\"]*)\" as courier", (String arg0, String arg1) -> {
            bukalapak.chooseCourierPage().chooseAllKurir(arg0, arg1);
        });

        Then("user validate courier \"([^\"]*)\" with condition \"([^\"]*)\"", (String arg0, String arg1) -> {
            bukalapak.chooseCourierPage().validateCourierOnDemandNotSupported(arg0, arg1);
        });

        Then("user validate courier \"([^\"]*)\" with condition \"([^\"]*)\" and \"([^\"]*)\" as ETA", (String arg0, String arg1, String arg2) -> {
            bukalapak.chooseCourierPage().validateCourierOnDemandNotSupported(arg0, arg1, arg2);
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
        });

        Then("^validate recommendation courier \"([^\"]*)\" section is (not )?displayed$", (String courier, String isReco) -> {
            bukalapak.chooseCourierPage().validateCourierRecoSectionDisplayed(courier, isReco == null);
        });

        Then("validate \"([^\"]*)\" section is displayed and courier \"([^\"]*)\" is part of the section", (String courierSection, String courierName) -> {
            bukalapak.chooseCourierPage().validationCourierSection(courierSection, courierName);
        });

        Then("^validate \"([^\"]*)\" has (not )?tag \"([^\"]*)\"$", (String courier, String hasTag, String tag) -> {
            bukalapak.chooseCourierPage().validationTagTersediaCOD(courier, hasTag, tag);
        });

        Then("^validate error message ambil sendiri \"([^\"]*)\"$", (String errorMsg) -> {
            bukalapak.chooseCourierPage().validateErrorMessageAmbilSendiri(errorMsg);
        });
    }
}
