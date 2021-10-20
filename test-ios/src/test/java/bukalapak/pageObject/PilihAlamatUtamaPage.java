package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PilihAlamatUtamaPage extends BasePage {

    public PilihAlamatUtamaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPilihAlamatUtamaPage() {
        verifyElementExist("pilih_alamat_utama_title");
        HelperData.setLastActionPage(new PilihAlamatUtamaPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("pilih_alamat_utama_cross_button");
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
