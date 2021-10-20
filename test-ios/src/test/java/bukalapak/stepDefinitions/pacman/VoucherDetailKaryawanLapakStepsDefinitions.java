package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VoucherDetailKaryawanLapakStepsDefinitions extends TestInstrument implements En {

    public VoucherDetailKaryawanLapakStepsDefinitions() {

        Given("user is in \"Voucher Lapak Detail\" page", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().userOnVoucherDetailKaryawanLapak();
        });

        Then("^user is shown voucher with Kode Voucher \"([^\"]*)\" on (?:Super Seller|premium dashboard) voucher detail$", (String voucherCode) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherCode(voucherCode);
        });

        Then("^user is shown voucher with Status (.*) on (?:Super Seller|premium dashboard) voucher detail$", (String status) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherStatus(status);
        });

        Then("^user is shown voucher type with \"([^\"]*)\"$", (String voucherType) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherType(voucherType);
        });

        Then("^user is shown voucher with etalase (.*) on (?:Super Seller|premium dashboard) voucher detail$", (String etalaseName) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherEtalase(etalaseName);
        });

        Then("^user is shown \"([^\"]*)\" info in penggunaan per hari", (String qty) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyPenggunaanPerHari(qty);
        });

        Then("^user is shown \"([^\"]*)\" info in penggunaan per periode", (String qty) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyPenggunaanPerPeriode(qty);
        });

        Then("^user is shown voucher with Minimum Nilai Transaksi \"([^\"]*)\" on (?:Super Seller|premium dashboard) voucher detail$", (String amount) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherMinTrx(amount);
        });

        Then("^user is shown voucher with Potongan Harga \"([^\"]*)\" on (?:Super Seller|premium dashboard) voucher detail$", (String amount) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherPotonganHarga(amount);
        });

        When("^user taps on Rincian Voucher on Voucher Detail page$", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapRincianVoucher();
        });

        Then("^user is shown voucher with Voucher Dibuat \"([^\"]*)\" on (?:Super Seller|premium dashboard) voucher detail$", (String qty) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherQuantity(qty);
        });

        When("^user taps on Detail Pengeluaran on Voucher Detail page$", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapDetailPengeluaran();
        });

        Then("^user is shown voucher with Voucher Terpakai \"([^\"]*)\"$", (String qty) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherTerpakai(qty);
        });

        Then("^user is shown voucher with Potongan Voucher \"([^\"]*)\"$", (String amountPrice) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyPotonganVoucher(amountPrice);
        });

        Then("^user taps on toggle publish voucher lapak in voucher detail$", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapVoucherToggle();
        });

        Then("^user is shown voucher with toggle publish (ON|OFF) on (?:Super Seller|premium dashboard) voucher detail$", (String status) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherToggle(status);
        });

        When("user taps Edit on voucher lapak", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapOnEditOnVoucher();
        });

        And("user tap back button from Detail Voucher page", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapBackButton();
        });

        // Voucher Diskon
        Then("^user is shown (?:Besar Diskon|Maksimum Potongan Harga) \"([^\"]*)\" on voucher detail besar diskon$", (String percentAmount) -> {
            bukalapak.voucherDetailKaryawanLapakPage().besarDiskon(percentAmount);
        });

        // Voucher Potongan Ongkos Kirim
        Then("^user is shown (?:Besar Potongan Ongkir|Jenis Kurir) \"([^\"]*)\" on voucher detail potongan ongkos kirim$", (String type) -> {
            bukalapak.voucherDetailKaryawanLapakPage().besarPotonganOngKir(type);
        });

        // Riwayat Voucher
        Then("^user is in Riwayat Voucher page$", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyRiwayatVoucherDisplay();
        });

        Then("^user is shown voucher code \"([^\"]*)\" in the first list riwayat voucher lapak$", (String voucherCode) -> {
            bukalapak.voucherDetailKaryawanLapakPage().verifyVoucherCodeRiwayatVoucher(voucherCode);
        });

        Then("^user tap first voucher in riwayat voucher lapak$", () -> {
            bukalapak.voucherDetailKaryawanLapakPage().tapVoucherListRiwayatVoucher();
        });
    }
}
