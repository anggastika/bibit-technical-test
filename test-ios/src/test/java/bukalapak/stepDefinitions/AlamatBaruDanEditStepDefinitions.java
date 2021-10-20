package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AlamatBaruDanEditStepDefinitions extends TestInstrument implements En {

    public AlamatBaruDanEditStepDefinitions() {

        Given("user is in \"Edit Alamat\" page", () -> {
            bukalapak.alamatBaruDanEditPage().userOnAlamatBaruPage();
        });

        And("user is on Alamat Baru page", () -> {
            bukalapak.alamatBaruDanEditPage().userOnAlamatBaruPage();
        });

        And("user select a Label Alamat \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().selectLabelAlamat(arg0);
        });

        And("user fill label alamat with \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().fillLebelAlamat(arg0);
        });

        And("user fill no telepon with \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().fillnoTlp(arg0);
        });

        And("user select Provinsi \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().chooseProvinsi(arg0);
        });

        And("user select Kota Kabupaten \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().chooseKota(arg0);
        });

        And("user select Kecamatan \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().chooseKecamatan(arg0);
        });

        And("user select KodePos \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().chooseKodePos(arg0);
        });

        And("user write Alamat Lengkap \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().fillAlamatLengkap(arg0);
        });

        And("user tap tambah on alamat page", () -> {
            bukalapak.alamatBaruDanEditPage().clickTambahButtonAlamat();
        });

        And("user edit Alamat Lengkap with \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().fillEditFullAddress(arg0);
        });

        And("user search location \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatBaruDanEditPage().searchAlamatInMap(arg0);
        });

        When("user save new address", () -> {
            bukalapak.alamatBaruDanEditPage().tapSimpanButton();
        });

        When("user search region \"([^\"]*)\" in internasional address", (String region) -> {
            bukalapak.alamatBaruDanEditPage().searchRegion(region);
        });

        When("user input (.*) as new zip code", (String zipcode) -> {
            bukalapak.alamatBaruDanEditPage().inputRandomZipCode(zipcode);
        });

        Then("^user validate message \"([^\"]*)\" on edit alamat page$", (String warningMessage) -> {
            bukalapak.alamatBaruDanEditPage().validateWarningMessage(warningMessage);
        });

        Then("^validate geocoder information \"([^\"]*)\"$", (String region) -> {
            bukalapak.alamatBaruDanEditPage().validateGeocoderInformation(region);
        });
    }
}
