package bukalapak.pageObject.vp.insurance;

import bukalapak.data.InsuranceData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiCovidCheckoutPage extends VpBasePage {
    public AsuransiCovidCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionCovid() {
        waitForVisibilityOf("ASURANSI_PRODUCT_NAME", 10);
        assertEquals(InsuranceData.getProductName(), getElementLabel("ASURANSI_PRODUCT_NAME"));
        assertEquals(InsuranceData.getCovidInsurancedPrice(), getElementLabel("ASURANSI_HARGA_PREMI"));
        assertEquals(InsuranceData.getValidityPeriode(), getElementLabel("ASURANSI_MASA_BERLAKU"));
    }

    public void setDataInsurance() {
        InsuranceData.setPremiValue(getElementLabel("ASURANSI_JUMLAH_PREMI"));
    }
}
