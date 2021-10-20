package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class InternetBankingPage extends BasePage {

    public InternetBankingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnInternetBankingPage() {
        verifyElementExist("internet_banking_page_title");
        HelperData.setLastActionPage(new InternetBankingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
