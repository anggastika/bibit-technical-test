package bukalapak.pageObject.vp.prepaid.pulsaprabayar;

import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PulsaPrabayarCheckOutPage extends VpCheckOutPage {
    public PulsaPrabayarCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        waitForVisibilityOf("vp_check_out_pulsa_prabayar_text_rincian_informasi_nomor_telepon");
        String phoneNumber = getText("vp_check_out_pulsa_prabayar_text_rincian_informasi_nomor_telepon");
        String pulsa = getText("vp_check_out_pulsa_prabayar_text_rincian_informasi_pulsa");

        validateValue().equals(phoneNumber.replaceAll("-", "").replaceAll(" ", ""), PulsaPrabayarData.getPhoneNumber());
        validateValue().equals(pulsa, PulsaPrabayarData.getPackageName());
    }

    @Override
    public void validatePaymentAmount() {
        swipeUpToElement("vp_check_out_rincian_harga_text_harga");
        validateElementWithText("vp_check_out_rincian_harga_text_harga", PulsaPrabayarData.getPackagePrice());
        super.validatePaymentAmount();
        swipeDownToElement("vp_check_out_pulsa_prabayar_text_rincian_informasi_pulsa");
    }
}
