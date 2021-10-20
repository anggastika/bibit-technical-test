package bukalapak.pageObject.vp.tix.tiketpesawat;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketPesawatData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TiketPesawatInvoiceDetailPage extends VpBasePage {
    public TiketPesawatInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private String getBuyerInvoiceName() {
        String buyerFirstName = TiketPesawatData.getBuyerName().split(" ")[0];
        String buyerLastName = TiketPesawatData.getBuyerName().split(" ")[1];
        return buyerFirstName + ", " + buyerLastName;
    }

    private String getPassengerInvoiceName() {
        String passengerFirstName = TiketPesawatData.getPassengerName().split(" ")[0];
        String passengerLastName = TiketPesawatData.getPassengerName().split(" ")[1];
        return "Mr. " + passengerFirstName + ", " + passengerLastName;
    }

    private void validateInvoiceDetailData(String buyerInvoiceName) {
        waitForVisibilityOf("VP_PESAWAT_INVOICE_DETAIL_STATUS_TRANSAKSI", 15);
        assertEquals("Belum Dibayar", getText("VP_PESAWAT_INVOICE_DETAIL_STATUS_TRANSAKSI"));
        assertEquals(buyerInvoiceName, getText("VP_PESAWAT_INVOICE_DETAIL_NAMA_PEMESAN"));
        assertEquals(TiketPesawatData.getBuyerEmail(), getText("VP_PESAWAT_INVOICE_DETAIL_EMAIL_PEMESAN"));
        assertEquals(TiketPesawatData.getBuyerPhoneNumber(), getText("VP_PESAWAT_INVOICE_DETAIL_NOMOR_TELEPON_PEMESAN"));
    }

    private void swipeUpToAndTapTwiceOn(String elementLabel) {
        swipeUpToElement(constructLocator("VP_PESAWAT_GENERAL_LABEL", elementLabel));
        tapElement(constructLocator("VP_PESAWAT_GENERAL_LABEL", elementLabel));
        waitFor(3); // need to add this to wait for element animation to end before tapping again
        tapElement(constructLocator("VP_PESAWAT_GENERAL_LABEL", elementLabel));
    }

    public void validateTiketPesawatData() {
        String buyerInvoiceName = getBuyerInvoiceName();
        String passengerInvoiceName = getPassengerInvoiceName();

        swipeUpToAndTapTwiceOn("Informasi Pemesan");
        validateInvoiceDetailData(buyerInvoiceName);
        swipeUpToAndTapTwiceOn("Tiket Pergi");
        validateElementVisible(constructLocator("VP_PESAWAT_INVOICE_DETAIL_AIRLINE_DEPARTURE", TiketPesawatData.getSelectedDepartureAirline()));
        if (TiketPesawatData.isRoundTrip()) {
            validateElementVisible(constructLocator("VP_PESAWAT_INVOICE_DETAIL_AIRLINE_RETURN", TiketPesawatData.getSelectedReturnAirline()));
        }

        swipeUpToElement("VP_PESAWAT_INVOICE_DETAIL_NAMA_PENUMPANG");
        assertEquals(passengerInvoiceName, getText("VP_PESAWAT_INVOICE_DETAIL_NAMA_PENUMPANG"));

        HelperData.setLastActionPage(new TiketPesawatInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
