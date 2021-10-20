package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiSepedaLandingPage extends BasePage {

    public AsuransiSepedaLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        changeContext().toWebview();
        verifyElementExist("asuransi_sepeda_landing_page_title_text");
        verifyElementExist("asuransi_sepeda_landing_page_header_text");
    }

    public void tapOnBeliSepedaButton() {
        tapElement("asuransi_sepeda_landing_page_beli_sepeda_button");
        HelperData.setLastActionPage(new AsuransiSepedaLandingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
