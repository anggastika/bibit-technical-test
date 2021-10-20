package bukalapak.pageObject.vp.tix.giftCard;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.GiftCreditsCouponData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class GiftCardCheckoutPage extends BasePage {
    public GiftCardCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        if (GiftCreditsCouponData.getGiftCardType().equals("credits")) {
            waitForVisibilityOf("GIFTCARD_CHECKOUT_GIFT_CREDITS_NAME", 5);
            validateValue().equals(GiftCreditsCouponData.getDealsName(), getText("GIFTCARD_CHECKOUT_GIFT_CREDITS_NAME"));
            waitForVisibilityOf("GIFTCARD_NAMA_GIFT_CARD", 5);
            validateNotExist("GIFTCARD_NAMA_KUPON", 5);
        } else if (GiftCreditsCouponData.getGiftCardType().equals("coupons")) {
            waitForVisibilityOf("GIFTCARD_CHECKOUT_GIFT_COUPONS_NAME", 5);
            validateValue().equals(GiftCreditsCouponData.getDealsName(), getText("GIFTCARD_CHECKOUT_GIFT_COUPONS_NAME"));
            waitForVisibilityOf("GIFTCARD_NAMA_KUPON", 5);
            validateNotExist("GIFTCARD_NAMA_GIFT_CARD", 5);
        }
        waitForVisibilityOf("GIFTCARD_CHECKOUT_RECIPIENT_NAME", 5);
        waitForVisibilityOf("GIFTCARD_CHECKOUT_RECIPIENT_PHONE_NUMBER", 5);
        validateValue().equals(GiftCreditsCouponData.getRecipientName(), getText("GIFTCARD_CHECKOUT_RECIPIENT_NAME"));
        validateValue().equals(GiftCreditsCouponData.getRecipientPhoneNumber(), getText("GIFTCARD_CHECKOUT_RECIPIENT_PHONE_NUMBER"));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
