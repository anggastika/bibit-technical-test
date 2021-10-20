package bukalapak.pageObject;

import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;


public class TambahKaryawanPage extends BasePage {

    public TambahKaryawanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnTambahKaryawan() {
        verifyElementExist("tambah_karyawan_title");
        verifyElementExist("base_back_button");
        verifyElementExist("tambah_karyawan_nama_field");
        verifyElementExist("tambah_karyawan_email_field");
        verifyElementExist("tambah_karyawan_hak_akses_text");
        verifyElementExist("tambah_karyawan_hak_akses_kelola_barang_checkbox");
        verifyElementExist("tambah_karyawan_hak_akses_promosi_checkbox");
        verifyElementExist("tambah_karyawan_hak_akses_kelola_transaksi_checkbox");
        verifyElementExist("tambah_karyawan_hak_akses_chat_checkbox");
        verifyElementExist("tambah_karyawan_simpan_button");
    }

    public void clickTambahKaryawanButton() {
        waitForElementClickable("tambah_karyawan_simpan_button", 20);
        tapElement("tambah_karyawan_simpan_button");
    }

    public void changePrivilegeStaff(String privilegeName) {
        switch (privilegeName) {
            case "Kelola Barang":
                waitForVisibilityOf(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName), 20);
                tapElement(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName));
                break;
            case "Kelola Transaksi":
                waitForVisibilityOf(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName), 20);
                tapElement(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName));
                break;
            case "Chat":
                waitForVisibilityOf(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName), 20);
                tapElement(constructLocator("tambah_karyawan_hak_akses_checkbox", privilegeName));
                break;
            default:
                LogUtil.info("privilage name not found");
                break;
        }
    }

    public void enterPasswordTextField() {
        waitForVisibilityOf("tambah_karyawan_password_textfield", 20);
        typeAndEnterValue("tambah_karyawan_password_textfield", dotenv.get("PREMIUM_BASIC_01_PASSWORD"));
    }

    public void clickSaveEditStaffButton() {
        waitForElementClickable("tambah_karyawan_simpan_edit_staff_button", 15);
        tapElement("tambah_karyawan_simpan_edit_staff_button");
    }

}
