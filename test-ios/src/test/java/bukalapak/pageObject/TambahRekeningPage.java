package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TambahRekeningPage extends BasePage {

    public TambahRekeningPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnTambahRekeningStepsPage() {
        waitForVisibilityOf("rekening_tambah_rekening_page_title",60);
        HelperData.setLastActionPage(new TambahRekeningPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
