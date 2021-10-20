package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PengaturanPembayaranTambahKartuStepDefinitions extends TestInstrument implements En {

    public PengaturanPembayaranTambahKartuStepDefinitions() {

        Then("user is in \"pengaturan pembayaran tambah kartu\" page", () -> {
            bukalapak.pengaturanPembayaranTambahKartuPage().userOnPengaturanPembayaranTambahKartuPage();
        });

        When("user add new card", () -> {
            bukalapak.pengaturanPembayaranTambahKartuPage().typeOnCardInfoField(dotenv.get("CC_NUMBER"), dotenv.get("CC_CVV"));
            bukalapak.pengaturanPembayaranTambahKartuPage().tapOnSimpanButton();
        });

        Then("^show message kartu \"([^\"]*)\"", (String status) -> {
            bukalapak.pengaturanPembayaranTambahKartuPage().validateNotificationTambahKartu(status);
        });
    }
}
