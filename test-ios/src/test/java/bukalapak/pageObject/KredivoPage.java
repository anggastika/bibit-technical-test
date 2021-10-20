package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 17/12/18.
 */
public class KredivoPage extends BasePage {

    public KredivoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKredivoPage() {
        waitForVisibilityOf("kredivo_30_hari_text", 30);
        HelperData.setLastActionPage(new KredivoPage(iosDriver));
    }

    public void verifyTotalPaymentKredivoIsMatch() {
        double biayaLayananKredivo = 0;
        if (isElementPresentWithScroll("kredivo_biaya_kredivo_text")) {
            biayaLayananKredivo = Double.parseDouble(getElementValue("kredivo_biaya_kredivo_text").replaceAll("[^0-9]", ""));
        }
        double totalPembayaranKredivo = Double.parseDouble(getElementValue("kredivo_jumlah_pinjaman_text").replaceAll("[^0-9]", ""));
        double totalTransactionPayment = Double.parseDouble(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""));
        assertTrue(Math.abs(totalPembayaranKredivo - biayaLayananKredivo - totalTransactionPayment) < .01);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
