package bukalapak.data.bukasend;

public class FormData {
        private static String senderName;
        private static String receiverName;
        private static String receiverPhone;
        private static String postalCode;
        private static String receiverAddress;
        private static String weight;
        private static String notes;
        private static String courier;
        private static String price;
        private static String insurance;

        public static void setSenderName(String senderName) {
            FormData.senderName = senderName;
        }

        public static String getInsurance() { return insurance; }

        public static void setInsurance(String insurance) { FormData.insurance = insurance; }

        public static String getSenderName() {
            return senderName;
        }

        public static void setReceiverName(String receiverName) {
            FormData.receiverName = receiverName;
        }

        public static String getReceiverName() {
            return receiverName;
        }

        public static void setReceiverPhone(String receiverPhone) {
            FormData.receiverPhone = receiverPhone;
        }

        public static String getReceiverPhone() {
            return receiverPhone;
        }

        public static void setPostalCode(String postalCode) {
            FormData.postalCode = postalCode;
        }

        public static String getPostalCode() {
            return postalCode;
        }

        public static void setReceiverAddress(String receiverAddress) {
            FormData.receiverAddress = receiverAddress;
        }

        public static String getReceiverAddress() {
            return receiverAddress;
        }

        public static void setWeight(String weight) {
            FormData.weight = weight;
        }

        public static String getWeight() {
            return weight;
        }

        public static void setNotesForm(String notes) {
            FormData.notes = notes;
        }

        public static String getNotesForm() {
            return notes;
        }

        public static void setCourier(String courier) {
            FormData.courier = courier;
        }

        public static String getCourier() {
            return courier;
        }

        public static void setPriceBukasend(String price) {
            FormData.price = price;
        }

        public static String getPriceBukasend() {
            return price;
        }
}
