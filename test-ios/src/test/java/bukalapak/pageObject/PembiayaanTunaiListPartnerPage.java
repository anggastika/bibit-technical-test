package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembiayaanTunaiListPartnerPage extends BasePage {
    public PembiayaanTunaiListPartnerPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateInListPartnerPage() {
        waitForVisibilityOf("pembiayaantunai_inlistpartner_text", 10);
        verifyElementExist("pembiayaantunai_inlistpartner_text");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void selectPartner(String partner) {
        waitForVisibilityOf(constructLocator("pembiayaantunai_partner_text", partner, 10));
        tapElement(constructLocator("pembiayaantunai_partner_text", partner));
    }

    public void tapOnCheckBox() {
        waitFor(2);
        waitForVisibilityOf("pembiayaantunai_sayasetuju_text", 10);
        tapElement("pembiayaantunai_sayasetuju_text");
    }

    public void tapOnLanjutIsiForm() {
        waitFor(2);
        waitForVisibilityOf("pembiayaantunai_lanjutisi_button", 10);
        tapElement("pembiayaantunai_lanjutisi_button");
    }

}


