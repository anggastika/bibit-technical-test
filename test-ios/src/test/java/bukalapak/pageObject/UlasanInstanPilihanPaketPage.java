package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.PRIOData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.List;

public class UlasanInstanPilihanPaketPage extends BasePage {

    public UlasanInstanPilihanPaketPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnUlasanInstanPilihanPaketPage() {
        int countPackage = 1;
        waitForVisibilityOf("ulasan_instan_pilihan_paket_title_text", 20);
        String multiplePackages = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_MULTIPLE_PACKAGES_STATUS");
        assert multiplePackages != null;
        if (multiplePackages.equalsIgnoreCase("true")) {
            String numberOfPackage = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_MULTIPLE_PACKAGES_NUMBER");
            assert numberOfPackage != null;
            int autoPackages = Integer.parseInt(numberOfPackage);
            assertEquals(autoPackages, getMaxItemsElements().size(), "Total packages isn't matched with expected!");
            String promoStatus = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_MULTIPLE_PACKAGES_PROMO_STATUS");
            assert promoStatus != null;
            for (int i = 1; i <= autoPackages; i++) {
                //initiate env of prio data
                String maxReview = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + i + "_REVIEWS_NUMBER");
                String maxItems = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + i + "_ITEMS_NUMBER");
                String duration = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + i + "_DURATION");
                String price = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + i + "_PRICE");
                String maxReviewsPerItem = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_REVIEW_PER_ITEM");
                assert maxReview != null;
                assert maxItems != null;
                assert duration != null;
                assert price != null;
                assert maxReviewsPerItem != null;
                //get prio data
                PRIOData.setNumberOfReview(getMaxUlasanElements().get(i - 1).getText());
                PRIOData.setNumberOfItems(getMaxItemsElements().get(i - 1).getText());
                PRIOData.setDuration(getDurationElements().get(i - 1).getText());
                PRIOData.setNormalPrice(getPriceElements().get(i - 1).getText());
                PRIOData.setNumberOfReviewPerItem(getReviewsPerItem().get(i - 1).getText());
                if (promoStatus.equalsIgnoreCase("true")) {
                    String numberOfPromo = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_MULTIPLE_PACKAGES_PROMO_NUMBER");
                    assert numberOfPromo != null;
                    PRIOData.setNumberOfPromo(getNumberOfPromo());
                    assertEquals(getNumberOfPromo(), Integer.parseInt(numberOfPromo));
                    if (isElementVisible(constructLocator("ulasan_instan_pilihan_paket_promo_text_details", i - 1), 3)) {
                        PRIOData.setPromoPrice(getPromoPrice().get(i - countPackage).getText());
                    } else {
                        PRIOData.setPromoPrice("0");
                        countPackage++;
                    }
                    assertNotSame(PRIOData.getNormalPrice().get(i - 1), PRIOData.getPromoPrice().get(i - 1));
                }
                //Assert prio data
                assertTrue(PRIOData.getNumberOfReview().get(i - 1).contains(maxReview),
                        "Total review isnt matched with expected!");
                assertTrue(PRIOData.getNumberOfItems().get(i - 1).contains(maxItems),
                        "Total item isnt matched with expected!");
                assertTrue(PRIOData.getDuration().get(i - 1).contains(duration),
                        "Duration isnt matched with expected!");
                assertTrue(PRIOData.getNormalPrice().get(i - 1).contains(price),
                        "Price isnt matched with expected!");
            }
        }
        HelperData.setLastActionPage(new UlasanInstanPilihanPaketPage(iosDriver));
    }

    public void userSelectAutomaticReviewPackage(int selection) {
        getMaxUlasanElements().get(selection-1).click();
    }

    private List<IOSElement> getMaxUlasanElements() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_reviews_max_text", 20);
        return getElements("ulasan_instan_pilihan_paket_reviews_max_text");
    }

    private List<IOSElement> getMaxItemsElements() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_items_max_text", 20);
        return getElements("ulasan_instan_pilihan_paket_items_max_text");
    }

    private List<IOSElement> getDurationElements() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_duration_text", 20);
        return getElements("ulasan_instan_pilihan_paket_duration_text");
    }

    private List<IOSElement> getPriceElements() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_price_text", 20);
        return getElements("ulasan_instan_pilihan_paket_price_text");
    }

    private List<IOSElement> getReviewsPerItem() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_max_reviews_per_item_text", 20);
        return getElements("ulasan_instan_pilihan_paket_max_reviews_per_item_text");
    }

    private int getNumberOfPromo() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_promo_text", 20);
        return getElements("ulasan_instan_pilihan_paket_promo_text").size();
    }

    private List<IOSElement> getPromoPrice() {
        waitForVisibilityOf("ulasan_instan_pilihan_paket_promo_price_text", 20);
        return getElements("ulasan_instan_pilihan_paket_promo_price_text");
    }

    @Deprecated
    public void userOnPilihanPaketDetailPage(int selection) {
        String maxReview = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + selection + "_REVIEWS_NUMBER");
        String maxItems = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + selection + "_ITEMS_NUMBER");
        String duration = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + selection + "_DURATION");
        String price = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_PACKAGE_" + selection + "_PRICE");
        String maxReviewPerItem = TestInstrument.dotenv.get("AUTOMATIC_REVIEW_REVIEW_PER_ITEM");
        assert maxReview != null;
        assert maxItems != null;
        assert duration != null;
        assert price != null;
        assert maxReviewPerItem != null;
        assertTrue(getTextFromElement("ulasan_instan_pilihan_single_package_total_review_text").contains(maxReview),
                "Total review isnt matched with expected!");
        assertTrue(getTextFromElement("ulasan_instan_pilihan_single_package_max_items_text").contains(maxItems),
                "Total item isnt matched with expected!");
        assertTrue(getTextFromElement("ulasan_instan_pilihan_single_package_duration_text").contains(duration),
                "Duration isnt matched with expected!");
        assertTrue(getTextFromElement("ulasan_instan_pilihan_single_package_price_text").contains(price),
                "Price isnt matched with expected!");
        assertTrue(getTextFromElement("ulasan_instan_pilihan_single_package_max_review_per_item_text").contains(maxReviewPerItem),
                "Total reviews for one product isnt matched with expected!");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
