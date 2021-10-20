package bukalapak.pageObject;

import bukalapak.data.HelperData;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bukalapak.TestInstrument.dotenv;

public class PembekuanLapakPage extends BasePage {

    private final static String BACKGROUND_COLOR_WHITE = "rgba(255, 255, 255, 1)";
    private final static String BACKGROUND_COLOR_RED = "rgba(255, 247, 248, 1)";
    private final static String BORDER_COLOR_GREY = "1px solid rgb(224, 224, 224)";
    private final static String BORDER_COLOR_RED = "1px solid rgb(236, 12, 12)";
    private final static String COLOR_BLACK = "rgba(51, 51, 51, 1)";
    private final static String COLOR_RED = "rgba(229, 5, 5, 1)";
    private final static String FREEZE_TITLE_PERMANENT = "Maaf, lapakmu sudah dibekukan permanen";
    private final static String FREEZE_TITLE_TEMPORARY = "Lapak kamu dibekukan sementara";
    private final static String[] PUNISHMENT_ORDER = {"warning-1", "warning-2", "freeze-1", "warning-3", "warning-4", "freeze-2", "warning-5", "warning-6", "freeze-3"};

    public PembekuanLapakPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    /***
    * validations
    ***/
    public void validatePageDisplayed() {
        verifyElementExist("pembekuan_lapak_page_title", 15, "Halaman pembekuan lapak tidak ditampilkan!");
        changeContext().toWebview();
        verifyElementExist("pembekuan_lapak_page_status_header", 15, "Header Status Lapak tidak ditampilkan!");
        HelperData.setLastActionPage(new PembekuanLapakPage(iosDriver));
    }

    public void validateFreezeSection(String freezeStatus) {
        verifyElementExist("pembekuan_lapak_freeze_info_section", 3, "Info lapak dibekukan tidak ditampilkan!");

        if (freezeStatus.equals("permanent")) {
            assertEquals(getText("pembekuan_lapak_freeze_info_section_title"), FREEZE_TITLE_PERMANENT);
        } else {
            assertEquals(getText("pembekuan_lapak_freeze_info_section_title"), FREEZE_TITLE_TEMPORARY);
        }
    }

    public void validateUnfreezeButton(String buttonShown) {
        if (buttonShown.equals("shown")) {
            verifyElementExist("pembekuan_lapak_page_unfreeze_button", 1, "Tombol Buka Pembekuan tidak ditampilkan!");
        } else {
            verifyElementNotExist("pembekuan_lapak_page_unfreeze_button");
        }
    }

    public void validateLapakNotPunished() {
        verifyElementExist("pembekuan_lapak_page_status_safe_text", 3, "Informasi lapak aman tidak ditampilkan!");
    }

    public void validateLapakPunished(String stage) {
        verifyElementNotExist("pembekuan_lapak_page_status_safe_text");
        validatePunishmentContent(
            stage,
            constructLocator("pembekuan_lapak_page_punishment_active_title", stage),
            constructLocator("pembekuan_lapak_page_punishment_date_text", stage),
            constructLocator("pembekuan_lapak_page_punishment_info_text", stage)
        );
    }

    public void validateTimelineLapakNotPunished() {
        validateTimelineLapakPunished("none");
    }

    public void validateTimelineLapakPunished(String stage) {
        openPunishmentTimelineModal();

        int punishedIdx = Arrays.asList(PUNISHMENT_ORDER).indexOf(stage);

        for (int i = 0; i <= punishedIdx; i++) {
            validatePunishmentContent(
                PUNISHMENT_ORDER[i],
                constructLocator("pembekuan_lapak_page_punishment_active_title", PUNISHMENT_ORDER[i]),
                constructLocator("pembekuan_lapak_page_punishment_date_text", PUNISHMENT_ORDER[i]),
                constructLocator("pembekuan_lapak_page_punishment_info_text", PUNISHMENT_ORDER[i])
            );
        }
        for (int i = punishedIdx + 1; i < PUNISHMENT_ORDER.length; i++) {
            validateExist(constructLocator("pembekuan_lapak_page_punishment_inactive_title", PUNISHMENT_ORDER[i]), 1);
        }
    }

    public void validateSectionColor(String type, String borderColor, String backgroundColor) {
        String expectedBorderColor = borderColor.equals("normal") ? BORDER_COLOR_GREY : BORDER_COLOR_RED;
        String expectedBackgroundColor = backgroundColor.equals("normal") ? BACKGROUND_COLOR_WHITE : BACKGROUND_COLOR_RED;

        assertEquals(getElementPresent(constructLocator("pembekuan_lapak_page_weekly_report_section", type)).getCssValue("border"), expectedBorderColor);
        assertEquals(getElementPresent(constructLocator("pembekuan_lapak_page_weekly_report_section", type)).getCssValue("background-color"), expectedBackgroundColor);
    }

    public void validatePercentageColor(String type, String color) {
        assertEquals(getElementPresent(constructLocator("pembekuan_lapak_page_weekly_report_percentage_text", type)).getCssValue("color"), color.equals("normal") ? COLOR_BLACK : COLOR_RED);
    }

    public void validateCountColor(String type, String color) {
        assertEquals(getElementPresent(constructLocator("pembekuan_lapak_page_weekly_report_count_text", type)).getCssValue("color"), color.equals("normal") ? COLOR_BLACK : COLOR_RED);
    }

    public void validateNoPreviousUpdateRecord() {
        assertEquals(getTextFromElement("pembekuan_lapak_page_weekly_report_total_transaction_text"), "-");
        assertEquals(getTextFromElement("pembekuan_lapak_page_weekly_report_last_updated_text"), "-");
    }

    /***
    * single actions
    ***/
    public void goToPageWithDeeplink(String deeplinkEnv) throws Exception {
        String deeplink = dotenv.get(deeplinkEnv);

        if (deeplink == null) {
            throw new Exception(deeplinkEnv + " is not set!");
        }

        openDeepLink(deeplink);
        validatePageDisplayed();
    }

    // can't tap on the locator using tapElement(), so use tap by coordinate instead
    public void seeFAQ() {
        IOSElement element = getElement("pembekuan_lapak_page_see_faq_button", 3);
        int x = element.getCoordinates().onPage().getX();
        int y = element.getCoordinates().onPage().getY();

        nativeTap(x, y + 60);
        changeContext().toNative();
        verifyElementNotExist("pembekuan_lapak_page_title");
    }

    public void openPunishmentTimelineModal() {
        verifyElementExist("pembekuan_lapak_page_status_more_info_link", 3, "Link lihat status lapak selengkapnya tidak ditampilkan!");
        tapElement("pembekuan_lapak_page_status_more_info_link");
        verifyElementExist("pembekuan_lapak_page_punishment_timeline_title", 3, "Modal tahap pembekuan lapak tidak ditampilkan!");
    }

    /***
    * helper
    ***/
    private void validatePunishmentContent(String punishment, String titleLocator, String dateLocator, String infoLocator) {
        String type;
        char punishmentNum = punishment.charAt(punishment.length() - 1);

        if (punishment.startsWith("warning")) {
            type = "peringatan";
            assertEquals(getText(titleLocator), "Peringatan ke-" + punishmentNum);
        } else {
            type = "pembekuan";

            if (punishmentNum == '3') {
                assertEquals(getText(titleLocator), "Pembekuan Permanen");
            } else {
                assertEquals(getText(titleLocator), "Pembekuan Sementara " + punishmentNum);
            }
        }

        Matcher dateMatcher = Pattern.compile("Tanggal " + type + " \\d\\d? [a-zA-Z]+ \\d\\d\\d\\d").matcher(getText(dateLocator));
        assertTrue(dateMatcher.find());

        Matcher infoMatcher = Pattern.compile("Kamu melanggar [a-z\\s]+ sebanyak \\d+ dari total \\d+ transaksi \\(\\d+\\%\\)").matcher(getText(infoLocator));
        assertTrue(infoMatcher.find());
    }
}
