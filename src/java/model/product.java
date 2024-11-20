package model;

public class product {

    private int id;
    private String name;
    private double price;
    private String description;
    private String image;
    private int CategoryID;
    private int ManufacturerID;
    private int stock;

    public product() {
    }

    public product(int id, String name, double price, String description, String image, int CategoryID, int ManufacturerID, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.CategoryID = CategoryID;
        this.ManufacturerID = ManufacturerID;
        this.stock = stock;
    }

    

    public product(String name, double price, String description, String image, int CategoryID, int ManufacturerID, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.CategoryID = CategoryID;
        this.ManufacturerID = ManufacturerID;
        this.id = id;
    }

//    public product(int id, String image, String name, double price, String description) {
//        this.id = id;
//        this.image = image;
//        this.name = name;
//        this.price = price;
//        this.description = description;
//    }
//
//    public product(String name, double price, String image, int CategoryID, int ManufacturerID) {
//        this.image = image;
//        this.name = name;
//        this.price = price;
//        this.ManufacturerID = ManufacturerID;
//        this.CategoryID = CategoryID;
//    }
    public product(String name, double price, String description, String image, int CategoryID, int ManufacturerID) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
        this.ManufacturerID = ManufacturerID;
        this.CategoryID = CategoryID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "product{" + "id=" + id + ", name=" + name + ", price=" + price + ", image=" + image + ", CategoryID=" + CategoryID + ", ManufacturerID=" + ManufacturerID + ", stock=" + stock + '}';
    }

    
}
