package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import static bukalapak.TestInstrument.dotenv;

public class CloseStorePage extends BasePage {

    private final static String DEFAULT_REASON = "Pindah lapak";

    public CloseStorePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    /***
    * validations
    ***/
    public void userOnCloseStorePage() {
        verifyElementExist("tutup_lapak_page_title");
        HelperData.setLastActionPage(new CloseStorePage(iosDriver));
    }

    public void userOnCloseStorePageWithDeeplink(String deeplinkEnvVar) throws Exception {
        String deeplink = dotenv.get(deeplinkEnvVar);
        if (deeplink == null) {
            throw new Exception(deeplinkEnvVar + " is not set!");
        }

        openDeepLink(deeplink);
        userOnCloseStorePage();
    }

    public void validateCloseStoreSuccess() {
        userOnCloseStorePage();
        assertTrue(isElementExist("tutup_lapak_page_open_button") || isElementExist("tutup_lapak_page_cancel_button"));
    }

    public void validateOpenStoreSuccess() {
        verifyElementNotExist("tutup_lapak_page_title");
        HelperData.setLastActionPage(new CloseStorePage(iosDriver));
    }

    /***
    * single actions
    ***/
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void setCloseDate() {
        LocalDate today = LocalDate.now();
        String date = today.format(DateTimeFormatter.ofPattern("d"));

        setCloseDate(date);
    }

    public void setCloseDate(String date) {
        tapElement("tutup_lapak_page_close_date_dropdown");
        pickDate(constructLocator("tutup_lapak_page_calendar_date_button", date));
    }

    public void setOpenDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String date = tomorrow.format(DateTimeFormatter.ofPattern("d"));

        setOpenDate(date);
    }

    public void setOpenDate(String date) {
        tapElement("tutup_lapak_page_open_date_dropdown");
        pickDate(constructLocator("tutup_lapak_page_calendar_date_button", date));
    }

    public void setReason() {
        setReason(DEFAULT_REASON);
    }

    public void setReason(String reason) {
        typeAndEnterValue("tutup_lapak_page_reason_field", reason);
    }

    public void submit() {
        tapElement("tutup_lapak_page_save_button");
    }

    public void openStore() {
        try {
            tapElement("tutup_lapak_page_open_button");
        } catch(Exception ex) {
            tapElement("tutup_lapak_page_cancel_button");
        }
    }

    /***
    * action groups
    ***/
    public void fillFormWithDefaultValue() {
        setCloseDate();
        setOpenDate();
        setReason();
        submit();
    }

    /***
    * private methods
    ***/
    private void pickDate(String dateLocator) {
        try {
            tapElements(dateLocator, 0);
            userOnCloseStorePage();
        } catch(Exception e) { // to handle if there is two same date in the calendar cz there is no locator difference
            tapElements(dateLocator, 1);
            userOnCloseStorePage();
        }
    }
}
