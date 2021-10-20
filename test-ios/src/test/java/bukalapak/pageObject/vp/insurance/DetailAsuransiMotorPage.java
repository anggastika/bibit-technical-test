package bukalapak.pageObject.vp.insurance;

import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by ferawati h.p on 30/06/2020.
 */
public class DetailAsuransiMotorPage extends BasePage {

    public DetailAsuransiMotorPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapTnCChekcbox() {
        swipeUpToElement("detail_asuransimotor_tnc_checkbox");
        tapElement("detail_asuransimotor_tnc_checkbox");
    }

    public void tapLanjutKePembayaranButton() {
        tapElement("detail_asuransimotor_lanjutkepembayaran_button");
    }
}
