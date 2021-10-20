package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilChatPage extends BasePage {
    public BukaMobilChatPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnBukaMobilChat() {
        if (isElementVisible("BUKAMOBIL_LIVECHAT_POPUP_INFO_1", 10)) {
            skipPopupInformation();
        }
        validateEnabled("BUKAMOBIL_LIVECHAT_PAGE_TITLE");
        validateExist("BUKAMOBIL_LIVECHAT_CHAT_FIELD");
        HelperData.setLastActionPage(new BukaMobilPage(iosDriver));
    }

    private void skipPopupInformation() {
        tapElement("BUKAMOBIL_LIVECHAT_LANJUT_BTN");
        verifyElementDisplayed("BUKAMOBIL_LIVECHAT_POPUP_INFO_2");
        tapElement("BUKAMOBIL_LIVECHAT_LANJUT_BTN");
        verifyElementDisplayed("BUKAMOBIL_LIVECHAT_POPUP_INFO_3");
        tapElement("BUKAMOBIL_LIVECHAT_MENGERTI_BTN");
    }
}
