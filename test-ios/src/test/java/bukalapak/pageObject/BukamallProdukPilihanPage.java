package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukamallProdukPilihanPage extends BasePage {

    public BukamallProdukPilihanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnProdukPilihanPage() {
        verifyElementExist("bukamall_produk_pilihan_title");
        HelperData.setLastActionPage(new BukamallProdukPilihanPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("base_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
