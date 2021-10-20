package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PemasukanPage extends BasePage {
    public PemasukanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPage() {
        HelperData.setLastActionPage(new PemasukanPage(iosDriver));
        waitForVisibilityOf("pemasukan_title_text", 15);
    }

    public void verifyPemasukanPage(String state) {
        HelperData.setLastActionPage(new PemasukanPage(iosDriver));
        waitForVisibilityOf("pemasukan_title_text", 15);
        if (state.equalsIgnoreCase("sudah")) {
            validateRpFormat("pemasukan_total_sudah_remit_text");
            validateDisplayed("pemasukan_periode_dropdown");
        } else {
            validateRpFormat("pemasukan_total_belum_remit_text");
            validateNotDisplayed("pemasukan_periode_dropdown");
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
