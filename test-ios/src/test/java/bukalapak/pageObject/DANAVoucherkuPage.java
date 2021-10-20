package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


/**
 * Created by Denis Hendriansah on 20/09/2019.
 */
public class DANAVoucherkuPage extends BasePage {

    public DANAVoucherkuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void tapFirstDANAVoucher() {
        if (isElementVisible("voucherku_dana_filter")) {
            tapElement("voucherku_dana_filter");
            if (isElementVisible("voucherku_dana_first_voucher")) {
                tapElement("voucherku_dana_first_voucher");
            } else {
                LogUtil.info("DANA voucher should be expired");
            }
        } else {
            LogUtil.info("DANA tab should be disappeared");
        }
    }

    public void validateDetailVoucherkuDANA() {
        String voucherDetail = API_CALL.getDANAPocketDetails();
        int voucherTotal = API_CALL.getDANAPocketTotal();
        if (voucherTotal != 0) {
            assertTrue(getElementValue("voucherku_dana_label").contains(voucherDetail),
                    "Voucher not matched");
        } else {
            tapFirstDANAVoucher();
        }
        HelperData.setLastActionPage(new DANAVoucherkuPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
