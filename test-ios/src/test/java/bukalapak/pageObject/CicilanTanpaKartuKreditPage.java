package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class CicilanTanpaKartuKreditPage extends BasePage {

    public CicilanTanpaKartuKreditPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnCicilanTanpaKartuKreditPage() {
        verifyElementExist("cicilan_tanpa_kartu_kredit_page_title");
        HelperData.setLastActionPage(new CicilanTanpaKartuKreditPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
