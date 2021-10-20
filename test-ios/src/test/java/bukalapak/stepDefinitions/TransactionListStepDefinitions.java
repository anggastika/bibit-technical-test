package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import bukalapak.data.XPRData;
import cucumber.api.java8.En;

public class TransactionListStepDefinitions extends TestInstrument implements En {

    public TransactionListStepDefinitions() {
        When("user search \"([^\"]*)\" invoice number", (String args0) -> {
            /*
            Please simplify these 3 methods into a single method in TransactionListPage class. So it can be
            invoked as a single line of method from TransactionListage class.
             */
            bukalapak.transactionListPage().userOnTransactionListPage();
            bukalapak.transactionListPage().searchInvoice(args0);
            bukalapak.transactionListPage().clickOnTransaction();
        });

        And("user search Nomor Transaksi in pembelian page", () -> {
            bukalapak.transactionListPage().searchTransactionPembelian(TransactionData.getNomorTransaksi());
        });

        And("user search Nomor Transaksi in list of penjualan", () -> {
            bukalapak.transactionListPage().searchTransactionPenjualan(TransactionData.getNomorTransaksi());

        });

        Then("user see the first invoice is in (.*) transaction with (.*) status", (String type, String status) -> {
            bukalapak.transactionListPage().checkFirstInvoiceDetailProm(type, status);
        });

        And("^user search pembelian with \"([^\"]*)\" keyword on Pembelian tab$", (String pembelianTransaction) -> {
            bukalapak.transactionListPage().searchTransactionPembelian(pembelianTransaction);
        });

        Then("^user see Wajib Resi Otomatis tag in product list", () -> {
            bukalapak.transactionListPage().validatePriorityResiOtomatis();
        });

        Then("^user is in \"transaction\" page", () -> {
            bukalapak.transactionListPage().userOnTransactionListPage();
        });

        When("^user tap penjualan page", () -> {
            bukalapak.transactionListPage().clickOnPenjualanTab();
        });

        When("user tap first transaction", () -> {
            bukalapak.transactionListPage().clickOnTransaction();
        });

        When("user tap first transaction penjualan", () -> {
            bukalapak.transactionListPage().clickOnTransactionPenjualan();
        });

        And("^user is in transaction list page$", () -> {
            bukalapak.transactionListPage().verifyPushTransaction();
        });

        When("^user go to last pembelian transaction$", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://www.bukalapak.com/payment/invoices/" + TransactionData.getInvoiceNo());
            bukalapak.detailPembelianMarketplacePage().userOnDetailPembelianPage();
        });

        And("^user tap pembelian tab$", () -> {
            bukalapak.transactionListPage().clickOnPembelianTab();
        });

        When("^user tap first transaction on pembelian tab$", () -> {
            bukalapak.transactionListPage().tapFirstTransactionPembelian();
        });

        Then("^user validate \"([^\"]*)\" banner (not )?shown on Transaction Page", (String freezeWarningBanner, String bannerState) -> {
            bukalapak.transactionListPage().validateFreezeWarningBannerDisplayed(freezeWarningBanner, bannerState);
        });

        And("^user search penjualan with \"([^\"]*)\" keyword on Penjualan tab$", (String penjualanTransaction) -> {
            bukalapak.transactionListPage().searchTransactionPenjualan(penjualanTransaction);
        });

        Then("^user will see COD label on list transaction$", () -> {
            bukalapak.transactionListPage().validateCODlabel();
        });

        When("user tap transaction penjualan", () -> {
            bukalapak.transactionListPage().tapOnTransactionPenjualan();
        });

        When("user tap on voucher lapak banner", () -> {
            bukalapak.transactionListPage().tapOnBuatVoucherLapakBanner();
        });

        And("^user go to last pembelian transaction from list transaction$", () -> {
            bukalapak.iOSBasePage().openDeepLink("https://www.bukalapak.com/payment/invoices");
            bukalapak.transactionListPage().clickOnPembelianTab();
            bukalapak.iOSBasePage().waitForVisibilityOf("transaction_list_first_transaction_pembelian", 120);
            bukalapak.transactionListPage().searchTransactionPembelian(TransactionData.getNomorTransaksi());
            bukalapak.transactionListPage().tapFirstTransactionPembelian();
            bukalapak.detailPembelianMarketplacePage().userOnDetailPembelianPage();
        });

        When("seller banner on Transaction page is displayed", () -> {
            bukalapak.transactionListPage().verifySellerBanner();
        });

        Then("user validate tag bukaExpress in Transaction list", () -> {
            bukalapak.transactionListPage().validateTagBukaExpress();
        });

        When("^user go to \"([^\"]*)\" tab in Transaction page$", (String tabName) -> {
            bukalapak.homePage().clickTransactionPage();
            bukalapak.transactionListPage().userOnTransactionListPage();
            bukalapak.transactionListPage().tapTabMenu(tabName);
        });

        Then("^user search (invoice|transaction)? with keyword \"([^\"]*)\"$", (String type, String keyword) -> {
            bukalapak.transactionListPage().searchSpecificTrxOrInvoice(type, keyword);
        });

        And("^user validate (invoice|transaction)? is shown$", (String type) -> {
            bukalapak.transactionListPage().validateSearchTrxOrInvoice(type);
        });

        And("^user tap the \"([^\"]*)\" item$", (String type) -> {
            bukalapak.transactionListPage().tapSearchResultTrx(type);
        });

        And("^user validate (invoice|transaction)? number is correct$", (String type) -> {
            bukalapak.transactionListPage().validateInvoiceOrTrxNumber(type);
        });
    }
}
