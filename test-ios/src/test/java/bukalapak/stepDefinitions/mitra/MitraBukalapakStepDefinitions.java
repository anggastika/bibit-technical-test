package bukalapak.stepDefinitions.mitra;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class MitraBukalapakStepDefinitions extends TestInstrument implements En {

    public MitraBukalapakStepDefinitions() {
        Then("^user is on Mitra Bukalapak page$", () -> {
            bukalapak.mitraBukalapakPage().userOnMitraBukalapakPage();
        });

        Then("^user validate cannot use grosir feature", () -> {
            bukalapak.mitraBukalapakPage().validateCannotUseGrosirFeature();
        });

        Then("^user is on Mitra Bukalapak Aktifkan Kembali page", () -> {
            bukalapak.mitraBukalapakPage().userOnMitraBukalapakAktifkanKembaliPage();
        });

        Then("^user is on Grosir Mitra Bukalapak page", () -> {
            bukalapak.mitraBukalapakPage().userOnGrosirPage();
        });

        Then("^user is on Transaksi Mitra Bukalapak page", () -> {
            bukalapak.mitraBukalapakPage().userOnTransaksiPage();
        });

        Then("^user is on Bantuan Mitra Bukalapak page", () -> {
            bukalapak.mitraBukalapakPage().userOnBantuanPage();
        });
    }
}
