package bukalapak.pageObject.lsf;

import bukalapak.data.HelperData;
import bukalapak.data.LSFData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NoSuchElementException;
import static bukalapak.TestInstrument.dotenv;

public class LiveStreamingWebPage extends BasePage {

    private static final int LONG_TIMEOUT = 30;
    private String getContext = iosDriver.getContext();

    public LiveStreamingWebPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateLiveStreamLandingPage() {
        changeContext().toWebview();
        verifyElementExist("LIVE_STREAM_BUKALIVE_TITLE", LONG_TIMEOUT, "Header Not Exist");
        validateCardStreamComponent();
        HelperData.setLastActionPage(new LiveStreamingWebPage(iosDriver));
    }

    private void validateCardStreamComponent() {
        validateExist("LIVE_STREAM_CARD_LIST", LONG_TIMEOUT);
        validateExist("LIVE_STREAM_CARD_TITLE");
        validateExist("LIVE_STREAM_VIEWER_COUNT");
        validateExist("LIVE_STREAM_AVATAR");
        validateExist("LIVE_STREAM_NAME");
    }

    public void validateErrorMessageBroadcast() {
        verifyElementDisplayed("LIVE_STREAM_ERROR_BROADCAST_TEXT");
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnStreamCard() {
        tapElements("LIVE_STREAM_NAME", 0);
    }

    public void validateStreamPlayer(String user) {
        changeContext().toWebview();
        if(!isElementExist("LIVE_STREAM_PLAYER_PUBLISHER_NAME", 10)){
            String credential = user.replaceAll(" ", "_").toUpperCase();
            String username = dotenv.get(credential + "_USERNAME");
            String password = dotenv.get(credential + "_PASSWORD");
            inputUsername(username);
            inputPassword(password);
        }
        if (isElementExist("LIVE_STREAM_PLAYER_STATISTIC")) {
            validateExist("LIVE_STREAM_PLAYER_STATISTIC");
        }
        validateExist("LIVE_STREAM_PLAYER_PUBLISHER_NAME", 10);
    }

    private void inputUsername(String username) {
        validateExist("USERNAME_WEB_LOGIN_FIELD", 5);
        typeValue("USERNAME_WEB_LOGIN_FIELD", username);
        tapElement("USERNAME_WEB_LOGIN_BUTTON");
    }

    private void inputPassword(String password) {
        validateExist("PASSWORD_WEB_LOGIN_FIELD", 5);
        typeValue("PASSWORD_WEB_LOGIN_FIELD", password);
        tapElement("PASSWORD_WEB_LOGIN_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapProfilePicture() {
        changeContext().toNative();
        waitForVisibilityOf("LIVE_STREAM_PLAYER_PUBLISHER_AVATAR", 5);
        tapElement("LIVE_STREAM_PLAYER_PUBLISHER_AVATAR");
    }

    public void tapOnLinkPinnedMessage() {
        changeContext().toNative();
        waitForVisibilityOf("LIVE_STREAM_PLAYER_PINNED_MESSAGE", LONG_TIMEOUT);
        tapElements("LIVE_STREAM_PLAYER_PINNED_MESSAGE", 0);
    }

    public void tapCloseButton() {
        if (getContext.contains("NATIVE_APP")) {
            tapElement("LIVE_STREAM_PLAYER_CLOSE");
        } else {
            tapElement("LIVE_STREAM_PLAYER_CLOSE_WEBVIEW");
        }
    }
}
