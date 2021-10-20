package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * @author Andi Maryono
 */

public class CatalogReviewListingPage extends BasePage {

    private static final String ALL = "Semua";
    private static final String ONE_STAR = "1 star";
    private static final String TWO_STAR = "2 star";
    private static final String THREE_STAR = "3 star";
    private static final String FOUR_STAR = "4 star";
    private static final String FIVE_STAR = "5 star";
    private static final String WITH_FOTO = "Dengan Foto";
    private static final String WITH_DESCRIPTION = "Dengan Deskripsi";
    private static final String INVALID_FILTER = "Invalid review listing filter";

    public CatalogReviewListingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnCatalogReviewListingPage() {
        verifyElementExist("catalog_review_listing_page_title");

        HelperData.setLastActionPage(new CatalogReviewListingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        if (isElementVisible("back_button_produt_listing_revamp")) {
            tapElement("back_button_produt_listing_revamp");
        }

        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyFilteredContent(String filter) {
        switch (filter) {
            case ALL:
                filteredContentVerifier("catalog_review_listing_semua_ulasan_text", ALL);
                break;
            case ONE_STAR:
                filteredContentVerifier("catalog_review_listing_1_star_text", ONE_STAR);
                break;
            case TWO_STAR:
                filteredContentVerifier("catalog_review_listing_2_star_text", TWO_STAR);
                break;
            case THREE_STAR:
                filteredContentVerifier("catalog_review_listing_3_star_text", THREE_STAR);
                break;
            case FOUR_STAR:
                filteredContentVerifier("catalog_review_listing_4_star_text", FOUR_STAR);
                break;
            case FIVE_STAR:
                filteredContentVerifier("catalog_review_listing_5_star_text", FIVE_STAR);
                break;
            case WITH_FOTO:
                filteredContentVerifier("catalog_review_listing_dengan_foto_text", WITH_FOTO);
                break;
            case WITH_DESCRIPTION:
                filteredContentVerifier("catalog_review_listing_dengan_deskripsi_text", WITH_DESCRIPTION);
                break;
            default:
                Assert.fail(INVALID_FILTER);
        }
    }

    private void filteredContentVerifier(String filterLocator, String filter) {
        if (isElementVisible(filterLocator, 20)) {
            verifyElementExist(filterLocator);
        } else {
            Assert.fail("Filter " + filter + " didn't displayed");
        }
    }

    public void filterReviewListing(String filter) {
        waitForVisibilityOf("catalog_review_listing_rating_icon", 10);
            switch (filter) {
                case ALL:
                    tapElement("catalog_review_listing_semua_button");
                    break;
                case ONE_STAR:
                    tapElement("catalog_review_listing_1_star_button");
                    break;
                case TWO_STAR:
                    tapElement("catalog_review_listing_2_star_button");
                    break;
                case THREE_STAR:
                    tapElement("catalog_review_listing_3_star_button");
                    break;
                case FOUR_STAR:
                    tapElement("catalog_review_listing_4_star_button");
                    break;
                case FIVE_STAR:
                    tapElement("catalog_review_listing_5_star_button");
                    break;
                case WITH_FOTO:
                    tapElement("catalog_review_listing_dengan_foto_button");
                    break;
                case WITH_DESCRIPTION:
                    tapElement("catalog_review_listing_dengan_deskripsi");
                    break;
                default:
                    Assert.fail(INVALID_FILTER);
                    break;
            }
    }
}
