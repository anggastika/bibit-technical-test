package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PilihNomorKartuPage extends BasePage {

    public PilihNomorKartuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPilihNomorKartuPage() {
        waitForVisibilityOf("pilih_nomor_kartu_title_text", 5);
        HelperData.setLastActionPage(new PilihNomorKartuPage(iosDriver));
    }

    public void selectCreditCard(String creditCardNumber) {
        String selectedCreditCardElement = "labelcontains_" + creditCardNumber;
        tapElement(selectedCreditCardElement);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
