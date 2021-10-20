package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 9/1/19.
 */
public class CreditCardListPage extends BasePage {

    public CreditCardListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnCreditCardListPage() {
        waitForVisibilityOf("credit_card_list_page_title",20);
        HelperData.setLastActionPage(new CreditCardListPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userSelectCreditCardWithNumber(String number) {
        tapElement(constructLocator("credit_card_list_credit_card_number_text",number));
        waitForVisibilityOf("credit_card_list_gunakan_metode_ini_button",10);
        tapElement("credit_card_list_gunakan_metode_ini_button");
    }

    public void tapOnTambahKartuBaruButton() {
        waitForVisibilityOf("credit_card_list_tambah_kartu_baru_button", 15);
        tapElement("credit_card_list_tambah_kartu_baru_button");
    }
}
