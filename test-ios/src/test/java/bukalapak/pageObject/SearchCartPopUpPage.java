package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.ProductDetailData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class SearchCartPopUpPage extends BasePage {

    public SearchCartPopUpPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSearchCartPopUpPage() {
        waitForVisibilityOf("search_cart_pop_up_berhasil_masuk_keranjang_text",10);
        HelperData.setLastActionPage(new SearchCartPopUpPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateProductNameOnPopUp() {
        String elm_productName = constructLocator("cart_page_elm_productName", ProductDetailData.getProductName());
        waitForVisibilityOf("berhasil_masuk_keranjang");
        assertTrue(isElementVisible(elm_productName), "No related product on Cart Pop up");
        HelperData.setLastActionPage(new ProductDetailsPage(iosDriver));
    }

    public void deleteCartItem() {
        tapElement("delete_cart_item_popup",10);
        tapElement("confirm_delete_item_popup",10);
    }

    public void assertEmptyCartItemPopup() {
        assertTrue(isElementVisible("empty_cart_item_text",10));
    }
}
