package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import bukalapak.data.HelperData;

public class ClaimRewardsOutsideAppsPage extends BasePage {

    public ClaimRewardsOutsideAppsPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyHasClaimed() {
        changeContext().toWebview();
        verifyElementExist("lihat_hadiah_btn",5,"Lihat hadiah button doesn't exist");
    }

    public void tapOnLihatHadiah() {
        webViewTapOnElement("lihat_hadiah_btn");
        changeContext().toNative();
    }

    public void verifyOnPromoPage() {
        verifyElementExist("promo_page_title");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
