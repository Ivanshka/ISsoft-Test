public class PriceEstimater {
    private int estimatedPrice;

    public PriceEstimater(int price){
        estimatedPrice = price;
    }

    public int estimate(){
        return estimatedPrice;
    }

    public void addPrice(int price){
        estimatedPrice += price;
    }
}
