package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;
import org.openqa.selenium.InvalidElementStateException;

public class KasihTebarEmasPage extends BasePage{
    public KasihTebarEmasPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyBukaEmasSharingPage(String option) {
        //delay is used to wait for BukaEmas sharing native page to be rendered
        delay(5000);
        changeContext().toNative();
        validateExist(constructLocator("BE_SHARING_TITLE", option), 3);
        verifyCurrentBalanceDisplayed();
        switch (option) {
            case "Kasih Emas":
                verifyKasihEmasSection();
                break;
            case "Tebar Emas":
                verifyTebarEmasSection();
                break;
            default:
                Assert.fail("Invalid BukaEmas sharing option!");
        }
        HelperData.setLastActionPage(new KasihTebarEmasPage(iosDriver));
    }

    private void verifyCurrentBalanceDisplayed() {
        validateExist("BE_BALANCE_TEXT", 3);
        validateExist("BE_UNIT_BALANCE", 3);
        validateExist("BE_AMOUNT_BALANCE", 3);
        InvestmentData.setCurrentBukaemasBalance(getBullionBalanceFromEstimate());
    }

    private String getBullionBalanceFromEstimate() {
        return getTextFromElement("BE_AMOUNT_BALANCE").replaceAll("[.a-zA-Z]|[\\s]", "");
    }

    private void verifyKasihEmasSection() {
        validateExist("KASIH_SECTION_TITLE", 3);
        validateExist("KASIH_LINK_OPTION", 3);
        validateExist("KASIH_SEARCH_BAR", 3);
    }

    private void verifyTebarEmasSection() {
        validateExist("TEBAR_SECTION_TITLE", 3);
        validateExist("TEBAR_CARA_NEBAR_BTN", 3);
        validateExist("TEBAR_WINNER_COUNT_TITLE", 3);
    }

    public void tapOnCaraNebarBtn() {
        tapElement("TEBAR_CARA_NEBAR_BTN");
    }

    public void verifyCaraNebarPage() {
        validateExist("TEBAR_EMAS_CARA_NEBAR_HEADER", 3);
        validateExist("TEBAR_EMAS_CARA_NEBAR_BACK_BTN", 3);
        validateCaraNebarInfotext();
        HelperData.setLastActionPage(new KasihTebarEmasPage(iosDriver));
    }

    private void validateCaraNebarInfotext() {
        validateExist("CARA_NEBAR_EMAS_TITLE",3);
        validateExist("CARA_NEBAR_FIRST_POINT", 3);
        validateExist("CARA_NEBAR_FIRST_INFO", 3);
        validateExist("CARA_NEBAR_SECOND_POINT", 3);
        validateExist("CARA_NEBAR_SECOND_INFO", 3);
        validateExist("CARA_NEBAR_THIRD_POINT", 3);
        validateExist("CARA_NEBAR_THIRD_INFO", 3);
    }

    public void tapOnTransferWithLink() {
        InvestmentData.setKasihEmasSearchType("link");
        InvestmentData.setKasihReceiverUsername("Kasih pakai link");
        tapElement("KASIH_LINK_OPTION");
    }

    public void tapOnSearchPageBackBtn() {
        tapElement("KASIH_SEARCH_BACK_BTN");
    }

    public void validateReceiverUsername() {
        waitForVisibilityOf("KASIH_EMAS_RECEIVER_NAME", 3);
        validateValue().equals(InvestmentData.getKasihReceiverUsername(), getText("KASIH_EMAS_RECEIVER_NAME"));
    }

    public void inputKasihEmasValue(String method) {
        validateKasihEmasInput(method);
        typeAndEnterValue("KASIH_EMAS_VALUE_FIELD", sharingEmasValueType(method));
        verifyTransferEstimationText(method);
    }

    private String sharingEmasValueType(String method) {
        String transferValue = null;
        switch(method) {
            case "unit":
                transferValue = TestInstrument.dotenv.get("KASIH_EMAS_UNIT_VALUE");
                break;
            case "amount":
                transferValue = TestInstrument.dotenv.get("KASIH_EMAS_AMOUNT_VALUE");
                break;
            case "spread unit":
                transferValue = TestInstrument.dotenv.get("TEBAR_EMAS_UNIT_VALUE");
                break;
            case "spread amount":
                transferValue = TestInstrument.dotenv.get("TEBAR_EMAS_AMOUNT_VALUE");
                break;
            case "unit less than minimum limit":
                transferValue = TestInstrument.dotenv.get("SHARING_EMAS_UNIT_LOWER_VALUE");
                break;
            case "amount more than user balance":
                transferValue = TestInstrument.dotenv.get("SHARING_EMAS_AMOUNT_HIGHER_VALUE");
                break;
            default:
                Assert.fail("Invalid Kasih Emas input value type!");
        }
        return transferValue;
    }

    private void verifyTransferEstimationText(String method) {
        if (method.equals("unit")) {
            validateExist("SHARING_EMAS_VALUE_INFO_UNIT", 1);
        } else if (method.equals("amount")) {
            validateExist("SHARING_EMAS_VALUE_INFO_AMOUNT", 1);
        }
    }

    private void validateKasihEmasInput(String method) {
        verifyInputKasihEmasSection();
        verifySharingEmasValueType(method);
    }

    private void verifySharingEmasValueType(String method) {
        String type = method.contains("unit") ? "unit" : "amount";
        switch (type) {
            case "unit":
                validateUsingUnit();
                break;
            case "amount":
                validateUsingAmount();
                break;
            default:
                Assert.fail("Invalid transfer value type!");
        }
    }

    private void validateUsingUnit() {
        if (!isElementVisible("KASIH_EMAS_UNIT_INDICATOR", 3)) {
            tapElement("KASIH_EMAS_PAKAI_GRAM");
        }
        validateExist("KASIH_EMAS_PAKAI_RUPIAH", 3);
    }

    private void validateUsingAmount() {
        if (!isElementVisible("KASIH_EMAS_AMOUNT_INDICATOR", 3)) {
            tapElement("KASIH_EMAS_PAKAI_GRAM");
        }
        validateExist("KASIH_EMAS_PAKAI_GRAM", 3);
    }

    private void verifyInputKasihEmasSection() {
        validateExist("KASIH_EMAS_INPUT_SECTION_TITLE", 3);
        validateExist("KASIH_EMAS_VALUE_FIELD", 3);
        validateExist("KASIH_EMAS_LANJUTKAN_BTN", 3);
    }

    public void verifyKasihEmasCardPage() {
        validateExist("KASIH_EMAS_CARD_PAGE_TITLE", 3);
        validateExist("KASIH_EMAS_CARD_UNIT", 3);
        InvestmentData.setTransferEmasUnit(getKasihEmasUnitAmount());
        validateExist("KASIH_EMAS_CARD_AMOUNT", 3);
        validateExist("KASIH_EMAS_MESSAGE_FIELD", 3);
        validateExist("KASIH_EMAS_SEND_BTN", 3);
    }

    private String getKasihEmasUnitAmount(){
        return getText("KASIH_EMAS_CARD_UNIT").replaceAll("[()]", "");
    }

    public void createKasihEmasCard() {
        inputKasihEmasMessage();
        chooseKasihEmasImage();
        tapElement("KASIH_EMAS_SEND_BTN");
    }

    private void inputKasihEmasMessage() {
        String message = TestInstrument.dotenv.get("KASIH_EMAS_MESSAGE_TEXT");
        typeAndEnterValue("KASIH_EMAS_MESSAGE_FIELD", message);
        InvestmentData.setKasihEmasMessage(message);
    }

    private void chooseKasihEmasImage() {
        int numberOfImages = getElementsPresent("KASIH_EMAS_CARD_OPTION").size();
        int cardOption = randomize().number(numberOfImages);
        try {
            tapElement("KASIH_EMAS_CARD_OPTION", cardOption);
        } catch (InvalidElementStateException e) {
            swipeLeftAtSpecifiedLocator("KASIH_EMAS_CARD_THEME_LIST");
            tapElement("KASIH_EMAS_CARD_OPTION", cardOption);
        }
    }

    public void validateShareWithLinkModal() {
        validateExist("SHARING_EMAS_SHARE_MODAL_TITLE", 3);
        validateExist("SHARING_EMAS_SHARE_LINK_BTN", 3);
        validateExist("SHARING_EMAS_SHARE_LINK_CANCEL_BTN", 3);
    }

    public void tapOnShareWithLinkButton() {
        tapElement("SHARING_EMAS_SHARE_LINK_BTN");
    }

    public void verifyShareWithLinkPage() {
        validateExist("KASIH_EMAS_SHARE_PAGE_TEXT", 3);
        validateExist("KASIH_EMAS_SHARE_PAGE_DESC", 3);
        validateExist("KASIH_EMAS_SHARE_PAGE_SHARE_BTN", 3);
    }

    public void tapOnSharePageBackButton() {
        tapElement("KASIH_EMAS_SHARE_PAGE_BACK_BTN");
    }

    public void verifyShareLaterModal() {
        validateExist("KASIH_EMAS_SHARE_LATER_TITLE", 3);
        validateExist("KASIH_EMAS_SHARE_LATER_BUTTON", 3);
        validateExist("KASIH_EMAS_SHARE_LATER_CANCEL_BTN", 3);
    }

    public void tapOnShareLaterButton() {
        tapElement("KASIH_EMAS_SHARE_LATER_BUTTON");
    }

    public void tapKasihEmasSearchBar() {
        tapElement("KASIH_SEARCH_BAR");
    }

    public void verifyKasihEmasSearchPage() {
        validateExist("KASIH_SEARCH_SECTION", 3);
        validateExist("KASIH_SEARCH_FIELD", 3);
        validateExist("KASIH_SEARCH_INFO", 3);
    }

    public void searchKasihReceiver(String method) {
        typeAndEnterValue("KASIH_SEARCH_FIELD",setSearchKeyword(method));
        LogUtil.info(String.valueOf(isElementVisible("KASIH_SEARCH_USER_RESULT")));
        LogUtil.info("testa");
        // have to add delay here for search result to render
        delay(2000);
        if (isElementExist("KASIH_SEARCH_USER_RESULT", 5)) {
            LogUtil.info("testb");
            tapElement("KASIH_SEARCH_USER_RESULT");
            InvestmentData.setKasihReceiverUsername(getText("KASIH_SEARCH_USER_RESULT"));
        }
        delay(2000);
    }

    private String setSearchKeyword(String method) {
        String keyword = null;
        switch (method) {
            case "username":
                keyword = TestInstrument.dotenv.get("KASIH_EMAS_RECEIVER_USERNAME");
                InvestmentData.setKasihEmasSearchType("username");
                break;
            case "phone number":
                keyword = TestInstrument.dotenv.get("KASIH_EMAS_RECEIVER_PHONE_NUMBER");
                InvestmentData.setKasihEmasSearchType("phone");
                break;
            case "self number":
                keyword = TestInstrument.dotenv.get("KASIH_EMAS_SENDER_PHONE_NUMBER");
                break;
            case "invalid username":
                keyword = TestInstrument.dotenv.get("KASIH_EMAS_RECEIVER_INVALID_USERNAME");
                break;
            case "invalid phone number":
                keyword = TestInstrument.dotenv.get("KASIH_EMAS_RECEIVER_INVALID_PHONE_NUMBER");
                break;
            default:
                Assert.fail("Invalid search method!");
                break;
        }
        return keyword;
    }

    public void tapOnRecentTransferReceiver() {
        int userCount = getElementsPresent("KASIH_EMAS_LAST_RECEIVER").size();
        int randReceiver = randomize().number(userCount);
        String lastReceiverUsername = constructLocator("KASIH_EMAS_LAST_RECEIVER", randReceiver);
        InvestmentData.setKasihReceiverUsername(getText(lastReceiverUsername));
        InvestmentData.setKasihEmasSearchType("username");
        try {
            tapElement(lastReceiverUsername);
        }
        catch (Exception e) {
            Assert.fail("User doesn't have any last BukaEmas receiver!");
        }
    }

    public void verifyShareEmasValueError(String errorType) {
        String errorText = null;
        switch(errorType){
            case "lower than minimum limit":
                errorText = "emas minimal";
                break;
            case "higher than maximum limit":
                errorText = "emas maksimal";
                break;
            default:
                Assert.fail("Invalid error type!");
        }
        validateExist(constructLocator("KASIH_EMAS_VALUE_ERROR_TEXT", errorText), 3);
        HelperData.setLastActionPage(new KasihTebarEmasPage(iosDriver));
    }

    public void tapOnLanjutkanBton() {
        tapElement("KASIH_EMAS_LANJUTKAN_BTN");
    }

    public void verifyKasihEmasSearchError(String errorType) {
        validateSearchErrorMessage(errorType);
        validateShareLinkButtonDisplayed();
    }

    private void validateShareLinkButtonDisplayed() { validateExist("SEARCH_PAGE_SHARE_LINK_BTN", 3); }

    private void validateSearchErrorMessage(String method) {
        // have to add delay here for search result to render
        delay(2000);
        switch (method) {
            case "can't send to self":
                validateExist("SEARCH_SELF_ACCOUNT_ERROR", 3);
                break;
            case "user not found":
                validateExist("SEARCH_USER_NOT_FOUND_ERROR", 3);
                break;
            default:
                Assert.fail("Not a valid error input!");
        }
    }

    public void inputTebarEmasValue(String method) {
        verifyInputTebarEmasSection();
        verifySharingEmasValueType(method);
        typeAndEnterValue("TEBAR_EMAS_VALUE_FIELD", sharingEmasValueType(method));
        verifyTransferEstimationText(method);
        getTebarEmasSharedUnit(method);
    }

    private void getTebarEmasSharedUnit(String method) {
        if (method.equals("spread unit")) {
            InvestmentData.setTransferEmasUnit(getText("TEBAR_EMAS_VALUE_FIELD"));
        } else if (method.equals("spread amount")) {
            String spreadUnit = (getText("SHARING_EMAS_VALUE_INFO_AMOUNT")).replaceAll("[a-zA-Z]|[\\s]", "");
            InvestmentData.setTransferEmasUnit(spreadUnit);
        }
    }

    private void verifyInputTebarEmasSection() {
        validateExist("TEBAR_EMAS_VALUE_FIELD", 3);
        validateExist("TEBAR_EMAS_START_SHARE_BTN", 3);
    }

    public void getTebarEmasReceiverAmount() {
        verifyTebarEmasReceiverAmountSection();
        InvestmentData.setTebarEmasReceiverAmount(getElementAttributeValue("TEBAR_EMAS_RECEIVER_AMOUNT_DROPDOWN", "value"));
    }

    private void verifyTebarEmasReceiverAmountSection() {
        validateExist("TEBAR_EMAS_RECEIVER_AMOUNT_TITLE", 3);
        validateExist("TEBAR_EMAS_RECEIVER_AMOUNT_DROPDOWN", 3);
    }

    public void tapOnMulaiSebarinBtn() {
        tapElement("TEBAR_EMAS_START_SHARE_BTN");
    }

    public void verifyTebarEmasLeaderboardPage(String state) {
        changeContext().toNative();
        verifyOnLeaderboardPage();
        validateLeaderboardState(state);
    }

    private void verifyOnLeaderboardPage() {
        validateExist("TEBAR_EMAS_LEADERBOARD_HEADER", 3);
        validateExist("TEBAR_EMAS_LEADERBOARD_BACK_BTN", 3);
        validateExist("TEBAR_EMAS_LEADERBOARD_UNIT_INFO", 3);
        validateExist("TEBAR_EMAS_LEADERBOARD_UNIT_AMOUNT", 3);
        validateExist("TEBAR_EMAS_LEADERBOARD_ESTIMATE", 3);
    }

    private void validateLeaderboardState(String state) {
        switch(state) {
            case "unclaimed":
                validateExist("TEBAR_EMAS_LEADERBOARD_UNCLAIMED_TEXT", 3);
                validateExist("TEBAR_EMAS_LEADERBOARD_SHARE_BUTTON", 3);
                break;
            case "partially claimed":
                validateExist("TEBAR_EMAS_LEADERBOARD_LUCKIEST_TEXT", 3);
                validateExist("TEBAR_EMAS_LEADERBOARD_LUCKIEST_WINNER", 3);
                validateExist("TEBAR_EMAS_LEADERBOARD_PARTIALLY_CLAIMED_TEXT", 3);
                break;
            case "fully claimed":
                validateExist("TEBAR_EMAS_LEADERBOARD_LUCKIEST_TEXT", 3);
                validateExist("TEBAR_EMAS_LEADERBOARD_LUCKIEST_WINNER", 3);
                validateExist("TEBAR_EMAS_LEADERBOARD_OTHER_WINNER", 3);
                validateNotExist("TEBAR_EMAS_LEADERBOARD_PARTIALLY_CLAIMED_TEXT", 3);
                validateNotExist("TEBAR_EMAS_LEADERBOARD_SHARE_BUTTON", 3);
                break;
            default:
                Assert.fail("Invalid Tebar Emas Leaderboard state!");
        }
    }

    public void tapOnLeaderboardBackBtn() {
        tapElement("TEBAR_EMAS_LEADERBOARD_BACK_BTN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
