package bukalapak.pageObject.vp;

import bukalapak.data.PrepaidData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by ikhsan danu on 25/11/20.
 */
public class HistoryTransactionDetailPage extends VpBasePage {
    public HistoryTransactionDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapToShowInformasiTagihan() {
        tapElement("riwayat_transaksi_detail_paket_data_info_tagihan");
    }

    public void validateTrxDetail(String product) {
        validateExist(constructLocator("riwayat_transaksi_detail_produk", product), 3);
    }

    public void validateTotalTagihan() {
        assertEquals(PrepaidData.getTotalTagihan(), getText("riwayat_transaksi_detail_paket_data_total_tagihan"));
    }
}
