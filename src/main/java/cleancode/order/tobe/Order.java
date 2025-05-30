package cleancode.order.tobe;

import java.util.List;

public class Order {
    private List<Item> items;
    private Customer customer;

    public Order(List<Item> items, Customer customer) {
        this.items = items;
        this.customer = customer;
    }

    public int getTotalItemCount() {
        return items.stream()
            .mapToInt(Item::getQuantity)
            .sum();
    }

    public int getTotalPrice() {
        return items.stream()
            .mapToInt(Item::getSubtotal)
            .sum();
    }

    public boolean isCustomerInfoEmpty() {
        return customer == null || customer.isEmpty();
    }
}
