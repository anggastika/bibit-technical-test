package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class DaftarPembelianPremiumPage extends BasePage {

    public DaftarPembelianPremiumPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnDaftarPembelianPremiumPage() {
        verifyElementExist("daftar_pembelian_premium_title");
        verifyElementExist("base_back_button");
        assertTrue(getElementValue("daftar_pembelian_premium_invoice_package_text").matches("Basic|Professional|Platinum"));
        HelperData.setLastActionPage(new DaftarPembelianPremiumPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
