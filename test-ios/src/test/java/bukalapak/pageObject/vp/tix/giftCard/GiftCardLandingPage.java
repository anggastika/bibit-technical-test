package bukalapak.pageObject.vp.tix.giftCard;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.GiftCreditsCouponData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 21/04/20, Tue
 **/
public class GiftCardLandingPage extends BasePage {

    public GiftCardLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        changeContext().toNative();
        waitForVisibilityOf("GIFTCARD_LANDING_COUPONS_TITLE_TEXT", 60);
        verifyElementDisplayed("GIFTCARD_LANDING_SEND_CREDITS_BUTTON");
        HelperData.setLastActionPage(new GiftCardLandingPage(iosDriver));
    }

    public void tapOnExchangeVoucherButton() {
        waitForVisibilityOf("GIFTCARD_LANDING_EXCHANGE_VOUCHER_CODE_BUTTON", 60);
        tapElement("GIFTCARD_LANDING_EXCHANGE_VOUCHER_CODE_BUTTON");
    }

    public void typeOnExchangeVoucherCodeField(String code) {
        validateExist("GIFTCARD_REDEEM_POPUP_TITLE", 10);
        validateExist("GIFTCARD_REDEEM_VOUCHER_CODE_INPUT", 10);
        tapElement("GIFTCARD_REDEEM_VOUCHER_CODE_INPUT");
        typeValue("GIFTCARD_REDEEM_VOUCHER_CODE_INPUT", code);
    }

    public void tapOnRedeemVoucherCodeButton() {
        tapElement("GIFTCARD_REDEEM_VOUCHER_CODE_BUTTON");
    }

    public void tapOnMasukinUlangButton() {
        tapElement("GIFTCARD_REDEEM_MASUKIN_ULANG_BUTTON");
    }

    public void validateInvalidVoucherCodeText() {
        validateExist("GIFTCARD_REDEEM_INVALID_VOUCHER_CODE_TEXT", 10);
        HelperData.setLastActionPage(new GiftCardLandingPage(iosDriver));
    }

    public void validateTryLaterText() {
        validateExist("GIFTCARD_REDEEM_TRY_LATER_TEXT", 10);
        HelperData.setLastActionPage(new GiftCardLandingPage(iosDriver));
    }

    public void tapOnOkeButton() {
        tapElement("GIFTCARD_REDEEM_OKE_BUTTON");
        HelperData.setLastActionPage(new GiftCardLandingPage(iosDriver));
    }

    public void inputInvalidCode(int invalidCount) {
        int count = 0;
        String fakeCode = fakerZipCode();

        do {
            if (isElementExist("GIFTCARD_REDEEM_INVALID_VOUCHER_CODE_TEXT", 10)) {
                tapOnMasukinUlangButton();
            }
            typeOnExchangeVoucherCodeField(fakeCode);
            tapOnRedeemVoucherCodeButton();
            count+=1;
        } while (count < invalidCount);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnSendGiftCardButton(String giftCardType) {
        if (giftCardType.equals("credits") || giftCardType.equals("DANA")) {
            tapElement("GIFTCARD_LANDING_SEND_CREDITS_BUTTON");
        } else if (giftCardType.equals("coupons")) {
            changeContext().toWebview();
            validateExist("GIFTCARD_LANDING_SEE_ALL_COUPON_BUTTON", 10);
            tapElement("GIFTCARD_LANDING_SEE_ALL_COUPON_BUTTON");
            validateExist("GIFTCARD_LANDING_DEALS_NAME", 10);
            GiftCreditsCouponData.setDealsName(getTextFromElement("GIFTCARD_LANDING_DEALS_NAME", 0));
            tapElements("GIFTCARD_LANDING_SEND_COUPONS_BUTTON", 0);
        } else {
            LogUtil.error(giftCardType + " is not supported yet");
        }
    }

    public void tapOnSeeAllMerchant() {
        changeContext().toWebview();
        validateExist("GIFTCARD_LANDING_SEE_ALL_COUPON_BUTTON", 10);
        tapElement("GIFTCARD_LANDING_SEE_ALL_COUPON_BUTTON");
    }

    public void validateListMerchant() {
        validateExist("GIFTCARD_LANDING_DEALS_NAME", 10);
        tapElement("GIFTCARD_LANDING_DEALS_NAME");
    }

    public void setGiftCardType(String giftCardType) {
        GiftCreditsCouponData.setGiftCardType(giftCardType);
    }
}
