package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;

public class PilihKategoriPage extends BasePage {
    public PilihKategoriPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPilihKategoriPage() {
        waitForVisibilityOf("pilih_kategori_title", 5);
        verifyElementExist("pilih_kategori_search_field");
        HelperData.setLastActionPage(new PilihKategoriPage(iosDriver));
    }

    public void clickCategoryMenu(String categoryName) {
        waitForVisibilityOf(constructLocator("pilih_kategori_category_name_text", categoryName), 15);
        tapElement(constructLocator("pilih_kategori_category_name_text", categoryName));
        HelperData.setLastActionPage(new PilihKategoriPage(iosDriver));
    }

    public void clickCategoryMenuBySearch(String categoryName) {
        waitForVisibilityOf(constructLocator("pilih_kategori_search_result_category_name_text", categoryName), 15);
        tapElement(constructLocator("pilih_kategori_search_result_category_name_text", categoryName));
        HelperData.setLastActionPage(new PilihKategoriPage(iosDriver));
    }

    public void verifySearchResults(String categoryName) {
        for (WebElement element : getElementsPresent("pilih_kategori_search_results_text")) {
            validateValue().equalsTrue(element.getText().toLowerCase().contains(categoryName.toLowerCase()));
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
