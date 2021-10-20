package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TambahKataKunciPage extends BasePage {

    public TambahKataKunciPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTambahKataKunciPage() {
        waitForVisibilityOf("tambah_kata_kunci_title_text", 30);
        HelperData.setLastActionPage(new TambahKataKunciPage(iosDriver));
    }

    public void selectKeyword(String keyword) {
        if (keyword.contains("second")) {
            tapElement("tambah_kata_kunci_second_keyword");
        } else {
            tapElement("tambah_kata_kunci_first_keyword");
        }
    }

    public void verifyInfoSelectedKeywords(String infoText, String totalKeywords) {
        waitForVisibilityOf("label_" + infoText);
        assertEquals(totalKeywords + " kata kunci dipilih", getText("tambah_kata_kunci_selected_text"));
    }

    public void searchKeyword(String keyword) {
        typeAndEnterValueWithTimeOut("tambah_kata_kunci_field", keyword);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
