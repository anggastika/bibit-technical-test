package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PendapatanStepDefinitons extends TestInstrument implements En {
    public PendapatanStepDefinitons() {
        Then("^user is in \"Pendapatan\" page$", () -> {
            bukalapak.pendapatanPage().verifyPage();
        });

        Then("^user is shown offering Super Seller on Pendapatan Page$", () -> {
            bukalapak.pendapatanPage().verifyOfferingSuperSeller();
        });

        Then("^user is shown income summary on Pendapatan page$", () -> {
            bukalapak.pendapatanPage().verifyIncomeSummary();
        });

        Then("^user is shown default period date for (\\d+) days$", (Integer periode) -> {
            bukalapak.pendapatanPage().verifyDatePeriode(periode);
        });

        When("^user click Periode on (?:Pendapatan|Pemasukan Lapak|Pola Pemasukan) page$", () -> {
            bukalapak.pendapatanPage().clickPeriodeDropdown();
        });

        Then("^user is shown info pemasukan \"([^\"]*)\" on pendapatan page$", (String info) -> {
            bukalapak.pendapatanPage().verifyInfoPemasukan(info);
        });

        When("^user click \"([^\"]*)\" link on pendapatan page$", (String linkText) -> {
            bukalapak.pendapatanPage().tapPantauPengeluaranmuLink(linkText);
        });
    }
}
