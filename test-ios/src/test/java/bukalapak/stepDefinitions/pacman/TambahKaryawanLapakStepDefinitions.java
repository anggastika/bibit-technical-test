package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class TambahKaryawanLapakStepDefinitions extends TestInstrument implements En {
    public TambahKaryawanLapakStepDefinitions() {
        Given("^user is in \"Tambah Karyawan\" page$", () -> {
            bukalapak.tambahKaryawanPage().userOnTambahKaryawan();
        });

        When("^user click tambah karyawan button$", () -> {
            bukalapak.tambahKaryawanPage().clickTambahKaryawanButton();
        });

        When("^user click checkbox privilage (Kelola Barang|Kelola Transaksi|Chat)$", (String privilageName) -> {
            bukalapak.tambahKaryawanPage().changePrivilegeStaff(privilageName);
        });

        And("^user enter password for edit staff$", () -> {
            bukalapak.tambahKaryawanPage().enterPasswordTextField();
        });

        And("^user click simpan button for edit staff$", () -> {
            bukalapak.tambahKaryawanPage().clickSaveEditStaffButton();
        });
    }
}
