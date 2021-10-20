package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiCovidLandingPage extends VpBasePage {
  public AsuransiCovidLandingPage(IOSDriver<IOSElement> iosDriver) {
    super(iosDriver);
  }

  public void userOnAsuransiCovidLandingPage() {
    validateExist("ASURANSI_COVID_HEADER_TITLE", 10);
    validateExist("ASURANSI_COVID_HEADER_DESCRIPTION", 5);
    validateExist("ASURANSI_COVID_HEADER_PARTNER_LOGO_IMAGE", 5);
    HelperData.setLastActionPage(new AsuransiCovidLandingPage(iosDriver));
  }

  public void validateProductOne() {
    swipeUpToElement("ASURANSI_COVID_PRODUCT_ONE_TITLE");
    validateExist("ASURANSI_COVID_PRODUCT_ONE_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_PARTNER_LOGO", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_BIAYA_PREMI_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_BIAYA_PREMI_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_LIMIT_KLAIM_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_LIMIT_KLAIM_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_MANFAAT_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_MANFAAT_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_LIHAT_DETAIL_BUTTON", 5);
    validateExist("ASURANSI_COVID_PRODUCT_ONE_BELI_BUTTON", 5);
  }

  public void validateProductTwo() {
    swipeUpToElement("ASURANSI_COVID_PRODUCT_TWO_BELI_BUTTON");
    validateExist("ASURANSI_COVID_PRODUCT_TWO_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_PARTNER_LOGO", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_BIAYA_PREMI_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_BIAYA_PREMI_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_LIMIT_KLAIM_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_LIMIT_KLAIM_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_MANFAAT_TITLE", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_MANFAAT_TEXT", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_LIHAT_DETAIL_BUTTON", 5);
    validateExist("ASURANSI_COVID_PRODUCT_TWO_BELI_BUTTON", 5);
  }

  public void validateKeySellingSection() {
    swipeUpToElement("ASURANSI_COVID_FOOTER_PENYEDIA_TITLE");
    validateExist("ASURANSI_COVID_BENEFIT_SECTION_TITLE");
    validateExist("ASURANSI_COVID_BENEFIT_ONE_TITLE");
    validateExist("ASURANSI_COVID_BENEFIT_ONE_DESCRIPTION");
    validateExist("ASURANSI_COVID_BENEFIT_ONE_IMAGE", 5);
    validateExist("ASURANSI_COVID_BENEFIT_TWO_TITLE");
    validateExist("ASURANSI_COVID_BENEFIT_TWO_DESCRIPTION");
    validateExist("ASURANSI_COVID_BENEFIT_TWO_IMAGE", 5);
    validateExist("ASURANSI_COVID_BENEFIT_THIRD_TITLE");
    validateExist("ASURANSI_COVID_BENEFIT_THIRD_DESCRIPTION");
    validateExist("ASURANSI_COVID_BENEFIT_THIRD_IMAGE", 5);
  }

  public void validatePromoSection() {
    if (!isElementExist("ASURANSI_COVID_BANNER_PROMO_IMAGE", 10)) {
      LogUtil.info("no promo banner");
    }
  }

  public void validateFooter() {
    validateElementWithText("ASURANSI_COVID_FOOTER_PENYEDIA_TITLE", "Penyedia:");
    validateExist("ASURANSI_COVID_FOOTER_PENYEDIA_IMAGE", 5);
    validateElementWithText("ASURANSI_COVID_FOOTER_OJK_TITLE", "Diawasi oleh:");
    validateExist("ASURANSI_COVID_FOOTER_OJK_IMAGE", 5);
    HelperData.setLastActionPage(new AsuransiCovidLandingPage(iosDriver));
  }

  public void goToHomePage() {
    backToHomePageViaDeeplink();
    HelperData.setLastActionPage(new HomePage(iosDriver));
  }
}
