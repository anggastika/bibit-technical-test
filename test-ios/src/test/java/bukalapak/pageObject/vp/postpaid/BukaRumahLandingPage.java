package bukalapak.pageObject.vp.postpaid;

import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.PostpaidBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaRumahLandingPage extends PostpaidBasePage {
    public BukaRumahLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnBukaRumahLandingPage() {
        changeContext().toWebview();
        verifyElementExist("postpaid_buka_rumah_header_text", 25, "Landing page can't to loaded");
        verifyElementExist("postpaid_buka_rumah_pilih_daerah_dropdown");
        verifyElementExist("postpaid_buka_rumah_tipe_properti_rumah_button");
        verifyElementExist("postpaid_buka_rumah_tipe_properti_apartemen_button");
        verifyElementExist("postpaid_buka_rumah_tipe_properti_ruko_button");
        HelperData.setLastActionPage(new BukaRumahLandingPage(iosDriver));
    }

    public void tapOnTipePropertyDropdown() {
        tapElement("postpaid_buka_rumah_pilih_daerah_dropdown");
    }

    public void chooseCityArea(int index) {
        verifyElementExist(constructLocator("postpaid_buka_rumah_header_text"), 25, "City not found");
        PostpaidData.setArea(getTextFromElement("postpaid_buka_rumah_list_area_text", index));
        tapElements("postpaid_buka_rumah_list_area", index);
    }

    public void tapOnRiwayatButton() {
        tapElement("postpaid_buka_rumah_riwayat_transaksi_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
