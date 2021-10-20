package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiTakePhotoStepsDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiTakePhotoStepsDefinitions() {

        When("^user Take Photo Selfie KTP and Foto KTP", () -> {
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnFotoKTPButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnFotoMulaiFotoKTPButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnFotoAmbilFotoButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnGunakanFotoButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnSelfieKTPButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnFotoMulaiSelfieButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnFotoAmbilFotoButton();
            bukalapak.pembiayaanTunaiTakePhotoPage().tapOnGunakanFotoButton();
        });

        When("^user is in take photo page", () -> {
            bukalapak.pembiayaanTunaiTakePhotoPage().validateInTakePhotoPage();
            bukalapak.pembiayaanTunaiTakePhotoPage().goToHomePage();
        });
    }
}
