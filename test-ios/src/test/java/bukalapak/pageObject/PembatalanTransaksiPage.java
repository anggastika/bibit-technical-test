package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembatalanTransaksiPage extends BasePage {

    public PembatalanTransaksiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPembatalanTransaksiPage() {
        verifyElementExist("pembatalan_transaksi_page_text");
        HelperData.setLastActionPage(new PembatalanTransaksiPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
