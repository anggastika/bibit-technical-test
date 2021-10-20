package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class InsuranceProductDetailPage extends BasePage {

    public InsuranceProductDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapOnManfaatTab() {
        waitForVisibilityOf("insurance_product_detail_manfaat_button", 10);
        tapElement("insurance_product_detail_manfaat_button");
    }

    public void tapOnCaraBeliTab() {
        tapElement("insurance_product_detail_cara_beli_button");
    }

    public void tapOnKetentuanTab() {
        tapElement("insurance_product_detail_ketentuan_button");
    }

    public void tapOnCaraKlaimTab() {
        tapElement("insurance_product_detail_cara_klaim_button");
    }

    public void tapOnBantuanTab() {
        tapElement("insurance_product_detail_bantuan_button");
    }

    public void validateButtonBeli() {
        tapElement("insurance_product_detail_beli_button");
    }

    public void tapOnTanyaJawabTab() {
        tapElement("insurance_product_detail_tanya_jawab_button");
    }

    public void tapOnKontakTab() {
        tapElement("insurance_product_detail_kontak_button");
        HelperData.setLastActionPage(new InsuranceProductDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
