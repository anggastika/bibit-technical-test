package bukalapak.stepDefinitions.vp.travel.giftCard;

import bukalapak.TestInstrument;

import cucumber.api.java8.En;

public class GiftCardDetailPageStepDefinitions extends TestInstrument implements En {
    public GiftCardDetailPageStepDefinitions(){

        And("^user buys gift card (credits|DANA) on SKU (\\d+) with (valid|invalid) recipient info$", (String giftCardType, Integer skuIndex, String validity) -> {
            bukalapak.giftCardLandingPage().tapOnSendGiftCardButton(giftCardType);
            bukalapak.giftCardLandingPage().setGiftCardType(giftCardType);
            bukalapak.productDetailPageGiftCard().validateOnGiftCardPage();
            bukalapak.productDetailPageGiftCard().tapOnGiftByType(giftCardType);
            bukalapak.productDetailPageGiftCard().setGiftCardName(giftCardType);
            bukalapak.productDetailPageGiftCard().chooseGiftCardNominal(skuIndex);
            bukalapak.productDetailPageGiftCard().setGiftCardPrice(skuIndex);
            bukalapak.productDetailPageGiftCard().setRecipientName();
            bukalapak.productDetailPageGiftCard().setRecipientPhoneNumber(validity);
            bukalapak.productDetailPageGiftCard().sendGiftCard();
        });

        And("^user buys gift card coupons with (valid|invalid) recipient info$", (String validity) -> {
            bukalapak.giftCardLandingPage().tapOnSendGiftCardButton("coupons");
            bukalapak.giftCardLandingPage().setGiftCardType("coupons");
            bukalapak.productDetailPageGiftCard().validateOnGiftCouponPage();
            bukalapak.productDetailPageGiftCard().tapChooseNominalButtonGiftCoupon();
            bukalapak.productDetailPageGiftCard().setRecipientName();
            bukalapak.productDetailPageGiftCard().setRecipientPhoneNumber(validity);
            bukalapak.productDetailPageGiftCard().sendGiftCard();
        });

        And("user see terms and conditions", () -> {
            bukalapak.productDetailPageGiftCard().validateOnGiftCardPage();
            bukalapak.productDetailPageGiftCard().tapTNCSection();
        });

        And("terms and conditions will have displayed", () -> {
            bukalapak.productDetailPageGiftCard().validateTNC();
        });

        And("error message format is invalid will have displayed", () -> {
            bukalapak.productDetailPageGiftCard().validateFormatPhoneNumberInvalid();
        });

        And("user taps on see all merchants button", () -> {
            bukalapak.giftCardLandingPage().tapOnSeeAllMerchant();
        });

        And("all merchants page will be displayed", () -> {
            bukalapak.giftCardLandingPage().validateListMerchant();
        });
    }
}
