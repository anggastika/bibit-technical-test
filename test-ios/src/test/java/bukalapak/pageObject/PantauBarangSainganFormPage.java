package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.cucumber.datatable.DataTable;

import java.util.Map;

public class PantauBarangSainganFormPage extends BasePage {
    public PantauBarangSainganFormPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnAddPantauBarangSaingan() {
        waitForVisibilityOf("pantau_barang_saingan_title");
        verifyElementExist("pantau_barang_saingan_back_button");
        verifyElementExist("pantau_barang_saingan_keyword_text");
        verifyElementExist("pantau_barang_saingan_keyword_text_field");
        verifyElementExist("pantau_barang_saingan_category_text");
        verifyElementExist("pantau_barang_saingan_category_tab");
        verifyElementExist("pantau_barang_saingan_sort_text");
        verifyElementExist("pantau_barang_saingan_sort_dropdown");
        verifyElementExist("pantau_barang_saingan_notify_checkbox");
        verifyElementExist("pantau_barang_saingan_simpan_button");
        HelperData.setLastActionPage(new PantauBarangSainganPage(iosDriver));
    }

    public void inputKeywordPantauSaingan(String keyword) {
        tapElement("pantau_barang_saingan_keyword_text_field");
        typeAndEnterValueWithTimeOut("pantau_barang_saingan_search_text_field", keyword);
    }

    public void chooseSortBy(String sorter) {
        tapElement("pantau_barang_saingan_sort_dropdown");
        waitForVisibilityOf("pantau_barang_saingan_" + sorter + "_text");
        tapElement("pantau_barang_saingan_" + sorter + "_text");
    }

    public void createPantauan(DataTable data) {
        Map<String, String> table = data.asMap(String.class, String.class);
        inputKeywordPantauSaingan(table.get("keyword"));
        setCheckboxNotify("true".equals(table.get("notify")));
        chooseSortBy(table.get("sorter"));
        tapElement("pantau_barang_saingan_simpan_button", 10);
    }

    public void setCheckboxNotify(boolean isNotify) {
        boolean checkboxStatus = "true".equals(getElementValue("pantau_barang_saingan_notify_checkbox"));
        if (isNotify != checkboxStatus) {
            tapElement("pantau_barang_saingan_notify_checkbox");
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
