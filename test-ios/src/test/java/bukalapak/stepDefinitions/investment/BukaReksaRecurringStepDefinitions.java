package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaReksaRecurringStepDefinitions extends TestInstrument implements En {

    public BukaReksaRecurringStepDefinitions() {

        //region Bukareksa Recurring list from Bukareksa Beranda page (Transaksi Rutin)
        Then("user should be able to see BukaReksa quick filter in recurring page", () -> {
            bukalapak.bukaReksaRecurringPage().checkRexaRecurringFilter();
        });

        Then("user should be able to see BukaReksa recurring list", () -> {
            bukalapak.bukaReksaRecurringPage().checkRexaRecurringCard();
        });

        When("user tap on Aktifkan Kembali button", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnAktifkanKembaliBtn();
        });

        When("user should be able to see recurring transaction \"([^\"]*)\"", (String state) -> {
            bukalapak.bukaReksaRecurringPage().validateRecurringState(state);
        });

        When("user tap on BukaReksa recurring card", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnBukaReksaRecurringCard();
        });
        //end region Bukareksa Recurring list from Bukareksa Beranda page (Transaksi Rutin)

        //region Bukareksa Recurring detail (Detail Transaksi Rutin)
        When("user should be able to see bukareksa recurring transaction detail", () -> {
            bukalapak.bukaReksaRecurringPage().isOnRexaRecurringTrxDetail();
        });

        When("user tap on recurring toggle", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnRecurringToggle();
        });

        When("user tap on recurring back button", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnRecurringBackBtn();
        });
        //end region Bukareksa Recurring detail (Detail Transaksi Rutin)

        //region BukaReksa Recurring Form from Portofolio page (Transaksi Rutin)
        When("user should be able to see BukaReksa recurring form", () -> {
            bukalapak.bukaReksaRecurringPage().isOnRecurringForm();
        });

        When("user tap on Produk BukaReksa dropdown", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnProductDropdown();
        });

        Then("user should be able to see bukareksa product option", () -> {
            bukalapak.bukaReksaRecurringPage().isOnProductListing();
        });

        When("user choose \"([^\"]*)\" product", (String productFilter) -> {
            bukalapak.bukaReksaRecurringPage().tapOnproductQuickFilter(productFilter);
        });

        Then("user should be able to see recurring product", () -> {
            bukalapak.bukaReksaRecurringPage().validateRecurringProductName();
        });

        Then("user tap on product card", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnReksaProduct() ;
        });

        When("user input \"([^\"]*)\" as recurring amount", (String amount) -> {
            bukalapak.bukaReksaRecurringPage().inputRecurringAmount(amount);
        });

        Then("user choose DANA as recurring payment method", () -> {
            bukalapak.bukaReksaRecurringPage().chooseRecurringPaymentMethod();
        });

        When("user tap on recurring checkbox tnc", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnRecurringCheckBox();
        });

        When("user verify recurring transaction detail", () -> {
            bukalapak.bukaReksaRecurringPage().verifyRecurringTrxDetailDisplayed();
        });

        When("user tap on Aktifkan Transaksi Rutin button", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnAktifkanTransaksiRutinBtn();
        });

        Then("user should be able to see \"([^\"]*)\" snackbar", (String snackbar) -> {
            bukalapak.bukaReksaRecurringPage().showRecurringSnackbar(snackbar);
        });

        When("user scroll down to BukaReksa recurring", () -> {
            bukalapak.bukaReksaRecurringPage().verifyRecurringCheckoutTransaction();
        });

        Then("user tap on jadikan transaksi rutin button", () -> {
            bukalapak.bukaReksaRecurringPage().tapOnJadikanTrxRutinBtn();
        });

        //end region BukaReksa Recurring Form from Portofolio page (Transaksi Rutin)
    }
}
