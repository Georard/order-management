package pl.edu.pwr.hyartsev.np.model;

import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

/**
 * Class responsible for creating order requests
 */
public class OrderRequest {
    private CustomerData customerData;
    private List<Product> shoppingCart;
    private String orderNumber;

    public OrderRequest() {
    }

    /**
     * It assigns an individual order number consisting of the customer's initials,
     * the quantity of items purchased,
     * and a unique hexadecimal hash code generated based on the order data.
     */
    public void assignDeterministicOrderNumber(){
        // tworzy indywidualny skrót w zależności od danych zamówienia
        int combinedHash = Objects.hash(customerData, shoppingCart);
        // zmienia int ze znakiem na szesnastkową liczbę bez znaków
        String hexHash = Integer.toHexString(combinedHash).toUpperCase();

        String initials = "";
        if(customerData != null
                && customerData.firstName() != null
                && customerData.lastName() != null){
                initials = (customerData.firstName().substring(0, 1)
                        + customerData.lastName().substring(0, 1));
        }
        int itemCount = (shoppingCart != null) ? shoppingCart.size() : 0;

        this.orderNumber = String.format("ORDER-%s_%d_%s", initials, itemCount, hexHash);
    }

    public CustomerData getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerData customerData) {
        this.customerData = customerData;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "customerData=" + customerData +
                ", shoppingCart=" + shoppingCart +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }
}
