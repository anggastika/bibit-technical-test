package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import cucumber.api.Scenario;
import cucumber.api.java8.En;

/**
 * Created by NurdianSetyawan on 17/12/18.
 * Modified by AdityaAminRefandi on 8/01/19.
 */
public class DetailPembelianMarketplaceStepDefinitions extends TestInstrument implements En {
    private String firstAddress = "";

    public DetailPembelianMarketplaceStepDefinitions() {
        Given("user is in \"detail_pembelian\" page", () -> {
            bukalapak.detailPembelianMarketplacePage().userOnDetailPembelianPage();
        });

        Given("user is in \"Detail Pembelian\" page", () -> {
            bukalapak.detailPembelianMarketplacePage().userOnDetailPembelianPage();
        });

        When("user verify total payment in detail pembelian page is match with the one memorized before", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyTotalPaymentIsMatch();
        });

        When("user verify total harga barang in detail pembelian page is match with the one memorized before", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyTotalHargaBarangIsMatch();
        });

        When("user verify total payment using Transfer Bank in detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyTotalPaymentMinusUniqueCode();
        });

        When("user verify total payment plus unique code using Transfer Bank in detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyTotalPaymentPlusUniqueCode();
        });

        When("user verify payment method is \"([^\"]*)\"", (String arg0) -> {
            bukalapak.detailPembelianMarketplacePage().verifyPaymentMethod(arg0);
        });

        And("user change payment method to \"([^\"]*)\" in Detail Pembelian Marketplace Page", (String newPaymentMethod) -> {
            bukalapak.iOSBasePage().tapElement("detail_pembelian_ubah_text");
            bukalapak.checkoutMarketplacePage().changePaymentMethodProcessOnInvoiceDetail(newPaymentMethod);
        });

        And("user verify current address in Detail Pembelian Marketplace Page is match with in Checkout Marketplace Page", () -> {
            firstAddress = TransactionData.getAddress();
            bukalapak.detailPembelianMarketplacePage().verifyAddressIsExist(firstAddress);
        });

        And("user click Ubah to change address in Detail Pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().changeAddress();
        });

        When("user verify the copy function of bill and account number works properly in detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyCopyFunctionOfBillAndAccountNumber();
        });
        When("user verify there is additional unique amount of total payment in detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyAdditionalUniqueAmount();
        });

        When("user verify the copy function of virtual account number works properly in detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyCopyFunctionOfVANumber();
        });

        And("user verify \"([^\"]*)\" as courier in detil pembelian page", (String arg0) -> {
            bukalapak.detailPembelianMarketplacePage().verifyJasaPengiriman(arg0);
        });

        And("user verify unique code of ambil sendiri is displayed on detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyKodeUnikAmbilSendiri();
        });

        And("user verify pickup address \"([^\"]*)\" is displayed on detail pembelian page", (String pickupAddress) -> {
            bukalapak.detailPembelianMarketplacePage().verifyPickupAddress(pickupAddress);
            bukalapak.detailPembelianMarketplacePage().verifyPetaLokasiPelapak();
        });

        Then("user validate status of purchase transaction \"([^\"]*)\" is displayed", (String statusPembelian) -> {
            bukalapak.detailPembelianMarketplacePage().validateStatusPembelianAmbilSendiri(statusPembelian);
        });

        Then("user should see status is paid on invoice detail page", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyPaidStatusOnInvoice();
        });

        After(new String[]{"@BTP-5443"}, (Scenario scenario) -> {
            bukalapak.apiCall().setUserAuthv4("confirmed");
            bukalapak.apiCall().cancelPaidInvoice();
        });

        And("^user click Back from BukaMart page$", () -> {
            bukalapak.detailPembelianMarketplacePage().clickBackFromBukaMart();
        });

        Then("^user click Rincian button$", () -> {
            bukalapak.detailPembelianMarketplacePage().clickOnRincianButton();
        });

        When("user verify detail invoice with Pembeli Prioritas (.*)", (String priorityType) -> {
            bukalapak.detailPembelianMarketplacePage().validatePriorityCheckoutType(priorityType);
        });

        When("user see (.*) error message as (.*) in Detail Pembelian Marketplace Page", (String transactionType, String errorText) -> {
            bukalapak.detailPembelianMarketplacePage().checkErrorPriorityBuyer(transactionType, errorText);
        });

        When("user see \"([^\"]*)\" in checkout detail pembelian page", (String option) -> {
            if (option.equalsIgnoreCase("nomor tagihan"))
                bukalapak.detailPembelianMarketplacePage().verifyNoTagihan();
            else
                bukalapak.detailPembelianMarketplacePage().verifyNoTransaksi();
        });

        When("^user tap on Ubah Ulasan button$", () -> {
            bukalapak.detailPembelianMarketplacePage().userTapUlasanBtn();
        });

        When("^user tap Terima Barang button$", () -> {
            bukalapak.detailPembelianMarketplacePage().userTapTerimaBarangBtn();
        });

        And("^user tap Ulas Barang button on detail pembelian$", () -> {
            bukalapak.detailPembelianMarketplacePage().userTapUlasBarangBtn();
        });

        //Investment
        And("user validate BukaEmas autoinvest on invoice detail section", () -> {
            bukalapak.detailPembelianMarketplacePage().validateAutoInvestAmount();
        });

        When("user scroll down to BukaEmas autoinvest section in invoice detail page", () -> {
            bukalapak.detailPembelianMarketplacePage().scrollToAutoInvestSection();
        });

        Then("user validate BukaEmas autoinvest on invoice detail page", () -> {
            bukalapak.detailPembelianMarketplacePage().validateAutoInvestSection();
        });

        When("user tap BukaEmas autoinvest section on invoice detail page", () -> {
            bukalapak.detailPembelianMarketplacePage().tapAutoInvestSection();
        });

        Then("user check that transaction has been canceled and the balance will be refunded", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyCancelTransactionAndRefunded();
        });

        Then("user verify no pesanan in detail pembelian match with no pesanan in instruksi pembayaran", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyNoPesanan();
        });

        Then("user verify total price in detail pembelian match with total price in instruksi pembayaran", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyTotalPayment();
        });

        Then("user tap Lanjutkan Pembayaran DANA Payment", () -> {
            bukalapak.detailPembelianMarketplacePage().goToDANACashierPage("continue payment");
        });

        Then("user memorizes invoice address", () -> {
            bukalapak.detailPembelianMarketplacePage().setInvoiceAddress();
        });

        Then("user tap Coba Lagi bayar with DANA Payment", () -> {
            bukalapak.detailPembelianMarketplacePage().goToDANACashierPage("repayment");
        });

        And("^user validate (multiple )?\"([^\"]*)\" match from checkout marketplace page$", (String flag, String sectionName) -> {
            bukalapak.detailPembelianMarketplacePage().validateDetailTrxFromCheckout(flag, sectionName);
        });

        And("user validate Invoice Number match from payment confirmation page", () -> {
            bukalapak.detailPembelianMarketplacePage().validateInvoiceNumber();
        });

        And("user validate kode pambayaran transfer bank manual", () -> {
            bukalapak.detailPembelianMarketplacePage().validatePaymentCode();
        });

        And("user validate buyer notes info on detail pembelian page", () -> {
            bukalapak.detailPembelianMarketplacePage().validateBuyerNotes();
        });

        And("^user validate dropshipper info on detail pembelian page$", () -> {
            bukalapak.detailPembelianMarketplacePage().validateDropshipperInfo();
        });

        And("user tap Detail button in Histori Pengiriman", () -> {
            bukalapak.detailPembelianMarketplacePage().tapDetailHistoryPengiriman();
        });

        Then("^user verify (not )?show Detail History Pengiriman modal$", (String condition) -> {
            bukalapak.detailPembelianMarketplacePage().verifyDetailHisotryPengirimanModal(condition);
        });

        Then("^user verify Last Shipping History is \"([^\"]*)\"$", (String state) -> {
            bukalapak.detailPembelianMarketplacePage().verifyDetailHisotryPengirimanState(state);
        });

        Then("user verify Address Maps located in correct address", () -> {
            bukalapak.detailPembelianMarketplacePage().verifyAddressInPetaLokasiPelapak();
        });
    }
}
