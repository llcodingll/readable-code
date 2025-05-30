package cleancode.order.tobe;

public class OrderValidator {
    public void isValid(Order order) {
        if (isEmpty(order)) {
            System.out.println("주문 항목이 없습니다.");
            return;
        }

        if (isPriceInvalid(order)) {
            System.out.println("올바르지 않은 총 가격입니다.");
            return;
        }

        if (isMissingCustomerInfo(order)) {
            System.out.println("사용자 정보가 없습니다.");
            return;
        }

        System.out.println("유효성 검사를 통과했습니다.");
    }

    private boolean isEmpty(Order order) {
        return order.getTotalItemCount() == 0;
    }

    private boolean isPriceInvalid(Order order) {
        return order.getTotalPrice() <= 0;
    }

    private boolean isMissingCustomerInfo(Order order) {
        return order.isCustomerInfoEmpty();
    }
}
