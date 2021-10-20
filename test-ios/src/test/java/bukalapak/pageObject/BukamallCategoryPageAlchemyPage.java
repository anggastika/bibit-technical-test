package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallCategoryPageAlchemyPage extends BasePage {

    public BukamallCategoryPageAlchemyPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallCategoryPageAlchemy() {
        verifyElementExist("bukamall_category_brand_pilihan_text");
        verifyElementNotExist("bukamall_alchemy_produk_pilihan_text");
        verifyElementNotExist("bukamall_alchemy_brand_terbaru_text");
        HelperData.setLastActionPage(new BukamallCategoryPageAlchemyPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("bukamall_category_button_alchemy");
        tapElement("bukamall_populer_category_list");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
