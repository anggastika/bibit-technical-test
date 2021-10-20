package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PengaturanRekeningStepDefinitions extends TestInstrument implements En {

    public PengaturanRekeningStepDefinitions() {
        Given("user is in \"pengaturan_rekening_bank\" page", () -> {
            bukalapak.pengaturanRekeningPage().userOnPengaturanRekeningPage();
        });

        Given("user is in \"tambah_rekening\" page", () -> {
            bukalapak.tambahRekeningPage().userOnTambahRekeningStepsPage();
        });

        Given("user is in \"edit_rekening\" page", () -> {
            bukalapak.editRekeningPage().userOnEditRekeningStepsPage();
        });

        Given("user \"([^\"]*)\" on rekening page", (String action) -> {
            bukalapak.pengaturanRekeningPage().typeOnRekeningField(action);
        });
    }
}
