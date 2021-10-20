package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InspirasiPenjualanPage extends BasePage {
    private static DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("id", "ID"));

    public InspirasiPenjualanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnInspirasiPenjualanPage() {
        waitForVisibilityOf("inspirasi_penjualan_title_text", 10);
        waitForVisibilityOf("inspirasi_penjualan_terlaris_dibukalapak_text", 20);
        HelperData.setLastActionPage(new InspirasiPenjualanPage(iosDriver));
    }

    public void verifyMenuInspirasiPenjualan(String menuInspirasiPenjualan) {
        verifyElementExist(constructLocator("inspirasi_penjualan_terlaris_general_text", menuInspirasiPenjualan));
        HelperData.setLastActionPage(new InspirasiPenjualanPage(iosDriver));
    }

    public void clickMenuInspirasiPenjualan(String menuInspirasiPenjualan) {
        tapElement(constructLocator("inspirasi_penjualan_terlaris_general_text", menuInspirasiPenjualan));
    }

    public void clickDatePeriode(int period) {
        switch (period) {
            case 7 :
                waitForVisibilityOf(constructLocator("inspirasi_penjualan_date_periode_btn", 1), 15);
                tapElement(constructLocator("inspirasi_penjualan_date_periode_btn", 1));
                break;
            case 14 :
                waitForVisibilityOf(constructLocator("inspirasi_penjualan_date_periode_btn", 2), 15);
                tapElement(constructLocator("inspirasi_penjualan_date_periode_btn", 2));
                break;
            default :
                waitForVisibilityOf(constructLocator("inspirasi_penjualan_date_periode_btn", 3), 15);
                tapElement(constructLocator("inspirasi_penjualan_date_periode_btn", 3));
        }
    }

    public void verifyDatePeriode(int period) {
        String startDate = LocalDate.now().minusDays(period).format(fullDateFormatter);
        String todayDate = LocalDate.now().format(fullDateFormatter);
        // sometimes get error retrieve value, so need some delay
        waitFor(1);
        waitForVisibilityOf("inspirasi_penjualan_date_periode_txt", 20);
        assertEquals(startDate + " - " + todayDate, getElementValue("inspirasi_penjualan_date_periode_txt"));
        HelperData.setLastActionPage(new InspirasiPenjualanPage(iosDriver));
    }

    public void verifySuperSellerModal() {
        waitForVisibilityOf("super_seller_modal_title", 20);
        verifyElementExist("super_seller_modal_desc_text");
        verifyElementExist("super_seller_modal_aktifkan_super_seller_button");
        waitForVisibilityOf("super_seller_modal_x_button", 15);
    }

    public void clickCloseSuperSellerModal() {
        tapElement("super_seller_modal_x_button", 10);
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
