package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaIklanReportPage extends BasePage {

    public BukaIklanReportPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBukaIklanReportPage() {
        verifyElementExist("bukaiklan_rincian_anggaran_button");
        verifyElementExist("bukaiklan_kinerja_iklan_button");
        verifyElementExist("bukaiklan_kinerja_iklan_produk_button");
        HelperData.setLastActionPage(new BukaIklanReportPage(iosDriver));
    }

    public void userOnBukaIklanReportNotSubscribedPage() {
        verifyElementExist("bukaiklan_not_subscribed_image");
        verifyElementExist("bukaiklan_not_subscribed_text");
        HelperData.setLastActionPage(new BukaIklanReportPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("bukaiklan_back_button");
        if (!isElementVisible("bukaiklan_pelajari_button")) {
            tapElement("bukaiklan_back_button");
        }
        tapElement("bukaiklan_back_button");
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
