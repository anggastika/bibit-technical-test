package bukalapak.stepDefinitions.bee.BukaSend.Native;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaSendStepDefinitions extends TestInstrument implements En{

    public BukaSendStepDefinitions() {
        When("user enter onboarding page BukaSend", () -> {
            bukalapak.bukaSendPage().bukaSendOnBoarding();
        });

        And("user will send package", () -> {
            bukalapak.bukaSendPage().sendPackage();
        });

        And("user fill receiver address", () -> {
            bukalapak.bukaSendPage().fillReceiverAddress();
        });

        And("user select \"([^\"]*)\" as Alamat Penerima with \"([^\"]*)\" as detail address", (String alamatPenerima, String detailAlamat) -> {
            bukalapak.bukaSendPage().inputAlamatPenerima(alamatPenerima, detailAlamat);
        });

        And("user redirect to send package page", () -> {
            bukalapak.bukaSendPage().fromAlamatToPackagePage();
        });

        And("user select \"([^\"]*)\" as sender address", (String alamatPengirim) -> {
            bukalapak.bukaSendPage().chooseAlamatPengirim(alamatPengirim);
        });

        And("user set weight \"([^\"]*)\" kg on detail package", (String weight) -> {
            bukalapak.bukaSendPage().inputWeight(weight);
        });

        And("user input \"([^\"]*)\" for package information", (String packageinfo) -> {
            bukalapak.bukaSendPage().inputPackageInfo(packageinfo);
        });

        And("user select \"([^\"]*)\" courier BukaSend", (String courier) -> {
            bukalapak.bukaSendPage().chooseCourier(courier);
        });

        And("user set receiver detail \"([^\"]*)\", \"([^\"]*)\"", (String name, String phone) -> {
            bukalapak.bukaSendPage().fillReceiverDetail(name, phone);
        });

        Then("user see the shipping list have correct data and price \"([^\"]*)\" with total price \"([^\"]*)\"", (String price, String totalPrice) -> {
            bukalapak.bukaSendPage().validateShippingList(price, totalPrice);
        });

        And("user redirect to checkout page", () -> {
            bukalapak.bukaSendPage().validateCheckoutPage();
        });

        And("user back to home from Bukasend", () -> {
            bukalapak.bukaSendPage().fromCheckoutBukaSendToHome();
        });

        And("user add another package", () -> {
            bukalapak.bukaSendPage().addMorePackage();
        });

        //Cek Tarif BukaSend
        When("user tap on Cek Tarif di Sini on BukaSend", () -> {
            bukalapak.bukaSendPage().tapCekTarifDiSini();
        });

        And("user input \"([^\"]*)\" on Cari daerah", (String lokasi) -> {
            bukalapak.bukaSendPage().inputLokasi(lokasi);
        });

        And("user input weight \"([^\"]*)\" kg", (String setWeightTarif) -> {
            bukalapak.bukaSendPage().setWeightPackageTarif(setWeightTarif);
        });

        Then("user will see result cek tarif", () -> {
            bukalapak.bukaSendPage().seeResultTarif();
        });

        And("user exit from BukaSend", () -> {
            bukalapak.bukaSendPage().userExit();
        });

        When("user wait kirim sekaligus button exist and click", () -> {
           bukalapak.bukaSendPage().validateAndClickButton();
        });
      
        Then("user see description that feature only available on web", () -> {
            bukalapak.bukaSendPage().seeDescriptionCSV();
        });

        And("user will see destination location text", () -> {
            bukalapak.bukaSendPage().seeDestinationLocation();
        });

        And("^user tap on Ajukan kerja sama on BukaSend$", () -> {
            bukalapak.bukaSendPage().tapAjukanKerjaSama();
        });

        Then("^user see failed message$", () -> {
            bukalapak.bukaSendPage().seeFailedMessage();
        });

        And("user tap siap button and back to landing page BukaSend", () -> {
            bukalapak.bukaSendPage().tapAndBackToLandingPage();
        });

        And("user select based on \"([^\"]*)\"", (String sendingType) -> {
            bukalapak.bukaSendPage().setSendingType(sendingType);
        });

        And("user input full address \"([^\"]*)\"", (String alamatPenerima) -> {
            bukalapak.bukaSendPage().inputAlamatPenerimaManual(alamatPenerima);
        });

        When("user tap name and type \"([^\"]*)\"", (String namePartner) -> {
            bukalapak.bukaSendPage().tapAndInputNamePartner(namePartner);
        });

        And("user tap corporate and type \"([^\"]*)\"", (String nameCorporate) -> {
            bukalapak.bukaSendPage().tapAndInputNameCorporate(nameCorporate);
        });

        And("user tap phone and type \"([^\"]*)\"", (String phonePartner) -> {
            bukalapak.bukaSendPage().tapAndInputPhonePartner(phonePartner);
        });

        And("user tap keterangan and type \"([^\"]*)\"", (String inputKeterangan) -> {
            bukalapak.bukaSendPage().tapAndInputKeterangan(inputKeterangan);
        });

        Then("^user see success message and proceed$", () -> {
            bukalapak.bukaSendPage().seeSuccessMessage();
        });

        And("^user will tap lanjut kirim barang button$", () -> {
            bukalapak.bukaSendPage().tapLanjutKirimButton();
        });
    }
}
