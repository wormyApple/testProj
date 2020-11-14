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

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
