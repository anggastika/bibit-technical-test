package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BuatVoucherStepDefinitions extends TestInstrument implements En {
    public BuatVoucherStepDefinitions() {

        When("^user create voucher lapak$", () -> {
            bukalapak.premiumDashboardPage().tapCreateVoucherLapak();
            bukalapak.buatVoucherPage().createVoucherLapak();
        });

        When("^user submit voucher lapak$", () -> {
            bukalapak.buatVoucherPage().createVoucherLapak();
        });

        When("^user choose jenis voucher (Potongan Harga|Diskon|Potongan Ongkos Kirim) radio button$", (String jenisVoucher) -> {
            bukalapak.buatVoucherPage().selectJenisVoucher(jenisVoucher);
        });

        When("^user fills Kode Voucher field with \"([^\"]*)\"$", (String voucherCode) -> {
            bukalapak.buatVoucherPage().inputKodeVoucher(voucherCode);
        });

        When("^user fills Potongan Harga field with \"([^\"]*)\"$", (String amount) -> {
            bukalapak.buatVoucherPage().inputPotonganHarga(amount);
        });

        When("^user fills Minimum Nilai Transaksi field with \"([^\"]*)\"$", (String amount) -> {
            bukalapak.buatVoucherPage().inputMinimumTrx(amount);
        });

        When("^user fills Jumlah Voucher field with \"([^\"]*)\"$", (String qty) -> {
            bukalapak.buatVoucherPage().inputJumlahVoucher(qty);
        });

        When("^user tap pengaturan tambahan in Buat Voucher Page$", () -> {
            bukalapak.buatVoucherPage().tapPengaturanTambahan();
        });

        When("^user fills \"([^\"]*)\" voucher maksimum voucher per hari$", (String qty) -> {
            bukalapak.buatVoucherPage().inputMaksPerHari(qty);
        });

        When("^user fills \"([^\"]*)\" voucher maksimum voucher per periode$", (String qty) -> {
            bukalapak.buatVoucherPage().inputMaksPerPeriode(qty);
        });

        When("^user click agreement checkbox on Buat Voucher page$", () -> {
            bukalapak.buatVoucherPage().checkAgreementCheckbox();
        });

        When("^user select etalase 1$", () -> {
            bukalapak.buatVoucherPage().selectEtalaseBarangTertentu();
            bukalapak.buatVoucherPage().selectEtalaseNumberOne();
        });

        When("^user taps simpan button", () -> {
            bukalapak.buatVoucherPage().tapSimpanButton();
        });

        // Untuk jenis voucher diskon
        When("^user fills Besar Diskon field with \"([^\"]*)\" in voucher diskon$", (String percentAmount) -> {
            bukalapak.buatVoucherPage().inputBesarDiskon(percentAmount);
        });

        When("^user fills Maksimum Potongan Harga field with \"([^\"]*)\" in voucher diskon$", (String amount) -> {
            bukalapak.buatVoucherPage().inputMaksPotonganHarga(amount);
        });

        // Untuk jenis Potongan Ongkos Kirim
        When("^user fills Besar Potongan Ongkos Kirim field with \"([^\"]*)\" in voucher ongkos kirim$", (String amount) -> {
            bukalapak.buatVoucherPage().besarPotonganongkir(amount);
        });

        When("^user select kurir for voucher ongkos kirim$", () -> {
            bukalapak.buatVoucherPage().tapJenisKurirDropdDown();
            bukalapak.buatVoucherPage().selectJenisKurir();
        });

        Given("user is in \"Buat Voucher\" page", () -> {
            bukalapak.buatVoucherPage().userOnBuatVoucherPage();
        });
    }
}
