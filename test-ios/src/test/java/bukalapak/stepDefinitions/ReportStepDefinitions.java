package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.STRIPEData;

import cucumber.api.java8.En;

public class ReportStepDefinitions extends TestInstrument implements En {

    public ReportStepDefinitions() {

        When("user fill report form", () -> {
            bukalapak.reportPage().isDisplayed();
            bukalapak.reportPage().fillFormWithDefaultValue();
        });

        When("user fill report form without reason", () -> {
            bukalapak.reportPage().isDisplayed();
            bukalapak.reportPage().fillDescription();
            bukalapak.reportPage().submit();
            STRIPEData.setMultipleValidation(bukalapak.reportPage().isSnackbarDisplayed());
        });

        When("user fill report form without description", () -> {
            bukalapak.reportPage().isDisplayed();
            bukalapak.reportPage().pickTopReason();
            bukalapak.reportPage().submit();
            STRIPEData.setMultipleValidation(bukalapak.reportPage().isBazaarSnackbarDisplayed());
        });

        Then("the report should succeed", () -> {
            bukalapak.reportPage().validateReportSuccess();
        });

        Then("the report should failed", () -> {
            bukalapak.reportPage().validateReportFailed();
        });
    }
}
