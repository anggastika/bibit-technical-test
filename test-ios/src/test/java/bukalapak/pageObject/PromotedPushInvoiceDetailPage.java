package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.PROMData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedPushInvoiceDetailPage extends BasePage {

    public PromotedPushInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnInvoiceDetailPage() {
        waitForVisibilityOf("invoice_detail_detail_tagihan_title", 60);
        HelperData.setLastActionPage(new PromotedPushInvoiceDetailPage(iosDriver));
    }

    public void checkInformasiTagihan() {
        if (!isElementVisible("detail_pembelian_status_pesanan_text")) {
            tapElement("detail_pembelian_informasi_pesanan");
        }
        if (isElementVisible("detail_pembelian_rincian_button")) {
            tapElement("detail_pembelian_rincian_button");
        }
        assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("total_tagihan_price_text"), "Price isnt matched with the expected!");
        HelperData.setLastActionPage(new PromotedPushInvoiceDetailPage(iosDriver));
    }

    private String getFillingTypeText() {
        return getText("promoted_push_invoice_tipe_pengisian_text");
    }

    public void checkPromotedPushBudgetSection(String type) {
        swipeDownToElement("promoted_push_invoice_tipe_pengisian_text");
        String nominalBudgetText = getTextFromElement("promoted_push_invoice_nominal_budget_text");
        if (nominalBudgetText.contains("+")) {
            String[] budget = nominalBudgetText.split("\\+", 2);
            assertEquals(PROMData.getInputtedBudget(), parseIntegerFromText(budget[0]));
            assertEquals(PROMData.getNominalBudgetBonus(), parseIntegerFromText(budget[1]));
        } else {
            assertEquals(PROMData.getInputtedBudget(), getIntegerFromTextElement("promoted_push_invoice_nominal_budget_text"));
        }
        if (type.equalsIgnoreCase("sekali isi")) {
            assertEquals("Sekali Isi", getFillingTypeText());
        } else {
            assertEquals("Harian", getFillingTypeText());
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
