package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerBonusPage extends BasePage{
    public SuperSellerBonusPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    public void userOnSuperSellerBonus() {
        verifyElementExist("super_seller_bonus_title");
        HelperData.setLastActionPage(new SuperSellerBonusPage(iosDriver));
    }

    public void userShownBonusDiskonInfo() {
        verifyElementExist("tambahan_budget_prom_text");
        verifyElementNotExist("hapus_feedback_text");
        verifyElementNotExist("bonus_kuota_label_text");
        verifyElementNotExist("bonus_voucher_lapak_text");
        verifyElementNotExist("cairkan_bukadompet_text");
        HelperData.setLastActionPage(new SuperSellerBonusPage(iosDriver));
    }

    public void userShownBonusFiturInfo() {
        verifyElementNotExist("tambahan_budget_prom_text");
        verifyElementExist("hapus_feedback_text");
        verifyElementExist("bonus_kuota_label_text");
        verifyElementExist("bonus_voucher_lapak_text");
        verifyElementExist("cairkan_bukadompet_text");
        HelperData.setLastActionPage(new SuperSellerBonusPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
