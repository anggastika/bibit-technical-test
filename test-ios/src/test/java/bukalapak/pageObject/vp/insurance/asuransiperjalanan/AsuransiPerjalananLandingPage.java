package bukalapak.pageObject.vp.insurance.asuransiperjalanan;

import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.vp.insurance.InsuranceProductDetailPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiPerjalananLandingPage extends VpBasePage {
    public AsuransiPerjalananLandingPage(IOSDriver<IOSElement> iosDriver){
      super(iosDriver);
    }
    public void tapOnInsuranceButton(String product) {
      if (product.equals("Kereta")) {
        tapElement("ASURANSI_PERJALANAN_TRAIN_ENTRY_POINT_BUTTON");
      } else tapElement("ASURANSI_PERJALANAN_FLIGHT_ENTRY_POINT_BUTTON");
    }

    public void validateOnDetailPage() {
      changeContext().toWebview();
      validateExist("ASURANSI_PERJALANAN_DETAIL_PAGE_TITLE_TEXT", 10);
      validateExist("ASURANSI_PERJALANAN_DETAIL_DROPDOWN_INSURANCE_NAME", 5);

      int loop = 0;
      while (isElementExist("ASURANSI_PERJALANAN_DETAIL_POPUP_INFO", 5) && loop < 3) {
        tapElement("ASURANSI_PERJALANAN_DETAIL_POPUP_INFO");
        loop++;
      }
      HelperData.setLastActionPage(new InsuranceProductDetailPage(iosDriver));
    }

    public void validateAllSection() {
      String[] sections = {"MANFAAT", "CAKUPAN", "KETENTUAN", "CARA_KLAIM", "TANYA_JAWAB"};

      for (String section : sections) {
        tapElement("ASURANSI_PERJALANAN_DETAIL_TAB_" + section);
        validateExist("ASURANSI_PERJALANAN_DETAIL_TAB_" + section + "_TITLE_TEXT", 10);
      }
      HelperData.setLastActionPage(new InsuranceProductDetailPage(iosDriver));
    }

    public void tapOnCariTiketPerjalanan() {
      tapElement("ASURANSI_PERJALANAN_DETAIL_CARI_TIKET_BUTTON");
      changeContext().toNative(); //go to search squad step definition
      HelperData.setLastActionPage(new InsuranceProductDetailPage(iosDriver));
    }

    public void goToHomePage() {
      backToHomePageViaDeeplink();
      HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
