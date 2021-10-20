package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 9/1/19.
 */
public class CreditCardPage extends BasePage {

    public CreditCardPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnCreditCardPage() {
        waitForVisibilityOf("credit_card_amount_text", 50);
        verifyElementExist("credit_card_page_title");
        HelperData.setLastActionPage(new CreditCardPage(iosDriver));
    }

    public void verifyTotalPaymentIsMatch() {
        if (isElementVisible("credit_card_jenius_text")) {
            assertEquals(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""), String.valueOf(getIntegerFromTextElement("credit_card_amount_jenius_text")/100), "Jumlah transaksi tidak sama!");
        } else {
            assertEquals(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""), String.valueOf(getIntegerFromTextElement("credit_card_amount_text")/100), "Jumlah transaksi tidak sama!");
        }
    }

    public void verifyMasterCardPaymentIsMatch() {
        waitForVisibilityOf("credit_card_mastercard_amount_text", 50);
        String amountText = getElementLabel("credit_card_mastercard_amount_text");
        amountText = amountText.replaceAll("[^0-9]", "");
        assertTrue(TransactionData.getTotalPrice().replaceAll("[^0-9]", "").equals(amountText.substring(0, amountText.length() - 2)));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
