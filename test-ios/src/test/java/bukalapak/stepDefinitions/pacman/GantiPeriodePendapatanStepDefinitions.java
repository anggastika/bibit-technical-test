package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class GantiPeriodePendapatanStepDefinitions extends TestInstrument implements En {
    public GantiPeriodePendapatanStepDefinitions() {
        Then("^user is in \"Ganti Periode (?:Pendapatan|Pemasukan Lapak|Pola Pemasukan)\" page$", () -> {
            bukalapak.gantiPeriodePendapatanPage().verifyPage();
        });

        Then("^user is shown Atur Tanggal pop up", () -> {
            bukalapak.gantiPeriodePendapatanPage().verifyCalendarSheet();
        });

        Then("^user is shown date of today as unclickable$", () -> {
            bukalapak.gantiPeriodePendapatanPage().verifyTodayDateDisabled();
        });

        Then("^user is shown \"([^\"]*)\" on period by date$", (String notes) -> {
            bukalapak.gantiPeriodePendapatanPage().verifyFilterByDatesNotes(notes);
        });

        Then("^user select period pemasukan (\\d+) days from today$", (Integer range) -> {
            bukalapak.gantiPeriodePendapatanPage().selectDateBeforeToday(range);
        });

        Then("^user is shown maximum period by date of (\\d+) days being selected$", (Integer range) -> {
            bukalapak.gantiPeriodePendapatanPage().verifySelectedMaximumDatePeriod(range);
        });
    }
}
