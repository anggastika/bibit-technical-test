package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TambahKartuCCPage extends BasePage {

    public TambahKartuCCPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnTambahKartuCCPage() {
        waitForVisibilityOf("tambah_kartu_title_text");
        waitForVisibilityOf("tambah_kartu_number_field");
        validateExist("tambah_kartu_month_field");
        validateExist("tambah_kartu_year_field");
        validateExist("tambah_kartu_cvv_field");
        HelperData.setLastActionPage(new TambahKartuCCPage(iosDriver));
    }

    public void inputCCNumber(String numberCC) {
        typeAndEnterValueWithTimeOut("tambah_kartu_number_field", numberCC);
    }

    public void selectMonth(String month) {
        tapElement("tambah_kartu_month_field");
        tapElement(constructLocator("vp_general_dynamic_name", month));
    }

    public void selectYear(String year) {
        tapElement("tambah_kartu_year_field");
        tapElement(constructLocator("vp_general_dynamic_name", year));
    }

    public void inputCVV(String cvv) {
        typeAndEnterValueWithTimeOut("tambah_kartu_cvv_field", cvv);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
