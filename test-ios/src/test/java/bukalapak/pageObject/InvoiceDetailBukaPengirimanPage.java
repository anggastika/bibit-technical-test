package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * @Author: faida royani
 * @Date: 17 jan 2019
 **/

public class InvoiceDetailBukaPengirimanPage extends BasePage {

    public InvoiceDetailBukaPengirimanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    /**
     * This method is used to check current page
     */
    public void userOnInvoiceDetailBP() {
        verifyElementExist("invoice_non_marketplace_detail_transaksi_header");
        HelperData.setLastActionPage(new InvoiceDetailBukaPengirimanPage(iosDriver));
    }

    /**
     * This method is used to get courier name in invoice detail page
     *
     * @param courier
     * @return courier name
     */
    private String getCourierName(String courier) {
        swipeDownToElement("invoice_non_marketplace_jasa_pengiriman_text");
        return getElementValue(constructLocator("invoice_non_marketplace_courier_name", courier));
    }

    /**
     * This method is used to check courier name of buka pengiriman transaction
     *
     * @param kurir
     */
    public void vefiryCourierInInvoiceDetail(String kurir) {
        if (getCourierName(kurir).equals(kurir)) {
            assertTrue(true, "Courier is match");
        } else {
            Assert.fail("Courier is not match");
        }
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
