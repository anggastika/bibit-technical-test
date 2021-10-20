package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.vp.tix.tiketpesawat.TiketPesawatInvoiceDetailPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiCovidInvoiceDetailPage extends VpBasePage {

    public AsuransiCovidInvoiceDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionData() {
        swipeUpToElement("invoice_non_marketplace_produk_asuransi");
        assertEquals(InsuranceData.getInsuredName(), getElementLabel("invoice_non_marketplace_produk_asuransi"));
        assertEquals(InsuranceData.getProductName(), getElementLabel("invoice_non_marketplace_jenis_asuransi"));
        assertEquals(InsuranceData.getValidityPeriode(), getElementLabel("invoice_non_marketplace_masa_berlaku_asuransi"));
        assertEquals(InsuranceData.getPremiValue(), getElementLabel("invoice_non_marketplace_masa_jumlah_premi_asuransi").toLowerCase());

        HelperData.setLastActionPage(new AsuransiCovidInvoiceDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
