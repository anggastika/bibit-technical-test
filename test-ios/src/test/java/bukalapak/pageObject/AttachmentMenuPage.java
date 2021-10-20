package bukalapak.pageObject;

import com.bukalapak.salad.util.Direction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.core.util.Integers;
import java.util.ArrayList;

/**
 * Created by dickyedgardo on 29/11/18.
 */
public class AttachmentMenuPage extends BasePage {

    private static final int MAX_ATTACHMENT_LIMIT = 5;

    public AttachmentMenuPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void tapCheckboxes(String amountString) {
        int amount = Integers.parseInt(amountString);
        isElementVisible("attachment_chat_products_checkbox");
        for (int i = 0; i < amount; i++) {
            tapElements("attachment_chat_products_checkbox", i);
        }
    }

    public ArrayList<String> saveProductsIdentifier() {
        ArrayList<String> priceIdentifierList = new ArrayList<String>();
        for (int i = 0; i < MAX_ATTACHMENT_LIMIT; i++) {
            String price = getTextFromElement("attachment_chat_price_products_text", i);
            priceIdentifierList.add(price);
        }
        return priceIdentifierList;
    }

    public void verifyGeneratedProducts(ArrayList<String> listIdentifier) {
        swipeToDirection(Direction.UP);
        for (String s : listIdentifier) {
            String elm_locator = constructLocator("attachment_gen_product", s);
            if (!isElementVisible(elm_locator)) {
                swipeToDirection(Direction.DOWN);
            }
            verifyElementExist(elm_locator);
        }
    }
}
