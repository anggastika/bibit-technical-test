package bukalapak.data;

import bukalapak.utils.PropertyUtil;

import java.util.ArrayList;

public class BeeData {

    private static String alamatPenerima;
    private static String car;
    private static String detailAlamat;
    private static String alamatPengirim;
    private static String weight;
    private static String packageinfo;
    private static String courier;
    private static String name;
    private static String phone;
    private static ArrayList<String> priceList = new ArrayList<String>();
    private static String totalPrice;
    private static String envKey = "BUKASEND_";
    private static String varKey = "VAR_";
    private static int count = 0;

    public static Integer getElementCount(int increment) {
        int elementCount = count + increment;
        return elementCount;
    }

    public static String getAlamatPenerima() {
        return alamatPenerima;
    }

    public static void setAlamatPenerima(String alamatPenerima) {
        BeeData.alamatPenerima = alamatPenerima.toUpperCase().contains(envKey.toUpperCase()) ? PropertyUtil.getDataEnv().get(alamatPenerima) : alamatPenerima;
    }

    public static String getDetailAlamat() {
        return detailAlamat;
    }

    public static void setDetailAlamat(String detailAlamat) {
        BeeData.detailAlamat = detailAlamat.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(detailAlamat):detailAlamat;
    }

    public static String getAlamatPengirim() {
        return alamatPengirim;
    }

    public static void setAlamatPengirim(String alamatPengirim) {
        BeeData.alamatPengirim=alamatPengirim.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(alamatPengirim):alamatPengirim;
    }

    public static String getWeight() {
        return weight;
    }

    public static void setWeight(String weight) {
        BeeData.weight = weight.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(weight):weight;
    }

    public static String getPackageInfo() {
        return packageinfo;
    }

    public static void setPackageInfo(String packageinfo) {
        BeeData.packageinfo = packageinfo.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(packageinfo):packageinfo;
    }

    public static String getCourier() {
        return courier;
    }

    public static void setCourier(String courier) {
        BeeData.courier = courier.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(courier):courier;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BeeData.name = name.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(name):name;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        BeeData.phone = phone.toUpperCase().contains(envKey.toUpperCase())?PropertyUtil.getDataEnv().get(phone):phone;
    }

    public static String getPrice() {
        return priceList.get(count);
    }

    public static void setPrice(String price) {
        BeeData.priceList.add("0");
        String tmpPrice=price;
        if (price.toUpperCase().contains(envKey.toUpperCase())) {
            tmpPrice = PropertyUtil.getDataEnv().get(price);
        } else if (price.toUpperCase().contains(varKey.toUpperCase())) {
            tmpPrice = BeeData.priceList.get(count);
        }
        BeeData.priceList.set(count, tmpPrice);
    }

    public static String getTotalPrice() {
        return totalPrice;
    }

    public static void setTotalPrice(String totalPrice) {
        String tmpTotalPrice=totalPrice;
        if (totalPrice.toUpperCase().contains(envKey.toUpperCase())) {
            tmpTotalPrice = PropertyUtil.getDataEnv().get(totalPrice);
        } else if (totalPrice.toUpperCase().contains(varKey.toUpperCase())) {
            int sum = 0;
            for (int i = 0; i < priceList.size(); i++) {
                sum += Integer.parseInt(priceList.get(i).replace(".", ""));
            }
            String str = Integer.toString(sum);
            if (str.length() > 3 && str.length() < 7) {
                tmpTotalPrice = new StringBuilder(str).insert(str.length() - 3, ".").toString();
            } else if (str.length() >= 7) {
                String newstr = new StringBuilder(str).insert(str.length() - 3, ".").toString();
                tmpTotalPrice = new StringBuilder(newstr).insert(newstr.length() - 7, ".").toString();
            }
        }
        count++;
        BeeData.totalPrice = tmpTotalPrice;
    }

    public static String getCar() {
        return car;
    }

    public static void setCar(String car) {
        BeeData.car = car;
    }
}
