package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaRumahStepDefinitions extends TestInstrument implements En {
    public BukaRumahStepDefinitions() {
        Then("the BukaRumah landing page will have displayed", () -> {
            bukalapak.bukaRumahLandingPage().validateOnBukaRumahLandingPage();
        });

        When("user chooses spesific area for search property", () -> {
            bukalapak.bukaRumahLandingPage().tapOnTipePropertyDropdown();
            bukalapak.bukaRumahLandingPage().chooseCityArea(Integer.valueOf(dotenv.get("BUKA_RUMAH_INDEX_AREA")));
        });

        Then("the property list with spesific area will have displayed", () -> {
            bukalapak.bukaRumahPropertyApplyPage().validateListPropertyWithArea();
        });

        When("user choose a property on property list", () -> {
            bukalapak.bukaRumahPropertyApplyPage().chooseTopPropertyOnListProperty();
        });

        Then("the detail information of a property will have displayed", () -> {
            bukalapak.bukaRumahPropertyApplyPage().validateDetailPropertyData();
        });

        When("user confirm to submit KPR", () -> {
            bukalapak.bukaRumahPropertyApplyPage().chooseTopPropertyOnListProperty();
            bukalapak.bukaRumahPropertyApplyPage().validateDetailPropertyData();
            bukalapak.bukaRumahPropertyApplyPage().tapOnAjukanKPRButton();
            bukalapak.bukaRumahPropertyApplyPage().tapOnLanjutkanIsiFormButton();
        });

        Then("the user will be redirected to the BukaRumah application form", () -> {
            bukalapak.bukaRumahPropertyApplyPage().validateFormKPR();
        });

        When("user tap on BukaRumah history page", () -> {
            bukalapak.bukaRumahLandingPage().tapOnRiwayatButton();
        });

        Then("the application history for BukaRumah will not have displayed", () -> {
            bukalapak.bukaRumahPropertyApplyPage().validateRiwayatisEmptyMessage();
        });
    }
}
