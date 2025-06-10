package cleancode.studycafe;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.order.StudyCafePassOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StudyCafeUnitTest {

    @Test
    @DisplayName("시간권은 사물함 이용이 불가하다")
    void hourlyPass_cannotUseLocker() {
        // given
        StudyCafeSeatPass hourlyPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 3, 12000, 0.0);

        // when
        boolean result = hourlyPass.cannotUseLocker();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("고정석은 사물함 이용이 가능하다")
    void fixedPass_canUseLocker() {
        // given
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 80000, 0.1);

        // when
        boolean result = fixedPass.cannotUseLocker();

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이용권과 동일한 타입/기간의 사물함을 찾을 수 있다")
    void findLockerPassBy_sameTypeAndDuration() {
        // given
        StudyCafeSeatPass fixedPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 80000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        boolean result = fixedPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이용권과 다른 타입의 사물함은 찾을 수 없다")
    void findLockerPassBy_differentType() {
        // given
        StudyCafeSeatPass weeklyPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 30000, 0.1);
        StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 9000);

        // when
        boolean result = weeklyPass.isSameDurationType(lockerPass);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("이벤트 할인 금액이 정상적으로 계산된다")
    void getDiscountPrice_shouldCalculateCorrectly() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 60000, 0.1);

        // when
        int discount = pass.getDiscountPrice();

        // then
        assertThat(discount).isEqualTo(6000); // 10% 할인
    }

    @Test
    @DisplayName("사물함 포함 결제 시 총 결제 금액이 올바르게 계산된다")
    void orderWithLocker_totalPrice() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 80000, 0.1);
        StudyCafeLockerPass locker = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, locker);
        int totalPrice = order.getTotalPrice();

        // then
        // (80000 + 10000) - 8000 = 82000
        assertThat(totalPrice).isEqualTo(82000);
    }

    @Test
    @DisplayName("사물함 미포함 결제 시 총 결제 금액이 올바르게 계산된다")
    void orderWithoutLocker_totalPrice() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 30000, 0.0);

        // when
        StudyCafePassOrder order = StudyCafePassOrder.of(pass, null);
        int totalPrice = order.getTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(30000);
    }

    @Test
    @DisplayName("StudyCafePassType의 isLockerType, isNotLockerType 동작 확인")
    void passType_lockerTypeCheck() {
        // given
        StudyCafePassType fixed = StudyCafePassType.FIXED;
        StudyCafePassType hourly = StudyCafePassType.HOURLY;

        // when & then
        assertThat(fixed.isLockerType()).isTrue();
        assertThat(hourly.isLockerType()).isFalse();
        assertThat(fixed.isNotLockerType()).isFalse();
        assertThat(hourly.isNotLockerType()).isTrue();
    }
}
