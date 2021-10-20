package bukalapak.pageObject.vp.insurance;

import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by ferawati h.p. on 30/06/20.
 */
public class AsuransiBepergianDetailPurchasedPage extends BasePage {

    public AsuransiBepergianDetailPurchasedPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void tapTnCCheckbox() {
        swipeUpToElement("asuransi_bepergian_detail_purchased_page_tnc_checkbox");
        tapElement("asuransi_bepergian_detail_purchased_page_tnc_checkbox", 15);
    }

    public void tapLanjutKePembayaranButton() {
        swipeUpToElement("asuransi_bepergian_detail_purchased_page_lanjutkepembayaran_button");
        tapElement("asuransi_bepergian_detail_purchased_page_lanjutkepembayaran_button", 10);
        changeContext().toNative();
    }
}
