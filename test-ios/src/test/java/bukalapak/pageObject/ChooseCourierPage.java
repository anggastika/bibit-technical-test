package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseCourierPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(ChooseCourierPage.class);

    public ChooseCourierPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnChooseCourierPage() {
        waitForVisibilityOf("choose_courier_title");
        verifyElementExist("choose_courier_title");
        HelperData.setLastActionPage(new ChooseCourierPage(iosDriver));
    }

    private void retrieveShippingfeeAndEta(String courierName) {
        String ongkir;
        String regexEta = "\\((.*?)\\)";
        ongkir = getElementValue(constructLocator("choose_courier_fee", courierName));
        if (ongkir.contains("Rp") || ongkir.contains("Gratis")) {
            Matcher eta = Pattern.compile(regexEta).matcher(ongkir);
            while (eta.find()) {
                XPRData.setEtaCourier(eta.group(1));
            }
            if (ongkir.contains("Rp")) {
                XPRData.setOngkirMarketplace(Integer.parseInt(ongkir.replaceAll("\\(.*\\)", "").replaceAll("[^0-9]", "")));
            } else {
                XPRData.setOngkirMarketplace(0);
            }
        } else {
            XPRData.setErrorMessageCourier(ongkir);
        }
        XPRData.setKurirName(getElementValue(constructLocator("choose_courier_name", courierName)));
    }

    private void scrollToCourier(String courierName) {
        //swipe up
        swipeUpToElement(constructLocator("choose_courier_name", courierName));
        //swipe down
        if (!isElementVisible(constructLocator("choose_courier_name", courierName))) {
            swipeDownToElement(constructLocator("choose_courier_name", courierName));
        }
    }

    public void chooseAllKurir(String courierName, String typecourier) {
        scrollToCourier(courierName);
        tapElement(constructLocator("choose_courier_name", courierName));
        retrieveShippingfeeAndEta(courierName);
        swipeUpToElement("choose_courier_pilih_button");
        if (isElementVisible("choose_courier_pilih_button")) {
            tapElement("choose_courier_pilih_button");
        } else {
            tapElement("choose_courier_back_button");
        }
    }

    public void validateCourierOnDemandNotSupported(String courierName, String warningMessage) {
        assertTrue(XPRData.getKurirName().equalsIgnoreCase(courierName), "On Demand not supported");
        assertTrue(XPRData.getErrorMessageCourier().equalsIgnoreCase(warningMessage), "On Demand not supported");
    }

    public void validateCourierOnDemandNotSupported(String courierName, String cost, String eta) {
        assertTrue(XPRData.getKurirName().equalsIgnoreCase(courierName), "On Demand not supported");
        String tmpCost = cost;
        if (cost.equalsIgnoreCase("Gratis")) {
            tmpCost = "0";
        }
        assertEquals(XPRData.getOngkirMarketplace(), Integer.parseInt(tmpCost), "On Demand not supported");
        assertTrue(XPRData.getEtaCourier().equalsIgnoreCase(eta), "On Demand not supported");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateCourierRecoSectionDisplayed(String courier, Boolean isReco) {
        if (!isElementVisible("choose_courier_tooltip_waktu_pengiriman", 3)) {
            swipeUpToElement("choose_courier_tooltip_waktu_pengiriman");
        }
        if (isReco) {
            validateElementWithText("choose_courier_rekomendasi_courier_name", courier);
            validateValue().equalsTrue(getTextFromElement("choose_courier_rekomendasi_section_text", 1).equals("Rekomendasi Bukalapak"));
            validateValue().equalsTrue(getTextFromElement("choose_courier_rekomendasi_section_text", 2).equals("Kurir pilihan dengan performa terbaik"));
        } else {
            validateValue().equalsFalse(getTextFromElement("choose_courier_rekomendasi_section_text", 1).equals("Rekomendasi Bukalapak"));
            validateValue().equalsFalse(getTextFromElement("choose_courier_rekomendasi_section_text", 1).equals("Kurir pilihan dengan performa terbaik"));
        }
        HelperData.setLastActionPage(new ChooseCourierPage(iosDriver));
    }

    private void validationOldCourierSection(String courierSection, String courierName) {
        swipeDownToElement(constructLocator("choose_courier_section_text", courierSection));
        switch (courierSection) {
            case "Waktu Pengiriman 1-3 hari kerja":
                validateElementWithText(constructLocator("choose_courier_section_text", courierSection), courierSection);
                validateValue().equals(getElementValue(constructLocator("choose_courier_name_section1_text", courierName)), courierName);
                break;
            case "Waktu Pengiriman 1 Hari":
                validateElementWithText(constructLocator("choose_courier_section_text", courierSection), courierSection);
                validateValue().equals(getElementValue(constructLocator("choose_courier_name_section3_text", courierName)), courierName);
                break;
            case "Waktu Pengiriman 2-6 Jam":
                validateElementWithText(constructLocator("choose_courier_section_text", courierSection), courierSection);
                validateValue().equals(getElementValue(constructLocator("choose_courier_name_section2_text", courierName)), courierName);
                break;
            default:
                LOGGER.info("No courier type, please change the courier type ");
                break;
        }
    }

    private void validationNewCourierSection(String courierName) {
        validateValue().equals(getElementValue(constructLocator("choose_courier_name1_text", getTextFromElement("choose_courier_section1_text"), courierName)), courierName);
    }

    public void validationCourierSection(String courierSection, String courierName) {
        String titlePage = getTextFromElement("choose_courier_title");
        if (titlePage.contains("Reguler") || titlePage.contains("Next Day") || titlePage.contains("Same Day") || titlePage.contains("Cargo")) {
            validationNewCourierSection(courierName);
        } else {
            validationOldCourierSection(courierSection, courierName);
        }
        HelperData.setLastActionPage(new ChooseCourierPage(iosDriver));
    }

    public void validationTagTersediaCOD(String courier, String hasTag, String tag) {
        if (hasTag == null) {
            swipeUpToElement("choose_courier_tag_COD_courier");
            validateExist(constructLocator("choose_courier_courier_name_with_tag_text", courier));
            validateValue().equals(getElementValue(constructLocator("choose_courier_name_with_tersedia_cod_text", tag, courier)), courier);
            LOGGER.info("Courier name :" + getElementValue(constructLocator("choose_courier_name_with_tersedia_cod_text", tag, courier)));
        } else {
            validateNotExist(constructLocator("choose_courier_courier_name_with_tag_text", courier), 5);
            validateValue().equals(getElements("choose_courier_choice_text").size(), 1);
            LOGGER.info("size : " + getElements("choose_courier_choice_text").size());
        }
        HelperData.setLastActionPage(new ChooseCourierPage(iosDriver));
    }

    public void validateErrorMessageAmbilSendiri(String courier) {
        validateValue().equals(XPRData.getErrorMessageCourier(), courier);
        HelperData.setLastActionPage(new ChooseCourierPage(iosDriver));
    }
}
