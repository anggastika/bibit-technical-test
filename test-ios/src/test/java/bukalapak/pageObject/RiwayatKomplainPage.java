package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class RiwayatKomplainPage extends BasePage {

    public RiwayatKomplainPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnRiwayatKomplainPage() {
        waitForVisibilityOf("riwayat_komplain_title");
        waitForVisibilityOf("riwayat_komplain_list_item");
        waitForVisibilityOf("riwayat_komplain_bukabantuan_button");
        HelperData.setLastActionPage(new RiwayatKomplainPage(iosDriver));
    }

    public void tapOnBukaBantuanButton() {
        tapElement("riwayat_komplain_bukabantuan_button");
    }

    public void tapOnFirstKomplain() {
        tapElements("riwayat_komplain_list_item", 0, Direction.UP);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateTotalKomplain(int total) {
        int komplainCardsNumber = getElementsPresent("riwayat_komplain_list_item").size();
        validateValue().equals(total, komplainCardsNumber);
    }
}
