package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandTerbaruPage extends BasePage {

    public BukamallBrandTerbaruPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallBrandTerbaruPage() {
        verifyElementExist("bukamall_brand_terbaru_title");
        verifyElementExist("bukamall_brand_terbaru_brand_image");
        HelperData.setLastActionPage(new BukamallBrandTerbaruPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
