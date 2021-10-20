package bukalapak.pageObject.vp.tix.giftCard;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.GiftCreditsCouponData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GiftCardInvoiceDetailPage extends BasePage {
    public GiftCardInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateGiftCardData() {
        swipeUpToElement("GIFTCARD_INVOICE_PARTNER_INFO_DROPDOWN");

        String giftCardType = GiftCreditsCouponData.getGiftCardType();
        switch (giftCardType) {
            case "credits":
                assertEquals(GiftCreditsCouponData.getDealsName(), getText("GIFTCARD_INVOICE_GIFT_CARD_NAME"));
                break;
            case "coupons":
                assertEquals(GiftCreditsCouponData.getDealsName(), getText("GIFTCARD_INVOICE_GIFT_COUPONS_NAME"));
                break;
            case "DANA":
                assertEquals(GiftCreditsCouponData.getDealsName(), getText("GIFTCARD_INVOICE_GIFT_DANA_NAME"));
                break;
            default:
                LogUtil.error("Gift card " + giftCardType + " has not been implemented yet");
                break;
        }

        verifyElementDisplayed("GIFTCARD_INVOICE_RECIPIENT_INFO_DROPDOWN", "Recipient info should be present");
        tapElement("GIFTCARD_INVOICE_RECIPIENT_INFO_DROPDOWN");
        waitForVisibilityOf("GIFTCARD_INVOICE_GIFT_CARD_RECIPIENT_NAME", 3);
        assertEquals(GiftCreditsCouponData.getRecipientName(), getText("GIFTCARD_INVOICE_GIFT_CARD_RECIPIENT_NAME"));
        assertEquals(GiftCreditsCouponData.getRecipientPhoneNumber(), getText("GIFTCARD_INVOICE_GIFT_CARD_RECIPIENT_PHONE_NUMBER"));

        HelperData.setLastActionPage(new GiftCardInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
