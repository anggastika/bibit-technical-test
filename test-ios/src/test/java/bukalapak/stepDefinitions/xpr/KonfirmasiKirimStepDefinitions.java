package bukalapak.stepDefinitions.xpr;

import bukalapak.TestInstrument;
import bukalapak.data.XPRData;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

public class KonfirmasiKirimStepDefinitions extends TestInstrument implements En {

    public KonfirmasiKirimStepDefinitions() {

        Then("user is in \"Confirmation shipping\" page", () -> {
            bukalapak.konfirmasiKirimPage().userOnKonfirmasiKirimPage();
        });

        Then("^user validate courier service is \"([^\"]*)\"( and manual)?$", (String courier, String isManual) -> {
            bukalapak.konfirmasiKirimPage().validateCourierService(courier, isManual);
        });

        Then("user validate receipt type of courier \"([^\"]*)\"", (String courier) -> {
            bukalapak.konfirmasiKirimPage().validateOptionRecipt(courier);
        });

        Then("user validate shipping type of courier \"([^\"]*)\"", (String courier) -> {
            bukalapak.konfirmasiKirimPage().validateShippingType(courier);
        });

        Then("user choose \"([^\"]*)\" option", (String shippingType) -> {
            bukalapak.konfirmasiKirimPage().tapShippingType(shippingType);
        });

        Then("user validate alamat penjemput \"([^\"]*)\" for kurir jemput barang", (String address) -> {
            bukalapak.konfirmasiKirimPage().validateAlamatPenjemputan(address);
        });

        Then("user validate shipping steps for (.*)", (String shippingType, DataTable langkahLangkah) -> {
            bukalapak.konfirmasiKirimPage().validateHowToShipping(langkahLangkah);
        });

        Then("user validate label of generate booking button \"([^\"]*)\"", (String labelButton) -> {
            bukalapak.konfirmasiKirimPage().validateGenerateBooking(labelButton);
        });

        Then("^user validate note for (automatic|manual) reciept \"([^\"]*)\"$", (String type, String note) -> {
            bukalapak.konfirmasiKirimPage().validateNoteKonfirmationPage(type, note);
        });

        Then("user validate alamat pengambilan \"([^\"]*)\" for on demand courier", (String alamat) -> {
            bukalapak.konfirmasiKirimPage().validateAlamatPengambilan(alamat);
        });

        Then("^user validate term and condition for (on demand courier|ambil sendiri)$", (String type, DataTable tnc) -> {
            bukalapak.konfirmasiKirimPage().validateTermAndCondition(type, tnc);
        });

        Then("user validate manual receipt \"([^\"]*)\" is displayed", (String kodeBooking) -> {
            bukalapak.konfirmasiKirimPage().validateManualResiField(kodeBooking);
        });

        When("user input unique code of ambil sendiri", () -> {
            bukalapak.konfirmasiKirimPage().inputKodeUnikAmbilSendiri(XPRData.getKodeUnikAmbilSendiri());
        });

        When("user proceed unique code of ambil sendiri", () -> {
            bukalapak.konfirmasiKirimPage().clickProsesUniqueCodeAmbilSendiri();
        });

        Then("^user validate educational banner for (Kurir Jemput Barang|Datangi Kantor Kurir)$", (String type, DataTable itemBanner) -> {
            bukalapak.konfirmasiKirimPage().validateBanner(type, itemBanner);
        });

        Then("^user validate title education banner \"([^\"]*)\" is (not )?displayed$", (String titleBanner, String status) -> {
            bukalapak.konfirmasiKirimPage().validateTitleEducationBanner(status, titleBanner);
        });
    }
}
