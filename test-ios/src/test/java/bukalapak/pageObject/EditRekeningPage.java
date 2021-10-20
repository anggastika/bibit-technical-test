package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class EditRekeningPage extends BasePage {

    public EditRekeningPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnEditRekeningStepsPage() {
        verifyElementExist("rekening_edit_rekening_page_title");
        HelperData.setLastActionPage(new EditRekeningPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
