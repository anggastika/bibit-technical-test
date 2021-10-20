package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PelangganPelapakPage extends BasePage{

    public PelangganPelapakPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPelangganPelapakPage() {
        waitForVisibilityOf("pelanggan_pelapak_search_box", 60);
        verifyElementExist("pelanggan_pelapak_page_title");
        HelperData.setLastActionPage(iosDriver);
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
