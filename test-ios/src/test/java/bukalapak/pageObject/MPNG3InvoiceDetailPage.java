package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.SocietyData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MPNG3InvoiceDetailPage extends BasePage {

    public MPNG3InvoiceDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateBillCode() {
        isElementVisible("detail_pembelian_informasi_tagihan");
        swipeDownToElement("mpng3_invdetail_billcode");
        assertEquals(getElementValue("mpng3_invdetail_billcode"),
                TestInstrument.dotenv.get("MPNG3_BILL_DJP"));
    }

    public void validateNominalPayment() {
        swipeDownToElement("mpng3_invdetail_nominal");
        assertEquals(SocietyData.getNominalTransactionMPNG3(),
                getElementValue("mpng3_invdetail_nominal"));
        HelperData.setLastActionPage(new MPNG3InvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        while(isElementVisible("back_icon")) {
             tapElement("back_icon");
        }
        tapElement("home_blhome_tab");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
