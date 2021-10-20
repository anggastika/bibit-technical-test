package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PolaPemasukanStepDefinitions extends TestInstrument implements En {
    public PolaPemasukanStepDefinitions() {
        Then("^user is in \"Pola Pemasukan\" page$", () -> {
            bukalapak.polaPemasukanPage().verifyPage();
        });

        Then("^user (is|is not) shown onboarding for pola pemasukan graph$", (String showStatus) -> {
            bukalapak.polaPemasukanPage().verifyOnboarding(showStatus);
        });

        Then("^user is shown transaction \"([^\"]*)\" checkbox (checked|unchecked) on pola pemasukan page$", (String trxType, String check) -> {
            bukalapak.polaPemasukanPage().verifyCheckbox(trxType, check);
        });

        When("^user click transaction \"([^\"]*)\" checkbox on pola pemasukan page$", (String trxType) -> {
            bukalapak.polaPemasukanPage().clickCheckboxTransactionType(trxType);
        });

        Then("^user is shown instruction to check at least one transaction$", () -> {
            bukalapak.polaPemasukanPage().verifyNoGraphWarning();
        });

        When("^user choose date on pola pemasukan graph$", () -> {
            bukalapak.polaPemasukanPage().clickGraphicDate();
        });

        Then("^user will shown pemasukan detail of \"([^\"]*)\" picked date", (String type) -> {
            bukalapak.polaPemasukanPage().verifyPemasukanDetailPickedDate(type);
        });

        When("^user click latest (?:day|month) on pola pemasukan graph$", () -> {
            bukalapak.polaPemasukanPage().clickLastPointOnGraph();
        });

        Then("^user is shown transaction detail of pola pemasukan$", () -> {
            bukalapak.polaPemasukanPage().verifyPemasukanDetail();
        });

        Then("^user is shown transaction detail of latest (day|month)$", (String period) -> {
            bukalapak.polaPemasukanPage().verifyLatestPeriodOnPemasukanDetail(period);
        });
    }
}
