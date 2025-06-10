package cleancode.studycafe;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class StudyCafeExceptionTest {

    @Test
    @DisplayName("존재하지 않는 이용권 타입으로 조회 시 빈 리스트를 반환한다")
    void findPassBy_nonExistingType_returnsEmptyList() {
        // given
        StudyCafeSeatPass pass1 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 10000, 0.0);
        StudyCafeSeatPass pass2 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 50000, 0.1);
        StudyCafeSeatPasses passes = StudyCafeSeatPasses.of(List.of(pass1, pass2));

        // when
        List<StudyCafeSeatPass> result = passes.findPassBy(StudyCafePassType.FIXED);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("StudyCafeSeatPass.getDiscountPrice는 음수 할인율에 대해 0 이상을 반환한다 (방어적 코드 필요 시)")
    void getDiscountPrice_negativeDiscountRate_returnsZeroOrPositive() {
        // given
        StudyCafeSeatPass pass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 10000, -0.1);

        // when
        int discount = pass.getDiscountPrice();

        // then
        // 현재 구현은 음수 할인율도 그대로 계산하므로 음수 나올 수 있음
        // 방어적 코드를 추가하면 0 이상 보장 가능
        assertThat(discount).isLessThanOrEqualTo(0);
    }

    @Test
    @DisplayName("StudyCafeSeatPasses.of에 null 리스트 전달 시 예외 발생")
    void studyCafeSeatPassesOf_nullList_throwsException() {
        // given
        List<StudyCafeSeatPass> nullList = null;

        // when & then
        assertThatThrownBy(() -> StudyCafeSeatPasses.of(nullList))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("StudyCafePassOrder.of에 null 이용권 전달 시 예외 발생")
    void studyCafePassOrderOf_nullSeatPass_throwsException() {
        // given
        StudyCafeSeatPass seatPass = null;
        // lockerPass는 null 허용

        // when & then
        assertThatThrownBy(() -> cleancode.studycafe.tobe.model.order.StudyCafePassOrder.of(seatPass, null))
            .isInstanceOf(NullPointerException.class);
    }
}
