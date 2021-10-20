package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandTerbaruAlchemyPage extends BasePage {

    public BukamallBrandTerbaruAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallBrandTerbaruAlchemyPage() {
        verifyElementExist("bukamall_brand_terbaru_title");
        verifyElementExist("bukamall_brand_terbaru_alchemy_brand_image");
        HelperData.setLastActionPage(new BukamallBrandTerbaruAlchemyPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
