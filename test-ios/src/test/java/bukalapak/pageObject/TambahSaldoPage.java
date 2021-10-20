package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TambahSaldoPage extends BasePage {

    public TambahSaldoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnTambahSaldoPage() {
        verifyElementExist("tambah_saldo_title");
        verifyElementExist("base_back_button");
        HelperData.setLastActionPage(new TambahSaldoPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
