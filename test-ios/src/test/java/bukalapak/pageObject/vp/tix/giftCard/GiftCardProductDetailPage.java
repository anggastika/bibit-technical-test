package bukalapak.pageObject.vp.tix.giftCard;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.GiftCreditsCouponData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GiftCardProductDetailPage extends VpBasePage {
    public GiftCardProductDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnGiftCouponPage() {
        changeContext().toWebview();
        verifyElementExist("GIFT_CARD_PDP_DEALS_NAME");
        verifyElementExist("GIFT_CARD_PDP_RECIPIENT_NAME");
        verifyElementExist("GIFT_CARD_PDP_RECIPIENT_PHONE");
        verifyElementExist("GIFT_CARD_PDP_TNC_SECTION");
    }

    public void validateOnGiftCardPage() {
        changeContext().toWebview();
        waitForVisibilityOf("GIFT_CARD_PDP_HEADER_TITLE", 20);
        verifyElementExist("GIFT_CARD_PDP_HEADER_TITLE");
        verifyElementExist("GIFT_CARD_PDP_CREDITS_OPTIONS");
        verifyElementExist("GIFT_CARD_PDP_TNC_SECTION");
        verifyElementExist("GIFT_CARD_PDP_RECIPIENT_NAME");
        verifyElementExist("GIFT_CARD_PDP_RECIPIENT_PHONE");
    }

    public void tapOnGiftByType(String giftCardType) {
        if (giftCardType.equals("credits")) {
            tapElement("GIFT_CARD_PDP_CREDITS_OPTIONS", 10);
        } else {
            tapElement("GIFT_CARD_PDP_DANA_OPTIONS", 10);
        }
    }

    public void tapChooseNominalButtonGiftCoupon() {
        if (isElementExist("GIFT_CARD_PDP_CHOOSE_NOMINAL_BUTTON")) {
            tapElement("GIFT_CARD_PDP_CHOOSE_NOMINAL_BUTTON");
            chooseGiftCouponNominal();
            setGiftCouponPrice();
        } else {
            GiftCreditsCouponData.setDealsPrice(getIntFromRp(getTextFromElement("GIFT_CARD_PDP_AVAILABLE_PRICE")));
        }
    }

    public void setRecipientName() {
        String fullName = fakerFullName("").replace(".", "");

        typeValue("GIFT_CARD_PDP_RECIPIENT_NAME", fullName);
        GiftCreditsCouponData.setRecipientName(fullName);
    }

    public void setRecipientPhoneNumber(String recipientType) {
        String phoneNumber = fakerPhoneNumber("+62812", 8);

        if (recipientType.equals("valid")) {
            typeValue("GIFT_CARD_PDP_RECIPIENT_PHONE", phoneNumber);
            GiftCreditsCouponData.setRecipientPhoneNumber(phoneNumber);
        } else if (recipientType.equals("invalid")) {
            typeValue("GIFT_CARD_PDP_RECIPIENT_PHONE", "241244332123");
        }
    }

    public void chooseGiftCouponNominal() {
        changeContext().toWebview();
        waitForVisibilityOf("GIFT_CARD_PDP_CHANGE_NOMINAL_CHOOSE_BUTTON", 20);
        tapElements("GIFT_CARD_PDP_CHANGE_NOMINAL_RADIO_BUTTON", 0);
        tapElement("GIFT_CARD_PDP_CHANGE_NOMINAL_CHOOSE_BUTTON");
    }

    public void chooseGiftCardNominal(int index) {
        changeContext().toWebview();

        if (GiftCreditsCouponData.getGiftCardType().equals("credits")) {
            tapElements("GIFT_CARD_PDP_NOMINAL_CREDITS_SKU_BUTTON", index);
        } else {
            tapElements("GIFT_CARD_PDP_NOMINAL_DANA_SKU_BUTTON", index);
        }
    }

    public void sendGiftCard() {
        waitForVisibilityOf("GIFT_CARD_PDP_SEND_BUTTON", 20);
        validatePrice();
        GiftCreditsCouponData.setTotalPrice(getIntFromRp(getTextFromElement("GIFT_CARD_PDP_TOTAL_PRICE")));
        tapElement("GIFT_CARD_PDP_SEND_BUTTON");
        HelperData.setLastActionPage(new GiftCardProductDetailPage(iosDriver));
    }

    public void validatePrice() {
        int actualPrice = getIntFromRp(getTextFromElement("GIFT_CARD_PDP_TOTAL_PRICE"));
        int expectedPrice = GiftCreditsCouponData.getDealsPrice();

        assertEquals(expectedPrice, actualPrice);
    }

    public void validateFormatPhoneNumberInvalid() {
        validateValue().contains(GiftCreditsCouponData.invalidPhoneFormatError, getText("GIFT_CARD_PDP_INVALID_PHONE_ERROR_TEXT"));
        HelperData.setLastActionPage(new GiftCardProductDetailPage(iosDriver));
    }

    public void setGiftCardName(String giftType) {
        if (giftType.equals("credits")) {
            GiftCreditsCouponData.setDealsName(getTextFromElement("GIFT_CARD_PDP_GIFT_CREDITS_NAME").trim());
        } else {
            GiftCreditsCouponData.setDealsName(getTextFromElement("GIFT_CARD_PDP_GIFT_DANA_NAME").trim());
        }
    }

    public void setGiftCouponName() {
        GiftCreditsCouponData.setDealsName(getTextFromElement("GIFT_CARD_PDP_DEALS_NAME"));
    }

    public void setGiftCouponPrice() {
        GiftCreditsCouponData.setDealsPrice(getIntFromRp(getTextFromElement("GIFT_CARD_PDP_DEALS_PRICE")));
    }

    public void setGiftCardPrice(int index) {
        String skuTotalPriceText;

        if (GiftCreditsCouponData.getGiftCardType().equals("credits")) {
            skuTotalPriceText = getTextFromElement(constructLocator("GIFT_CARD_PDP_NOMINAL_CREDITS_SKU_TEXT", index + 1));
        } else {
            skuTotalPriceText = getTextFromElement(constructLocator("GIFT_CARD_PDP_NOMINAL_DANA_SKU_TEXT", index + 1));
        }

        GiftCreditsCouponData.setDealsPrice(getIntFromRp(skuTotalPriceText));
    }

    public void tapTNCSection() {
        tapElement("GIFT_CARD_PDP_TNC_SECTION");
    }

    public void validateTNC() {
        changeContext().toWebview();
        verifyElementExist("GIFT_CARD_PDP_SYARAT_KETENTUAN",
                20, "TNC can't to loaded");
        verifyElementExist("GIFT_CARD_PDP_TNC_TEXT");
        verifyElementExist("GIFT_CARD_PDP_TNC_DANA_TAB");
        verifyElementExist("GIFT_CARD_PDP_TNC_CREDITS_TAB");
        HelperData.setLastActionPage(new GiftCardProductDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
