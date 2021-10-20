package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandPDPPage extends BasePage {

    public BukamallBrandPDPPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBrandPDP() {
        verifyElementExist("bukamall_pdp_beli");
        HelperData.setLastActionPage(new BukamallBrandPDPPage(iosDriver));
    }

    public void userOnBrandPDPAlchemy() {
        if (!isElementVisible("bukamall_pdp_beli_sekarang_button")) {
            verifyElementExist("bukamall_pdp_preorder_sekarang_button");
        }
        HelperData.setLastActionPage(new BukamallBrandPDPPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_base_back_buttonbutton");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
