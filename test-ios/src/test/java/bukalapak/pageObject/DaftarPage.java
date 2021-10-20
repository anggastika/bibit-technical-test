package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by sekarayukarindra on 09/10/18.
 */
public class DaftarPage extends BasePage {

    public DaftarPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnDaftarPage() {
        verifyElementExist("daftar_page_title");
        verifyElementExist("daftar_name_text_field");
        verifyElementExist("daftar_emailphone_text_field");
        HelperData.setLastActionPage(new DaftarPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("daftar_back_button");
        tapElement("akun_onboarding_close_button");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
