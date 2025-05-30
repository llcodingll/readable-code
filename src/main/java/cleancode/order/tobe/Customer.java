package cleancode.order.tobe;

public class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public boolean isEmpty() {
        return (name == null || name.isBlank()) &&
            (email == null || email.isBlank());
    }
}
