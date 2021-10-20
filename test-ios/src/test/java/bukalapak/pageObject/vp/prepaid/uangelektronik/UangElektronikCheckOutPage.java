package bukalapak.pageObject.vp.prepaid.uangelektronik;

import bukalapak.data.vp.prepaid.UangElektronikData;
import bukalapak.pageObject.vp.base.VpCheckOutPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UangElektronikCheckOutPage extends VpCheckOutPage {

    public UangElektronikCheckOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        waitForVisibilityOf("vp_check_out_rincian_informasi_text_nomor_kartu", MEDIUM_TIMEOUT);
        validateValue().equals(
            UangElektronikData.getCardNumber(),
            getText("vp_check_out_rincian_informasi_text_nomor_kartu").replaceAll(" ", "")
        );
    }

    @Override
    public void validatePaymentAmount() {
        swipeUpToElement("vp_check_out_rincian_harga_text_biaya_admin");
        swipeUp(); // need one more swipe but no element to target

        int amount = getIntegerFromTextElement("vp_check_out_rincian_harga_text_nominal");
        int adminFee = getIntegerFromTextElement("vp_check_out_rincian_harga_text_biaya_admin");
        int denominationPrice = getIntFromRp(UangElektronikData.getDenominationPrice());

        validateValue().equalsTrue(amount + adminFee == denominationPrice);
        super.validatePaymentAmount();
        swipeDownToElement("vp_check_out_rincian_informasi_text_nomor_kartu");
    }
}
