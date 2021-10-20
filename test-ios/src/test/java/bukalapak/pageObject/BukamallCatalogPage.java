package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallCatalogPage extends BasePage {

    public BukamallCatalogPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallCatalogPage() {
        verifyElementExist("bukamall_catalog_dalam_negeri_tab");
        verifyElementExist("bukamall_catalog_search_field");
        verifyElementNotExist("bukamall_brand_pilihan_text");
        verifyElementNotExist("bukamall_brand_terbaru_text");
        HelperData.setLastActionPage(new BukamallCatalogPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
