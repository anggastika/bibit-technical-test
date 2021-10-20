package bukalapak.pageObject.vp.prepaid.paketdata;

import bukalapak.data.vp.prepaid.PaketDataData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PaketDataCheckOutPage extends VpCheckOutPage {

    public PaketDataCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        swipeDownToElement("vp_check_out_rincian_informasi_text_no_handphone");
        String actualPhoneNumber = getTextFromElement("vp_check_out_rincian_informasi_text_no_handphone").replaceAll("-", "");

        validateValue().equals(PaketDataData.getPhoneNumber(), actualPhoneNumber);
        validateElementWithText("vp_check_out_rincian_informasi_text_paket_data", PaketDataData.getDenominationName());
    }

    @Override
    public void validatePaymentAmount() {
        swipeUpToElement("vp_check_out_rincian_harga_text_harga");
        validateElementWithText("vp_check_out_rincian_harga_text_harga", PaketDataData.getDenominationPrice());
        super.validatePaymentAmount();
        swipeDownToElement("vp_check_out_rincian_informasi_text_paket_data");
    }
}
