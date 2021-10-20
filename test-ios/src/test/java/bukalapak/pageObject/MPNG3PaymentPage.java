package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.SocietyData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MPNG3PaymentPage extends BasePage {

    public MPNG3PaymentPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateBillCode() {
        isElementVisible("mpng3_checkout_billcode");
        assertEquals(getElementValue("mpng3_checkout_billcode"),
                TestInstrument.dotenv.get("MPNG3_BILL_DJP"));
    }

    public void selectTransferMethod() {
        swipeDownToElement("detail_pembelian_transfer_text");
        tapElement("detail_pembelian_transfer_text");
    }

    public void validateNominalPayment() {
        swipeDownToElement("mpng3_total_payment");
        assertEquals(SocietyData.getNominalTransactionMPNG3(),
                getElementValue("mpng3_total_payment"));
        HelperData.setLastActionPage(new MPNG3PaymentPage(iosDriver));
    }

    public void goToHomePage() {
        tapElement("back_button");
        while(isElementVisible("alchemy_navbar_back_button")) {
             tapElement("alchemy_navbar_back_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
