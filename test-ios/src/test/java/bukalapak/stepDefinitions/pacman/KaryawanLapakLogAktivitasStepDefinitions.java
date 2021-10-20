package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import bukalapak.pageObject.KaryawanLapakLogAktivitasPage;
import cucumber.api.java8.En;

public class KaryawanLapakLogAktivitasStepDefinitions extends TestInstrument implements En {
    public KaryawanLapakLogAktivitasStepDefinitions() {
        Given("user is in \"Karyawan Lapak Log Activity\" page", () -> {
            bukalapak.karyawanLapakLogAktivitasPage().userOnLogAktivitas();
        });

        And("^user see list log activity karyawan lapak$", () -> {
            bukalapak.karyawanLapakLogAktivitasPage().verifyLogActivityInTheList();
        });

        And ("^user tap filter button in karyawan lapak activity log page$", () -> {
            bukalapak.karyawanLapakLogAktivitasPage().tapFilterBtn();
        });

        And ("^user select filter \"([^\"]*)\" category and \"([^\"]*)\" sub category$", (String filterCategory, String subFilterCategory) -> {
            bukalapak.karyawanLapakLogAktivitasPage().selectFilterTypeMenu(filterCategory, subFilterCategory);
        });

        And ("^user tap terapkan filter button filter popup$", () -> {
            bukalapak.karyawanLapakLogAktivitasPage().tapTerapkanFilterBtn();
        });

        Then ("^user see result filter have \"([^\"]*)\" activity in list karyawan lapak log activity page$", (String resultFilter) -> {
            bukalapak.karyawanLapakLogAktivitasPage().verifyFilterResult(resultFilter);
        });
    }
}
