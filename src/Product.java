class Product{
    private String name;
    private int productPrice;

    public Product(String name, int productPrice) {
        this.name = name;
        this.productPrice = productPrice;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
