package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import bukalapak.data.XPRData;
import cucumber.api.java8.En;
import com.bukalapak.salad.util.Direction;
import io.cucumber.datatable.DataTable;

public class TransactionDetailStepDefinitions extends TestInstrument implements En {

    public TransactionDetailStepDefinitions() {

        Given("user is in \"Detail Transaksi\" page", () -> {
            bukalapak.transactionDetailPage().isOnTransactionDetailPage();
        });

        When("user repurchase \"([^\"]*)\" from detail transaction page", (String productName) -> {
            /*
            Please simplify these 3 methods into a single method in TransactionDetailsPage class. So it can be
            invoked as a single line of method from TransactionDetailPage class.
             */
            bukalapak.transactionDetailPage().isOnTransactionDetailPage();
            bukalapak.transactionDetailPage().openTransactionDetailPage();
            bukalapak.transactionDetailPage().tapOnRepurchaseButton();
        });

        And("resi number match with input user", () -> {
            bukalapak.transactionDetailPage().validationResiNumber();
        });

        Then("resi number match with input user in detail transaction", () -> {
            bukalapak.transactionDetailPage().validateResiNumber();
        });

        Then("jasa pengiriman match with input user in detail transaction", () -> {
            bukalapak.transactionDetailPage().validateJasaPengiriman();
        });

        Then("Status pengiriman in Detil transaksi penjualan is \"([^\"]*)\"", (String arg0) -> {
            bukalapak.transactionDetailPage().validateStatusPengirimanInPenjualanPage(arg0);
        });

        Then("Status pengiriman in Detil transaksi pembelian is \"([^\"]*)\"", (String arg0) -> {
            bukalapak.transactionDetailPage().validateStatusPengirimanInPembelianPage(arg0);
        });

        And("user click kode unik ambil sendiri button", () -> {
            bukalapak.transactionDetailPage().clickMasukanKodeUnik();
        });

        And("user scroll page to refresh page", () -> {
            /*
            Please avoid using BasePage method directly. The method can be invoked from inherited class (related page object class).
             */
            bukalapak.iOSBasePage().swipeToDirection(Direction.UP);
            bukalapak.iOSBasePage().swipeToDirection(Direction.UP);
        });

        And("user verify status transaksi ambil sendiri", () -> {
            bukalapak.transactionDetailPage().validateStatusAfterInputKodeUnik();
        });

        Then("^user validate status of transaction (before|after) Unique Code ambil sendiri$", (String state, DataTable status) -> {
            bukalapak.transactionDetailPage().validateStatusAmbilSendiri(state, status);
        });

        Then("user validate confirmation button", () -> {
            bukalapak.transactionDetailPage().validateKonfirmasiButton();
        });

        When("^user click Komplain button in Detail Transaksi$", () -> {
            bukalapak.transactionDetailPage().tapOnKomplainbutton();
        });

        When("^user tap on BukaBantuan link in Detail Transaksi", () -> {
            bukalapak.transactionDetailPage().tapOnBukabantuan();
        });

        Then("^user should see \"([^\"]*)\" text at the end of Detail Transaksi page$", (String caption) -> {
            bukalapak.transactionDetailPage().validateKomplainText(caption);
        });

        When("^user tap on Halaman Komplain link in Detail Transaksi$", () -> {
            bukalapak.transactionDetailPage().tapOnHalamanKomplainLink();
        });

        Then("^user should see button \"([^\"]*)\" and \"([^\"]*)\" in Detail Transaksi$", (String primaryButton, String secondaryButton) -> {
            bukalapak.transactionDetailPage().validateButtonText(primaryButton);
            bukalapak.transactionDetailPage().validateButtonText(secondaryButton);
        });

        And("^user scrolls to recommendation section on transaction detail$", () -> {
            bukalapak.transactionDetailPage().scrollsToRecommendation();
        });

        And("^user validate recommendation title$", () -> {
            bukalapak.transactionDetailPage().validateRecommendationTitle();
        });

        When("^user clicks button Beli on recommendation transaction detail$", () -> {
            bukalapak.transactionDetailPage().tapButtonBeliRecommendation();
        });

        Then("^user see pop up add to cart on transaction detail$", () -> {
            bukalapak.transactionDetailPage().validateCartDialogue();
        });

        When("^user tap product recommendation on transaction detail$", () -> {
            bukalapak.transactionDetailPage().tapProductRecommendation();
        });

        Then("user is shown Super Seller fee amount on transaction detail", () -> {
            bukalapak.transactionDetailPage().verifySuperSellerFeeDetail();
        });

        When("user click Biaya Super Seller row on transaction detail", () -> {
            bukalapak.transactionDetailPage().tapSuperSellerFeeDetailText();
        });

        Then("^user is shown (.+) on Super Seller fee on transaction detail$", (String detailName) -> {
            bukalapak.transactionDetailPage().verifySuperSellerFeeDetail(detailName);
        });

        Then("user is shown Info Biaya Super Seller on transaction detail", () -> {
            bukalapak.transactionDetailPage().verifyInfoBiayaSuperSellerLink();
        });

        Then("user is not shown Biaya Super Seller on transaction detail", () -> {
            bukalapak.transactionDetailPage().verifySuperSellerFeeTextNotExist();
        });

        Then("user is shown pop up Info Biaya Super Seller on transaction detail", () -> {
            bukalapak.transactionDetailPage().verifyInfoBiayaSuperSellerPopUp();
        });

        When("user tap button Kunjungi Mutasi Super Seller in pop up Info Biaya Super Seller on transaction detail", () -> {
            bukalapak.transactionDetailPage().tapKunjungiMutasiSuperSeller();
        });

        When("^user go to transaction detail page with deeplink \"([^\"]*)\"$", (String deeplink) -> {
            bukalapak.transactionDetailPage().goToTransactionDetailPageWithDeeplink(deeplink);
        });

        Then("user should see status is refunded on transaction detail page", () -> {
            bukalapak.transactionDetailPage().validateRefundedStatus();
        });

        And("^user see old reject reason from \"al(?:chemy|gebra)\" contains \"([^\"]*)\"$", (String rejectReason) -> {
            bukalapak.transactionDetailPage().validateOldRejectReason(rejectReason);
        });

        Then("user validate tag bukaExpress in Detail transaction page", () -> {
            bukalapak.transactionDetailPage().validateTagBukaExpressOnTransactionDetail();
        });

        When("user navigate to transaction detail page via deeplink using transaction id from API", () -> {
            bukalapak.transactionDetailPage().openDetailTransactionViaDeeplinkwithIdTrxFromAPI();
        });

        And("user now on the transaction details page", () -> {
            bukalapak.transactionDetailPage().validateTrxDetailPage();
        });
    }
}
