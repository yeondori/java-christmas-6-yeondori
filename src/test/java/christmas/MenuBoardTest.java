package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuBoardTest {
    MenuBoard menuBoard = MenuBoard.loadMenu();

    @DisplayName("메뉴판에 존재하는 메뉴면 true를 반환한다.")
    @Test
    void hasMenuTrueCase() {
        assertThat(menuBoard.hasMenu("양송이수프")).isTrue();
    }

    @DisplayName("메뉴판에 존재하지 않는 메뉴면 false를 반환한다.")
    @Test
    void hasMenuFalseCase() {
        assertThat(menuBoard.hasMenu("뿌링클")).isFalse();
    }

    @DisplayName("메뉴판에 존재하면 메뉴를 반환한다.")
    @Test
    void findMenuIfPresent() {
        assertThat(menuBoard.findMenu("양송이수프")).isInstanceOf(Menu.class);
    }

    @DisplayName("메뉴판에 존재하지 않으면 예외를 발생한다.")
    @Test
    void findMenuIfNotPresent() {
        assertThatThrownBy(() -> menuBoard.findMenu("뿌링클"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 존재하면 카테고리를 반환한다.")
    @Test
    void findCategoryIfPresent() {
        assertThat(menuBoard.findCategory("양송이수프")).isEqualTo(Category.APPETIZER);
    }
}