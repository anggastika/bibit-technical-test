package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilCarSelectionPage extends BasePage {
    public BukaMobilCarSelectionPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnCarSelectionPage() {
        validateDisplayed("BUKAMOBIL_MOBILPILIHAN_PAGE_HEADER");
        validateDisplayed("BUKAMOBIL_MOBILPILIHAN_DP");
        validateDisplayed("BUKAMOBIL_MOBILPILIHAN_CAR_NAME");
        validateDisplayed("BUKAMOBIL_MOBILPILIHAN_NORMAL_PRICE");
        validateDisplayed("BUKAMOBIL_MOBILPILIHAN_FINAL_PRICE");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }
}
