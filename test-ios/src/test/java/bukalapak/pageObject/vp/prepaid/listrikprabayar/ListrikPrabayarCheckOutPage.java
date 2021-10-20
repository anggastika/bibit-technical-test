package bukalapak.pageObject.vp.prepaid.listrikprabayar;

import bukalapak.data.vp.prepaid.ListrikPrabayarData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ListrikPrabayarCheckOutPage extends VpCheckOutPage {

    public ListrikPrabayarCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        waitForVisibilityOf("vp_check_out_rincian_informasi_text_id_pelanggan", MEDIUM_TIMEOUT);
        validateDisplayed("vp_check_out_rincian_informasi_text_id_pelanggan");
        validateElementWithText("vp_check_out_rincian_informasi_text_id_pelanggan", ListrikPrabayarData.getCustomerId());
        validateElementWithText("vp_check_out_rincian_informasi_text_no_meter", ListrikPrabayarData.getMeterNumber());
        validateElementWithText("vp_check_out_rincian_informasi_text_nama_lengkap", ListrikPrabayarData.getFullName());
        validateElementWithText("vp_check_out_rincian_informasi_text_tarif_daya", ListrikPrabayarData.getPower());
        validateElementWithText("vp_check_out_rincian_informasi_text_nilai_token", ListrikPrabayarData.getDenominationName());
    }

    @Override
    public void validatePaymentAmount() {
        swipeUpToElement("vp_check_out_rincian_harga_text_harga");
        validateElementWithText("vp_check_out_rincian_harga_text_harga", ListrikPrabayarData.getDenominationPrice());
        super.validatePaymentAmount();
        swipeDownToElement("vp_check_out_rincian_informasi_text_nilai_token");
    }
}
