package bukalapak.data;

public class ItemData {

    public String itemName;
    public String sellerName;
    public double itemStock;
    public double itemPrice;
    public double itemWeight;
    public String itemDescription;
    public double itemShippingFee;
    public String phoneNumber;
    public String cityDestination;
    public String itemCourier;
    public String courierName;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityDestination() {
        return cityDestination;
    }

    public void setCityDestination(String cityDestination) {
        this.cityDestination = cityDestination;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getItemCourier() {
        return itemCourier;
    }

    public void setItemCourier(String itemCourier) {
        this.itemCourier = itemCourier;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public double getItemShippingFee() {
        return itemShippingFee;
    }

    public void setItemShippingFee(double itemShippingFee) {
        this.itemShippingFee = itemShippingFee;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemStock() {
        return itemStock;
    }

    public void setItemStock(double itemStock) {
        this.itemStock = itemStock;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
}
