package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class HistoryPage extends BasePage {

    public HistoryPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void checkUserOnHistoryPage(String page) {
        switch (page) {
            case ("omni"):
                verifyElementExist("history_omni_title");
                verifyElementExist("history_omni_product");
                break;
            case ("discover"):
                verifyElementExist("history_discover_title");
                verifyElementExist("history_discover_product");
                break;
            default:
                break;
        }
        HelperData.setLastActionPage(new HistoryPage(iosDriver));
    }

    public void clickProductHistory(String page) {
        switch (page) {
            case ("omni"):
                tapElement("history_omni_product");
                break;
            case ("discover"):
                tapElement("history_discover_product");
                break;
            default:
                break;
        }
        HelperData.setLastActionPage(new HistoryPage(iosDriver));
    }
}
