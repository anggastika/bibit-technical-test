package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandProductSearchResultPage extends BasePage {

    public BukamallBrandProductSearchResultPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallBrandProductSearchResultPage() {
        verifyElementExist("bukamall_search_result_brand_tab");
        verifyElementExist("bukamall_search_result_barang_tab");
        verifyElementExist("bukamall_search_result_category_text");
        HelperData.setLastActionPage(new BukamallBrandProductSearchResultPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
