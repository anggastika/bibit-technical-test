package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelTrainInvoiceDetailPage extends BasePage {
    public TravelTrainInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTrainData(String tripType) {
        if (tripType.equals("departure")) {
            swipeUpToElement("train_detail_penumpang_label");
            String trainDepartureName = TiketKeretaData.getDepartureTrainName();
            String trainDepartureClass = TiketKeretaData.getTrainClassDeparture();
            assertEquals(trainDepartureName + " | " + trainDepartureClass, getTextFromElement("train_detail_invoice_train_departure_detail_text"));

        } else {
            swipeUpToElement("train_detail_invoice_partner_information");
            String trainReturnName = TiketKeretaData.getReturnTrainName();
            String trainReturnClass = TiketKeretaData.getTrainClassReturn();
            verifyElementExist(constructLocator("train_detail_invoice_train_detail_text", trainReturnName + " | " + trainReturnClass));
        }

        HelperData.setLastActionPage(new TravelTrainInvoiceDetailPage(iosDriver));
    }

    public void tapInfoPassengerSection() {
        swipeUpToElement("train_nama_pemesan_label");
        tapElement("train_info_pemesanan_label");
    }

    public void tapDepartureTripSection() {
        tapElement("train_pergi_label");
    }

    public void validateTrainTicketTotalPriceText(String price) {
        if (isElementVisible("invoice_non_marketplace_kereta_total_pembelian")) {
            assertEquals(price, getTextFromElement("invoice_non_marketplace_kereta_total_pembelian"));
        } else {
            assertEquals(price, getTextFromElement("invoice_non_marketplace_kereta_total_pesanan"));
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
