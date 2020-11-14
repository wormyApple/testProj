package web.methods;

public class DataModel {

    private String basicInfo;
    private String address;
    private String price;
    private String description;

    public DataModel(String basicInfo, String adress, String price, String description) {
        this.basicInfo = basicInfo;
        this.address = adress;
        this.price = price;
        this.description = description;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
