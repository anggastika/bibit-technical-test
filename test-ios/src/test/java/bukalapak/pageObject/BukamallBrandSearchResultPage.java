package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallBrandSearchResultPage extends BasePage {

    public BukamallBrandSearchResultPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukamallBrandSearchResultPage() {
        verifyElementExist("bukamall_search_result_brand_tab");
        verifyElementExist("bukamall_search_result_barang_tab");
        verifyElementExist("bukamall_search_result_berlangganan_link");
        verifyElementExist("bukamall_search_result_brand_image");
        HelperData.setLastActionPage(new BukamallBrandSearchResultPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
