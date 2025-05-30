package cleancode.order.tobe;

import java.util.Collections;
import java.util.List;

public class AppRunner {
    public static void main(String[] args) {
        OrderValidator validator = new OrderValidator();

        System.out.println("======= 주문 유효성 테스트 시작 =======");

        Order emptyOrder = new Order(Collections.emptyList(), null);
        validator.isValid(emptyOrder);

        List<Item> items1 = List.of(
            new Item("노트북", 1, 1000000)
        );
        Order noCustomerOrder = new Order(items1, null);
        validator.isValid(noCustomerOrder);

        List<Item> items2 = List.of(
            new Item("샤프", 1, -100)
        );
        Customer customer1 = new Customer("홍길동", "hong@example.com");
        Order negativePriceOrder = new Order(items2, customer1);
        validator.isValid(negativePriceOrder);

        List<Item> items3 = List.of(
            new Item("모니터", 2, 150000)
        );
        Customer customer2 = new Customer("김철수", "kim@example.com");
        Order validOrder = new Order(items3, customer2);
        validator.isValid(validOrder);

        System.out.println("======= 테스트 종료 =======");
    }
}
