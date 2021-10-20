package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class AkulakuPage extends BasePage {

    public AkulakuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnAkulakuPage() {
        waitForVisibilityOf("akulaku_page_title", 30);
        verifyElementExist("akulaku_page_title");
        HelperData.setLastActionPage(new AkulakuPage(iosDriver));
    }

    public void verifyTotalPaymentIsMatch() {
        assertTrue(TransactionData.getTotalPrice().replace("Rp", "").equals(getElementValue("akulaku_total_harga_pesanan_text")));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void allowLocationServicesAlert() {
        try {
            waitForVisibilityOf("akulaku_alert_allow_text", 30);
            tapElement("akulaku_alert_allow_text");
        } catch (Exception e){
            LogUtil.info("Alert not appear");
        }
    }

    public void authorizeLocationServicesAlert() {
        try {
            waitForVisibilityOf("akulaku_alert_ok_text", 15);
            tapElement("akulaku_alert_ok_text");
        } catch (Exception e){
            LogUtil.info("Alert not appear");
        }
    }
}
