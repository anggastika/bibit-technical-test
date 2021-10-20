package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerMutationPage extends BasePage {

    public SuperSellerMutationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSuperSellerMutation() {
        if (isElementVisible("super_seller_mutation_lihat_mutasi_btn", 5)) {
            tapElement("super_seller_mutation_lihat_mutasi_btn");
        }
        waitForVisibilityOf("super_seller_mutation_title",15);
        validateDisplayed("alchemy_navbar_back_button");
        validateDisplayed("super_seller_mutation_change_date_btn");
        HelperData.setLastActionPage(new SuperSellerMutationPage(iosDriver));
    }

    public void verifyNewSuperSellerMutationPopUp() {
        validateDisplayed("super_seller_mutation_pop_up_title");
        validateDisplayed("super_seller_mutation_pop_up_close_btn");
        validateDisplayed("super_seller_mutation_pop_up_desc_text");
        validateDisplayed("super_seller_mutation_pop_up_lihat_mutasi_btn");
    }

    public void tapTransactionId(String trxId) {
        tapElement(constructLocator("super_seller_mutation_trx_detail_link", trxId));
    }

    public void verifyInfoBiayaSuperSeller() {
        validateDisplayed("super_seller_mutation_info_biaya_title");
        validateDisplayed("super_seller_mutation_info_biaya_layanan_text");
        validateDisplayed("super_seller_mutation_info_cashback_text");
        validateDisplayed("super_seller_mutation_info_refund_biaya_layanan_text");
        validateDisplayed("super_seller_mutation_info_refund_cashback_text");
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
