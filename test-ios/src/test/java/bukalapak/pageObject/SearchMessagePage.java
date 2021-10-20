package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

import java.util.List;

import static bukalapak.TestInstrument.dotenv;


public class SearchMessagePage extends BasePage {

    public SearchMessagePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void verifySearchResult(String username, String message) {
        verifySearchUsernameResult(username);
        verifySearchMessagesResult(message);
    }

    public void clickSearchResultByMessage(String message) {
        tapElement(constructLocator("search_msg_search_result", dotenv.get(message)));
    }

    private void verifySearchUsernameResult(String username) {
        String locator = constructLocator("search_msg_search_user", dotenv.get(username));
        verifyElementExist(constructLocator("search_msg_search_user", dotenv.get(username)));
        List<IOSElement> searchResults = getElements(locator);
        assertTrue(searchResults.size() > 1);
    }

    private void verifySearchMessagesResult(String message) {
        String locator = constructLocator("search_msg_search_result", dotenv.get(message));
        verifyElementExist(constructLocator("search_msg_search_result", dotenv.get(message)));
        List<IOSElement> searchResults = getElements(locator);
        assertTrue(searchResults.size() > 1);
    }
}
