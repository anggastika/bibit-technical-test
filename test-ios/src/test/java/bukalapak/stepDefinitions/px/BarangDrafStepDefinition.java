package bukalapak.stepDefinitions.px;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BarangDrafStepDefinition extends TestInstrument implements En {

    public BarangDrafStepDefinition () {

        And("validate there are draft products that have been made before", () -> {
            bukalapak.barangDrafPage().validateProductDraftMatch();
        });

        Then("validate there are no draft products listed", () -> {
            bukalapak.barangDrafPage().validateProductDraftDeleted();
        });

        And("user is in product draf page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.barangDrafPage().userOnPageWithDeeplink(deeplink);
        });

        And("validate product draft deleted successfully", () -> {
            bukalapak.barangDrafPage().validateProductDraftSuccessfullyDeleted();
        });
    }
}
