package cleancode.order.tobe;

public class Item {
    private String name;
    private int quantity;
    private int unitPrice;

    public Item(String name, int quantity, int unitPrice) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubtotal() {
        return quantity * unitPrice;
    }
}
