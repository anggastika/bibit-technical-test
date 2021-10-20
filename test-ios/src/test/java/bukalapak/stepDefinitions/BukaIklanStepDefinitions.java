package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaIklanStepDefinitions extends TestInstrument implements En {

    public BukaIklanStepDefinitions() {

        Given("user is in \"BukaIklan\" page", () -> {
            bukalapak.bukaIklanPage().userOnBukaIklanPage();
        });

        Given("user is in \"BukaIklan Report\" page", () -> {
            bukalapak.bukaIklanReportPage().userOnBukaIklanReportPage();
        });

        Given("user is in \"BukaIklan Information\" page", () -> {
            bukalapak.bukaIklanReportPage().userOnBukaIklanReportNotSubscribedPage();
        });

        Given("user is in \"BukaIklan FAQ\" page", () -> {
            bukalapak.bukaIklanFAQPage().userOnBukaIklanFAQPage();
        });

        When("user navigate to \"BukaIklan Pelajari\" page", () -> {
            bukalapak.bukaIklanPage().tapPelajariButton();
        });
    }
}
