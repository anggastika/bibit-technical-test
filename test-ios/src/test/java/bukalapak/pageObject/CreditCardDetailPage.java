package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.InvalidElementStateException;

/**
 * Created by NurdianSetyawan on 9/1/19.
 */
public class CreditCardDetailPage extends BasePage {

    public CreditCardDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnCreditCardDetailPage() {
        waitForVisibilityOf("credit_card_detail_nomor_kartu_textfield", 30);
        verifyElementExist("credit_card_detail_page_title");
        HelperData.setLastActionPage(new CreditCardDetailPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("credit_card_detail_close_button");
        tapElement("checkout_marketplace_back_button");
        tapElement("popup_alert_ya_button");
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void typeCVV(String cvv) {
        try {
            typeAndEnterValueWithTimeOut("credit_card_detail_cvv_textfield", cvv);
        } catch (InvalidElementStateException e) {
            typeAndEnterValueWithTimeOut("credit_card_detail_cvv_non_binding_card_textfield", cvv);
        }
    }

}
