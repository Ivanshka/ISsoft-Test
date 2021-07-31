public class OrderProduct {
    private String productId;
    private int amount;

    public OrderProduct(String productId, int quantity){
        this.productId = productId;
        this.amount = quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setAmount(int val){
        amount = val;
    }

    public int getAmount() {
        return amount;
    }
}