package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerEngagementPage extends BasePage {

    public SuperSellerEngagementPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void verifyStatusSuperSellerInfo() {
        waitForVisibilityOf("welcome_message_title",15);
        verifyElementExist("welcome_message_txt");
        validateDisplayed("lihat_fitur_baru_btn");
        HelperData.setLastActionPage(new SuperSellerEngagementPage(iosDriver));
    }

    private void verifyPerformaLapakInfo() {
        waitForVisibilityOf("performa_lapak_info_title",15);
        verifyElementExist("performa_lapak_info_txt");
        validateDisplayed("kembali_btn");
        validateDisplayed("lanjut_btn");
        HelperData.setLastActionPage(new SuperSellerEngagementPage(iosDriver));
    }

    private void verifyBonusSuperSellerInfo() {
        waitForVisibilityOf("bonus_melimpah_title",15);
        verifyElementExist("bonus_melimpah_txt");
        validateDisplayed("kembali_btn");
        validateDisplayed("lanjut_btn");
        HelperData.setLastActionPage(new SuperSellerEngagementPage(iosDriver));
    }

    private void verifyMutasiSuperSellerInfo() {
        waitForVisibilityOf("mutasi_super_seller_title",15);
        verifyElementExist("mutasi_super_seller_txt");
        validateDisplayed("kembali_btn");
        validateDisplayed("selesai_btn");
        HelperData.setLastActionPage(new SuperSellerEngagementPage(iosDriver));
    }

    public void userShownInfo(String info) {
        if (info.equals("status super seller")) {
            verifyStatusSuperSellerInfo();
        } else if (info.equals("performa lapak")) {
            verifyPerformaLapakInfo();
        } else if (info.equals("bonus super seller")) {
            verifyBonusSuperSellerInfo();
        } else if (info.equals("mutasi super seller")) {
            verifyMutasiSuperSellerInfo();
        } else {
            LogUtil.warn("Wrong option! Please select the option above!");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
