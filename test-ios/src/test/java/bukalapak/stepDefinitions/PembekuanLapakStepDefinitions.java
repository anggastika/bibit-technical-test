package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;

import cucumber.api.java8.En;

public class PembekuanLapakStepDefinitions extends TestInstrument implements En {

    public PembekuanLapakStepDefinitions() {
        Given("user is on pembekuan lapak page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.pembekuanLapakPage().goToPageWithDeeplink(deeplink);
        });

        Given("user is on pembekuan lapak page", () -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().userOnAkunPage();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickPembekuanLapak();
            bukalapak.pembekuanLapakPage().validatePageDisplayed();
        });

        When("user want to see pembekuan lapak FAQ", () -> {
            bukalapak.pembekuanLapakPage().seeFAQ();
        });

        Then("user should be on pembekuan lapak page", () -> {
            bukalapak.pembekuanLapakPage().validatePageDisplayed();
        });

        // freezeStatus -> permanent, temporary
        Then("\"([^\"]*)\" freeze info on pembekuan lapak page is shown", (String freezeStatus) -> {
            bukalapak.pembekuanLapakPage().validateFreezeSection(freezeStatus);
        });

        // buttonShown -> shown, not shown
        Then("unfreeze button on freeze section pembekuan lapak page is \"([^\"]*)\"", (String buttonShown) -> {
            bukalapak.pembekuanLapakPage().validateUnfreezeButton(buttonShown);
        });

        Then("safe from punishment info is shown on pembekuan lapak page", () -> {
            bukalapak.pembekuanLapakPage().validateLapakNotPunished();
            bukalapak.pembekuanLapakPage().validateTimelineLapakNotPunished();
        });

        // stage -> warning-x (1-6) or freeze-x (1-3)
        Then("punishment info on stage \"([^\"]*)\" is shown on pembekuan lapak page", (String stage) -> {
            bukalapak.pembekuanLapakPage().validateLapakPunished(stage);
        });

        // stage -> warning-x (1-6) or freeze-x (1-3)
        Then("punishment timeline should show until latest stage \"([^\"]*)\" on pembekuan lapak page", (String stage) -> {
            bukalapak.pembekuanLapakPage().validateTimelineLapakPunished(stage);
        });

        Then("weekly report shown on pembekuan lapak page should be on normal color", () -> {
            bukalapak.pembekuanLapakPage().validateSectionColor("rejection", "normal", "normal");
            bukalapak.pembekuanLapakPage().validatePercentageColor("rejection", "normal");
            bukalapak.pembekuanLapakPage().validateCountColor("rejection", "normal");
            bukalapak.pembekuanLapakPage().validateSectionColor("abandon", "normal", "normal");
            bukalapak.pembekuanLapakPage().validatePercentageColor("abandon", "normal");
            bukalapak.pembekuanLapakPage().validateCountColor("abandon", "normal");
            bukalapak.pembekuanLapakPage().validateSectionColor("return", "normal", "normal");
            bukalapak.pembekuanLapakPage().validatePercentageColor("return", "normal");
            bukalapak.pembekuanLapakPage().validateCountColor("return", "normal");
        });

        // type -> rejection, abandon, return
        // color -> normal, red
        Then("weekly report section color for \"([^\"]*)\" should be \"([^\"]*)\"", (String type, String color) -> {
            bukalapak.pembekuanLapakPage().validateSectionColor(type, color, color);
        });

        // type -> rejection, abandon, return
        // color -> normal, red
        Then("weekly report count color for \"([^\"]*)\" should be \"([^\"]*)\"", (String type, String color) -> {
            bukalapak.pembekuanLapakPage().validateCountColor(type, color);
        });

        // type -> rejection, abandon, return
        // color -> normal, red
        Then("weekly report percentage color for \"([^\"]*)\" should be \"([^\"]*)\"", (String type, String color) -> {
            bukalapak.pembekuanLapakPage().validatePercentageColor(type, color);
        });

        Then("weekly report shows no previous update record", () -> {
            bukalapak.pembekuanLapakPage().validateNoPreviousUpdateRecord();
        });
    }
}
