package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerPilihPaketPage extends BasePage{
    public SuperSellerPilihPaketPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyInfoPilihPaketSuperSeller() {
        validateDisplayed("super_seller_pilih_paket_page_title");
        validateDisplayed("super_seller_pilih_paket_bronze_tab");
        validateDisplayed("super_seller_pilih_paket_silver_tab");
        validateDisplayed("super_seller_pilih_paket_gold_tab");
        HelperData.setLastActionPage(new SuperSellerPilihPaketPage(iosDriver));
    }

    public void chooseSuperSellerPackage(String packageName) {
        if (packageName.equalsIgnoreCase("bronze")) {
            tapElement("super_seller_pilih_paket_bronze_tab",15);
            validateDisplayed("super_seller_pilih_paket_bronze_button");
            tapElement("super_seller_pilih_paket_bronze_button",15);
        } else if (packageName.equalsIgnoreCase("silver")) {
            tapElement("super_seller_pilih_paket_silver_tab",15);
            validateDisplayed("super_seller_pilih_paket_silver_button");
            tapElement("super_seller_pilih_paket_silver_button",15);
        } else {
            tapElement("super_seller_pilih_paket_gold_tab",15);
            validateDisplayed("super_seller_pilih_paket_gold_button");
            tapElement("super_seller_pilih_paket_gold_button",15);
        }
        HelperData.setLastActionPage(new SuperSellerPilihPaketPage(iosDriver));
    }

    public void verifyPopUpGantiPaket() {
        validateDisplayed("super_seller_ganti_paket_pop_up_title");
        validateDisplayed("super_seller_ganti_paket_ok_button");
        validateDisplayed("super_seller_ganti_paket_cancel_button");
        tapElement("super_seller_ganti_paket_cancel_button",15);
        verifyInfoPilihPaketSuperSeller();
        HelperData.setLastActionPage(new SuperSellerPilihPaketPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
