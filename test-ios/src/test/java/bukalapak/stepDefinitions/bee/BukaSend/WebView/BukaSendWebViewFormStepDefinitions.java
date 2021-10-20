package bukalapak.stepDefinitions.bee.BukaSend.WebView;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.java.an.E;

public class BukaSendWebViewFormStepDefinitions extends TestInstrument implements En {
    public BukaSendWebViewFormStepDefinitions() {
        And("^user select sender address \"([^\"]*)\"$", (String senderAddress) -> {
            bukalapak.bukaSendWebViewFormPage().validateFormBukasendPage();
            bukalapak.bukaSendWebViewFormPage().clickSenderAddressButton();
            bukalapak.bukaSendWebViewFormPage().selectSenderAddress(senderAddress);
            bukalapak.bukaSendWebViewFormPage().clickSelectButton();
        });

        And("^user input receiver name \"([^\"]*)\"$", (String receiverName) -> {
            bukalapak.bukaSendWebViewFormPage().clickReceiverButton();
            //bukalapak.bukaSendWebViewFormPage().selectInputManual();
            bukalapak.bukaSendWebViewFormPage().inputReceiverName(receiverName);
        });

        And("^user input receiver phone no \"([^\"]*)\"$", (String phoneNo) -> {
            bukalapak.bukaSendWebViewFormPage().inputPhoneNo(phoneNo);
        });

        And("^user input postal code \"([^\"]*)\"$", (String postalCode) -> {
            bukalapak.bukaSendWebViewFormPage().inputPostalCode(postalCode);
        });

        And("^user input address \"([^\"]*)\"$", (String address) -> {
            bukalapak.bukaSendWebViewFormPage().inputAddress(address);
            bukalapak.bukaSendWebViewFormPage().clickContinueButton();
        });

        And("^user input weight package \"([^\"]*)\" kg$", (String weight) -> {
            bukalapak.bukaSendWebViewFormPage().inputWeight(weight);
        });

        And("^user input notes \"([^\"]*)\"$", (String notes) -> {
            bukalapak.bukaSendWebViewFormPage().inputNotes(notes);
        });

        And("^user choose Isi Paket \"([^\"]*)\"$", (String category) -> {
            bukalapak.bukaSendWebViewFormPage().selectIsiPaket(category);
        });

        And("^user select \"([^\"]*)\" courier$", (String type) -> {
            bukalapak.bukaSendWebViewFormPage().selectSendingType(type);
        });

        And("^user select \"([^\"]*)\"$", (String courier) -> {
            bukalapak.bukaSendWebViewFormPage().selectCourier(courier);
        });

        And("user go to summary page", () -> {
            bukalapak.bukaSendWebViewFormPage().getPriceCourier();
            bukalapak.bukaSendWebViewFormPage().getInsuranceTrx();
            bukalapak.bukaSendWebViewFormPage().goToSummaryPage();
        });

        And("all data is correct", () -> {
            bukalapak.bukaSendWebViewFormPage().validateSummaryPage();
        });

        And("user go to checkout page", () -> {
            bukalapak.bukaSendWebViewFormPage().goToCheckoutPage();
        });

        And("^user choose metode pembayaran \"([^\"]*)\"$", (String args) -> {
            bukalapak.bukaSendWebViewFormPage().chooseMetodePembayaran(args);
        });

        And("user verify price ongkir in checkout", () -> {
            bukalapak.bukaSendWebViewFormPage().validatePriceCheckout();
        });

        And("user pay order bukasend", () -> {
            bukalapak.bukaSendWebViewFormPage().clickBayarButton();
        });

        And("user verify last trx that matching ongkir value", () -> {
            bukalapak.bukaSendWebViewFormPage().clickDetailPesananButton();
            bukalapak.bukaSendWebViewFormPage().validateTrxBukaSendInvoice();
        });
    }
}
