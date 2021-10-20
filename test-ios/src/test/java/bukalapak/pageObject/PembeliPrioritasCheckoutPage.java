package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembeliPrioritasCheckoutPage extends BasePage {

    public PembeliPrioritasCheckoutPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnDetailPembelianPage() {
        waitForVisibilityOf("priority_checkout_detail_pembelian");
        verifyElementExist("priority_checkout_pembayaran");
        verifyElementExist("priority_checkout_force_bd");
        verifyElementExist("priority_checkout_metode_lain");
        HelperData.setLastActionPage(new PembeliPrioritasCheckoutPage(iosDriver));
    }

    public void userOnPembayaranPage() {
        waitForVisibilityOf("checkout_non_marketplace_pilih_metode_text");
        HelperData.setLastActionPage(new PembeliPrioritasCheckoutPage(iosDriver));
    }

    public void tickTnCAgreement() {
        tapElement("priority_checkout_checkbox");
    }

    public void selectPaymentMethod(String paymentMethod) {
        if (paymentMethod.contains("BukaDompet")) {
            tapElement("priority_checkout_bukadompet");
        } else {
            LogUtil.error("There are no such payment method");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
