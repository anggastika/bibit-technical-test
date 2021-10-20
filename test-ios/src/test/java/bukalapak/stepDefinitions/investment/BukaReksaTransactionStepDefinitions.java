package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaTransactionStepDefinitions extends TestInstrument implements En {

    public BukaReksaTransactionStepDefinitions() {

        //region Transaction List
        When("^user should be able to see transaction list page", () -> {
            bukalapak.bukaReksaTransactionPage().isTransactionListPageDisplayed();
        });

        When("^user tap on transaction filter button", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnTransactionFilterBtn();
        });

        When("^user should be able to see transaction filter page displayed", () -> {
            bukalapak.bukaReksaTransactionPage().isTransactionFilterPageDisplayed();
        });

        When("^user tap on transaction filter date dropdown", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnDateDropdown();
        });

        When("user select filter transaction by date \"([^\"]*)\" month \"([^\"]*)\" year \"([^\"]*)\"", (String date, String month, String year) -> {
            bukalapak.bukaReksaTransactionPage().selectAlchemyDatePicker(date, month, year);
            bukalapak.datePickerAlchemyPage().selectDatePicker(date, month, year);
        });

        When("user should be able to see filter transaction value is selected", () -> {
            bukalapak.bukaReksaTransactionPage().verifyFilterTrxIsSelected();
        });

        When("user should be able to see transaction detail page", () -> {
            bukalapak.bukaReksaTransactionPage().isOnBukaReksaTransactionDetailPage();
        });

        When("user should be able to see value of invoice date created", () -> {
            bukalapak.bukaReksaTransactionPage().verifyInvoiceDateCreated();
        });

        When("user select filter transaction by type", () -> {
            bukalapak.bukaReksaTransactionPage().selectFilterTransactionType();
        });

        When("^user should be able to see all transaction type radio button as default checked", () -> {
            bukalapak.bukaReksaTransactionPage().verifyAllTrxTypeRadioAsDefaultChecked();
        });

        When("user should be able to see all product type checkbox as default checked", () -> {
            bukalapak.bukaReksaTransactionPage().verifyAllProductTypeCheckBoxAsDefaultChecked();
        });

        When("user select filter transaction by product type", () -> {
            bukalapak.bukaReksaTransactionPage().selectFilterTransactionProductType();
        });

        When("user select filter transaction by state", () -> {
            bukalapak.bukaReksaTransactionPage().selectFilterTransactionState();
        });

        When("user tap on apply transaction filter button", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnApplyTransactionFilterBtn();
        });

        When("^user tap on transaction product list", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnTrxProductList();
        });

        When("^user tap first transaction on bukareksa transaction list", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnFirstTrxtList();
        });

        When("user should be able to see trx list based on filtering by type", () -> {
            bukalapak.bukaReksaTransactionPage().verifyTransactionListAreFilteredByType();
        });

        When("^user should be able to see transaction list are filtered by state", () -> {
            bukalapak.bukaReksaTransactionPage().verifyTransactionListAreFilteredByState();
        });

        When("^user tap back button on bukareksa transaction page", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnTransactionBackBtn();
        });
        //end region Transaction List

        //region Transaction Detail
        When("user should be able to see non investor forced registration popup displayed", () -> {
            bukalapak.bukaReksaTransactionPage().verifyPopUpForceRegisBukaReksa();
        });

        When("user tap on mulai daftar button on popup bwr", () -> {
            bukalapak.bukaReksaTransactionPage().tapOnMulaiDaftarButton();
        });

        When("user should be able to see value of BukaReksa product type", () -> {
            bukalapak.bukaReksaTransactionPage().verifyProductTypeValue();
        });

        When("user should be able to see BukaReksa transaction detail page", () -> {
            bukalapak.bukaReksaTransactionPage().isOnBukaReksaTransactionDetailPage();
        });

        When("user verify Informasi Tagihan section", () -> {
            bukalapak.bukaReksaTransactionPage().verifyInvoiceTotalAmountText();
            bukalapak.bukaReksaTransactionPage().verifyPaymentMethod();
        });

        When("user tap on ubah metode pembayaran button", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().clickUbahMetodePembayaran();
        });

        Then("user should see instant redemption transaction detail page", () -> {
            bukalapak.bukaReksaTransactionPage().verifyRedemptionDetailPage();
            bukalapak.bukaReksaTransactionPage().validateRedemptionType();
        });

        When("user verify total pembelian bukareksa package",() -> {
            bukalapak.bukaReksaTransactionPage().verifyPaymentMethod();
            bukalapak.bukaReksaTransactionPage().verifyInvoiceTotalPurchaseText();
        });

        When("user verify lihat transaksi di bukaemas link displayed", () -> {
            bukalapak.bukaReksaTransactionPage().verifyLihatTransaksidiBukaemasBtn();
        });
        //endregion Transaction Detail
    }
}
