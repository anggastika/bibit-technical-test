package bukalapak.pageObject;

import bukalapak.data.SocietyData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


import static bukalapak.TestInstrument.dotenv;

public class MPNG3InquiryPage extends BasePage {

    public MPNG3InquiryPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateBillCode() {
        isElementVisible("mpng3_inquiry_billcode");
        assertEquals(getElementValue("mpng3_inquiry_billcode"), dotenv.get("MPNG3_BILL_DJP"));
    }

    public void setNominalTransaction() {
        swipeDownToElement("detail_pembelian_bayar_sekarang_button");
        SocietyData.setNominalTransactionMPNG3(getElementValue("mpng3_nominal_payment"));
    }

    public void validateTotalPayment() {
        assertEquals(SocietyData.getNominalTransactionMPNG3(),
                getElementValue("mpng3_total_payment"));
    }

    public void tapConfirmInquiryMPNG3() {
        tapElement("detail_pembelian_bayar_sekarang_button");
        HelperData.setLastActionPage(new MPNG3InquiryPage(iosDriver));
    }

    public void goToHomePage() {
        while(isElementVisible("alchemy_navbar_back_button")) {
             tapElement("alchemy_navbar_back_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
