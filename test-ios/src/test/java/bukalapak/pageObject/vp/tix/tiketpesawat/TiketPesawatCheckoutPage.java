package bukalapak.pageObject.vp.tix.tiketpesawat;

import bukalapak.data.vp.VpTransactionData;
import bukalapak.data.vp.tix.TiketPesawatData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TiketPesawatCheckoutPage extends VpBasePage {

    public TiketPesawatCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTotalPrice() {
        int expectedTotalPrice = getIntFromRp(TiketPesawatData.getTotalPrice()) + getIntFromRp(VpTransactionData.getDiscountAmount());
        int actualTotalPrice = getIntFromRp(getText("VP_PESAWAT_CHECKOUT_TOTAL_PEMBAYARAN"));

        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    public void validateBookingDetail() {
        validateElementVisible(constructLocator("VP_PESAWAT_CHECKOUT_AIRLINE_DEPARTURE", TiketPesawatData.getSelectedDepartureAirline()));

        if (TiketPesawatData.isRoundTrip()) {
            validateElementVisible(constructLocator("VP_PESAWAT_CHECKOUT_AIRLINE_RETURN", TiketPesawatData.getSelectedReturnAirline()));
        }
    }
}
