package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelTrainSeatingPage extends BasePage {

    public TravelTrainSeatingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isOnChooseSeatingPage() {
        assertTrue(isElementVisible("train_pilih_gerbong_label"));
    }

    public void tapOnPilihKursiButton() {
        swipeDownToElement("train_button_pilih_kursi");
        tapElement("train_button_pilih_kursi");
    }
}
