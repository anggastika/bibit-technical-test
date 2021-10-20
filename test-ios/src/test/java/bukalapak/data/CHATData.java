package bukalapak.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CHATData {
    public static final String[] defaultTemplate = {
            "Apakah stok masih ada?",
            "Bisa dikirim sekarang?"
    };
    public static final String linkDihapusWording = "*link dihapus oleh sistem Bukalapak*";
    private static int testId = new Random().nextInt(10000);
    public static final String blogBukalapakLink = "https://blog.bukalapak.com";
    public static final String validMessage = "Automated valid message - " + testId;
    public static final String repliedMessage = "Replied message - " + testId;
    public static final String greyKeyword = "cobapalavergreykeyword - " + testId;
    public static final String blackKeyword = "cobapalaverblackkeyword - " + testId;
    public static final String validDeeplinkMessage = "https://www.bukalapak.com/kereta-api?pos=" + testId;
    public static final String invalidDeeplinkMessage = "https://ababa.com/" + testId;
    public static final String deeplinkRouterInChat = "https://www.bukalapak.com/pulsa";
    public static final String phisingLink = "https://www.bukalapak.com/campaign/SOFCB-WCDPA/?l=http://www.promo-hadiah-ptlazada.gq";
    public static final String template = "New template test - " + testId;
    public static final String editedTemplate = "Edit template test - " + testId;
    public static final String productLinkName = "Testing Ajalah 1";
    public static final String productLink = "https://www.bukalapak.com/p/perawatan-kecantikan/perawatan-mata/softlens/36a6na0-jual-tidak-dijval-barang";
    public static final String productLinkPrice = "Rp100.000";
    public static final String blackKeywordWarningMessage = "Sistem Bukalapak mengindikasi adanya pesan yang tidak aman buat kamu.";
    public static final String blackKeywordWarningReply = "Sistem Bukalapak mengindikasi adanya upaya penipuan dari akun ini.";
    public static final String greyKeywordWarningMessage = "Pesan ini tidak dapat dikirim karena melanggar Syarat & Ketentuan";
    private static String productName;
    private static String productPrice;
    private static Map<String, String> sendingMessageTracker;
    private static Map<String, String> clickAttachmentTracker;
    private static ArrayList<String> namaBarang;
    private static String transactionTitle;
    private static String transactionNumber;
    private static String dynamicChatData = "";
    private static String secondDynamicChatData = "";

    public static String getProductName() {
        return productName;
    }

    public static void setProductName(String productName) {
        CHATData.productName = productName;
    }

    public static String getProductPrice() {
        return productPrice;
    }

    public static void setProductPrice(String productPrice) {
        CHATData.productPrice = productPrice;
    }

    public static Map<String, String> getSendingMessageTracker() {
        return sendingMessageTracker;
    }

    public static void setSendingMessageTracker() {
        sendingMessageTracker = new HashMap<>();
        sendingMessageTracker.put("event", "chat_message");
        sendingMessageTracker.put("state", "sending");
    }

    public static Map<String, String> getClickAttachmentTracker() {
        return clickAttachmentTracker;
    }

    public static void setClickAttachmentTracker(String action) {
        clickAttachmentTracker = new HashMap<>();
        clickAttachmentTracker.put("event", "chat_attachment");
        clickAttachmentTracker.put("action", action);
    }

    public static ArrayList<String> getNamaBarang() {
        return namaBarang;
    }

    public static void setNamaBarang(ArrayList<String> namaBarang) {
        CHATData.namaBarang = namaBarang;
    }

    public static String getTransactionTitle() {
        return transactionTitle;
    }

    public static void setTransactionTitle(String transactionTitle) {
        CHATData.transactionTitle = transactionTitle;
    }

    public static String getTransactionNumber() {
        return transactionNumber;
    }

    public static void setTransactionNumber(String transactionNumber) {
        CHATData.transactionNumber = transactionNumber;
    }

    public static String getDateStamp() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    public static String getDynamicChatData() {
        return dynamicChatData;
    }

    public static void setDynamicChatData(String dynamicChatData) {
        CHATData.dynamicChatData = dynamicChatData;
    }

    public static String getSecondDynamicChatData() {
        return secondDynamicChatData;
    }

    public static void setSecondDynamicChatData(String secondDynamicChatData) {
        CHATData.secondDynamicChatData = secondDynamicChatData;
    }
}