package christmas.ui;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputViewTest extends NsTest {

    @DisplayName("날짜에 숫자 이외의 값을 입력하면 예외 처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"12일", "12th", "friday", "!@#"})
    void readDateWithInvaildInput(String invalidInput) {
        InputView inputView = new InputView();

        assertSimpleTest(() -> {
            run(invalidInput);
            assertThatThrownBy(() -> inputView.readDate())
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("주문은 한글,숫자,쉼표(,),대시(-) 이외의 값을 입력받을 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"t-boneSteak", "티본steak-1", "티본스테이크-1, 양송이수프-5"})
    void readOrdersWithInvalidInputFormat(String invalidInput) {
        InputView inputView = new InputView();

        assertSimpleTest(() -> {
            run(invalidInput);
            assertThatThrownBy(() -> inputView.readOrders())
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("각각의 주문은 한글-대시(-)-숫자로만 구성되어야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"tboneSteak-3", "티본steak-1개", "티본스테이크-one", "티본스테이크!-4"})
    void readOrdersWithInvalidInputOrderFormat(String invalidInput) {
        InputView inputView = new InputView();

        assertSimpleTest(() -> {
            run(invalidInput);
            assertThatThrownBy(() -> inputView.readOrders())
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @DisplayName("중복된 메뉴를 주문할 수 없다")
    @Test
    void readOrdersWithDuplicateInput() {
        InputView inputView = new InputView();

        assertSimpleTest(() -> {
            run("티본스테이크-3,티본스테이크-4");
            assertThatThrownBy(() -> inputView.readOrders())
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}