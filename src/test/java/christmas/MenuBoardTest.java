package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}