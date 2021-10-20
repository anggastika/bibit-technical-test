package bukalapak.pageObject.vp.tix.tiketEvent;

import bukalapak.data.EventData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Fixco Amrizal Candra
 **/

public class TiketEventLandingPage extends VpBasePage {

    public TiketEventLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        skipCoachMark();
        waitForVisibilityOf("TICKET_EVENT_BANNER_IMAGE", 100);
        verifyElementExist("TICKET_EVENT_TITLE_TEXT");
        verifyElementExist("TICKET_EVENT_VENUE_TEXT");
        verifyElementExist("TICKET_EVENT_DATE_TEXT");
        HelperData.setLastActionPage(new TiketEventLandingPage(iosDriver));
    }

    public void skipCoachMark() {
        if (!EventData.isCoachMarked()) {
            tapOnCoachMark();
            EventData.setCoachMarked(true);
        }
    }

    public void tapOnCoachMark() {
        verifyElementExist("TICKET_EVENT_LANDING_COACHMARK_BUTTON");
        tapElement("TICKET_EVENT_LANDING_COACHMARK_BUTTON");
        verifyElementNotExist("TICKET_EVENT_LANDING_COACHMARK_BUTTON");
    }

    public void chooseCategoryEvent(String category) {
        tapElement(constructLocator("TICKET_EVENT_CATEGORY", category));
    }

    public void tapOnEventBanner(String eventIndex) {
        int eventIndexInt = Integer.parseInt(eventIndex);
        tapElements("TICKET_EVENT_TITLE_TEXT", eventIndexInt);
    }

    public void submitDataEventTicket() {
        swipeUpToElement("TICKET_EVENT_LANJUT_BUTTON");
        tapElement("TICKET_EVENT_ADD_BUTTON");
        EventData.setEventPrice(getText("TICKET_EVENT_TOTAL_PRICE"));
        swipeToLocator("TICKET_EVENT_LANJUT_BUTTON");
        tapElement("TICKET_EVENT_LANJUT_BUTTON");
    }

    public void setEventTitleData(String eventIndex) {
        int eventIndexInt = Integer.parseInt(eventIndex);
        EventData.setEventTitle(getTextFromElement("TICKET_EVENT_TITLE_TEXT", eventIndexInt));
    }

    public void tapShareButton() {
        tapElement("TICKET_EVENT_BANNER_IMAGE", 10);
        waitForVisibilityOf("TICKET_EVENT_SHARE_BUTTON", 5);
        tapElement("TICKET_EVENT_SHARE_BUTTON");
    }

    public void validateShareMedia() {
        waitForVisibilityOf("TICKET_EVENT_MEDIA_BUTTON", 10);
        verifyElementExist("TICKET_EVENT_MEDIA_BUTTON");
    }

    public void openEventFilter(){
        tapElement("TICKET_EVENT_FILTER_LOCATION");
    }

    public void chooseEventFilter(String city) {
        validateDisplayed(constructLocator("TICKET_EVENT_FILTER_LOCATION_CITY", city));
        tapElement(constructLocator("TICKET_EVENT_FILTER_LOCATION_CITY", city));
    }

    public void validateEventFilter(String city) {
        waitForVisibilityOf(constructLocator("TICKET_EVENT_FILTER_LOCATION_CITY_RESULT", city));
        HelperData.setLastActionPage(new TiketEventLandingPage(iosDriver));
    }

    public void searchInvalidEvent() {
        tapElement("TICKET_EVENT_SEARCH_ICON");
        typeAndEnterValue("TICKET_EVENT_KEYWORD_SEARCH", "!@#$%^&*()-_=+");
    }

    public void validateEventIsNotFound() {
        validateDisplayed("TICKET_EVENT_EMPTY_SEARCH_RESULT");
        HelperData.setLastActionPage(new TiketEventLandingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
