package bukalapak.stepDefinitions.stripe;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PenjualanListStepDefinitions extends TestInstrument implements En {

    public PenjualanListStepDefinitions() {
        And("user tap on Dibayar button", () -> {
            bukalapak.transactionPenjualanPage().selectFilterDibayar();
            bukalapak.transactionPenjualanPage().selectTerapkan();
        });

        And("user tap on first transaction", () -> {
            bukalapak.transactionPenjualanPage().selectFirstTransaction();
        });

        And("user get transaction number", () -> {
            bukalapak.transactionPenjualanPage().setTransactionNumber();
        });

        And("user search Nomor Transaksi", () -> {
            bukalapak.belumDikirimPage().typeOnSearchButton();
        });

        And("user reset filter in sell product filter page", () -> {
            bukalapak.transactionPenjualanPage().clickResetButton();
        });

        And("user search Nomor Transaksi in sell page", () -> {
            bukalapak.transactionPenjualanPage().typeOnSearchButton();
        });

        Then("validation Number Transaction is not found", () -> {
            bukalapak.transactionPenjualanPage().validationProductNotFound();
        });

        And("user input Jasa Pengiriman \"([^\"]*)\" column with the valid courier", (String jasaPengiriman) -> {
            bukalapak.transactionPenjualanPage().typeCourierName(jasaPengiriman);
        });

        And("user get courier name before edit", () -> {
            bukalapak.transactionPenjualanPage().getCourierName();
        });

        And("user click penjualan filter icon", () -> {
            bukalapak.transactionPenjualanPage().clickFilterIcon();
        });

        And("user tap on DiProses button", () -> {
            bukalapak.transactionPenjualanPage().selectFilterDiProses();
            bukalapak.transactionPenjualanPage().selectTerapkan();
        });

        And("user get receiver name", () -> {
            bukalapak.detailPenjualanPage().setReceiverName();
        });

        And("user get catatan pembeli", () -> {
            bukalapak.detailPenjualanPage().setNoteBuyer();
        });

        And("user get courier name on detail seller trasaction", () -> {
            bukalapak.transactionPenjualanPage().getCourierNameOnDetail();
        });

        And("user tap on edit resi in detail transaction page", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnEditResi();
        });

        Then("resi number match with input user in detail transaction alchemy", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().validateResiNumber();
        });
        
        When("user tap on proses pesanan button alchemy", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnProsesPesanan();
        });

        When("user tap on button proses pesanan on modal", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnModalProsesPesanan();
        });

        When("user tap on konfirmasi kirim button alchemy", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnKirimBarang();
        });

        And("user tap on Tolak Pesanan on single transaction alchemy", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnSingleTolakPesananButton();
        });

        And("user choose reason tolak pesanan", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().tapOnReasonTolakPesanan();
        });

        Then("validate reason tolak pesanan match with input user", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().validateReasonTolakPesanan();
        });

        Then("user is in \"detail_transaksi_dikembalikan\" page", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().userOnRefundedTransactionPage();
        });

        Then("user verify address is match with the one memorized before", () -> {
            bukalapak.detailTransaksiPenjualanAlchemyPage().verifyAddress();
        });

    }
}
