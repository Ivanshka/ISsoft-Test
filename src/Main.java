import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        //Buffers
        BufferedReader readerOrders = new BufferedReader(new FileReader("/home/ivanshka/Загрузки/orders.csv"));

        BufferedReader readerProducts = new BufferedReader(new FileReader("/home/ivanshka/Загрузки/products.csv"));

        BufferedReader readerOrderProducts = new BufferedReader(new FileReader("/home/ivanshka/Загрузки/order_items.csv"));

        //Maps
        Map<String, LocalDateTime> orders = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        Map<String, OrderProduct> orderProducts = new HashMap<>();
        Map<String, PriceEstimater> estimaters = new HashMap<>();

        String temp;
        //Orders
        readerOrders.readLine();

        while ((temp = readerOrders.readLine()) != null){

            String[] arr = temp.split(",");

            orders.put(arr[0], LocalDateTime.parse(arr[1]));
        }

        //Products
        readerProducts.readLine();

        while ((temp = readerProducts.readLine()) != null){

            String[] arr = temp.split(",");

            products.put(arr[0], new Product(arr[1], Integer.parseInt(arr[2])));
        }

        //Order Products
        readerOrderProducts.readLine();

        while ((temp = readerOrderProducts.readLine()) != null){

            String[] arr = temp.split(",");

            orderProducts.put(arr[0], new OrderProduct(arr[1], Integer.parseInt(arr[2])));
        }

        int day = 21;

        List<String> ordersCollection = orders.entrySet().stream().filter(ord -> ord.getValue().getDayOfMonth() == day)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        for (String s : ordersCollection) {
            OrderProduct orderProduct = orderProducts.get(s);

            Product product = products.get(orderProduct.getProductId());

            if (!estimaters.containsKey(orderProduct.getProductId())) {
                estimaters.put(orderProduct.getProductId(),
                        new PriceEstimater(orderProduct.getAmount() * product.getProductPrice()));
            } else {
                estimaters.get(orderProduct.getProductId()).addPrice(orderProduct.getAmount() * product.getProductPrice());
            }
        }

        estimaters.forEach((key, value) -> System.out.println(key + " " + value.estimate()));

        String key = estimaters.entrySet().stream().findFirst().get().getKey();

        for(var entry : estimaters.entrySet()){

            if(entry.getValue().estimate() > estimaters.get(key).estimate()){
                key = entry.getKey();
            }
        }
    }
}