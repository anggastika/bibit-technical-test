package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiTakePhotoPage extends BasePage {
    public PembiayaanTunaiTakePhotoPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInTakePhotoPage() {
        waitFor(2);
        waitForVisibilityOf("pembiayaantunai_fotoktp_button", 10);
        verifyElementExist("pembiayaantunai_fotoktp_button");
    }

    public void tapOnFotoKTPButton() {
        waitForVisibilityOf("pembiayaantunai_fotoktp_button", 10);
        tapElement("pembiayaantunai_fotoktp_button");
    }

    public void tapOnFotoMulaiFotoKTPButton() {
        waitForVisibilityOf("pembiayaantunai_mulaifotoktp_button", 10);
        tapElement("pembiayaantunai_mulaifotoktp_button");
    }

    public void tapOnFotoAmbilFotoButton() {
        waitFor(5);
        waitForVisibilityOf("pembiayaantunai_ambilfotoktp_button", 10);
        tapElement("pembiayaantunai_ambilfotoktp_button");
    }

    public void tapOnGunakanFotoButton() {
        waitForVisibilityOf("pembiayaantunai_gunakanfoto_button", 10);
        tapElement("pembiayaantunai_gunakanfoto_button");
        waitFor(5);
    }

    public void tapOnSelfieKTPButton() {
        waitForVisibilityOf("pembiayaantunai_selfiektp_button", 10);
        tapElement("pembiayaantunai_selfiektp_button");
    }

    public void tapOnFotoMulaiSelfieButton() {
        waitForVisibilityOf("pembiayaantunai_mulaiselfiektp_button", 10);
        tapElement("pembiayaantunai_mulaiselfiektp_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
