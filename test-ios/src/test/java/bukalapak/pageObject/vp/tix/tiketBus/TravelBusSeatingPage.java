package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.data.BusData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 04/01/19, Fri
 **/
public class TravelBusSeatingPage extends BasePage {

    public TravelBusSeatingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnSeatingPage() {
        waitForVisibilityOf("bus_seating_terisi_label", 30);
        verifyElementDisplayed("bus_seat");
        verifyElementDisplayed("bus_seating_kosong_label");
    }

    public void tapOnBusSeat(String tripType) {
        validateOnSeatingPage();

        if (tripType.equals("departure")) {
            BusData.setBusSeatNumber(getElementValue("bus_seat"));
        } else {
            BusData.setBusReturnSeatNumber(getElementValue("bus_seat"));
        }

        tapElement("bus_seat");
    }

    public void tapOnLanjutButton() {
        waitForVisibilityOf("bus_button_lanjut", 3);
        tapElement("bus_button_lanjut");
    }

    public void validatePopupVerifyAccountBus() {
        waitForVisibilityOf("bus_seating_verifikasi_dulu_label", 10);
        verifyElementDisplayed("bus_seating_verifikasi_dulu_label");

        HelperData.setLastActionPage(new TravelBusSeatingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
