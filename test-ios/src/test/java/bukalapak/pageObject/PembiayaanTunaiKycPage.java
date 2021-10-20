package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class PembiayaanTunaiKycPage extends BasePage {
    public PembiayaanTunaiKycPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }


    public void tapOnSelanjutnyaKycButton() {
        waitFor(3);
        tapElement("pembiayaantunai_kyc_selanjutnya_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateInKycStep(String step) {
        String tmpStep = step;
        switch (step) {
            case "2":
                tmpStep = "Info Pribadi";
                break;
            case "3":
                tmpStep = "Alamat";
                break;
            case "4":
                tmpStep = "Info Pekerjaan";
                break;
            case "5":
                tmpStep = "Info Pendukung";
                break;
            case "6":
                tmpStep = "Info";
                break;
            default:
                Assert.fail(tmpStep + " isn't the step");
        }
        waitForVisibilityOf(constructLocator("pembiayaantunai_kyc_validate_text", tmpStep, 10));
        verifyElementExist(constructLocator("pembiayaantunai_kyc_validate_text",tmpStep));
    }

    public void validateIsShownPopUpConfirmation() {
        waitForVisibilityOf("pembiayaantunai_preview_yakirim_button", 10);
        verifyElementExist("pembiayaantunai_preview_yakirim_button");
    }
}