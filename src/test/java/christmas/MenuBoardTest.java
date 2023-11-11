package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuBoardTest {
    MenuBoard menuBoard = MenuBoard.loadMenu();

    @DisplayName("메뉴판에 존재하는 메뉴면 true를 반환한다.")
    @Test
    void hasMenu() {
        assertThat(menuBoard.hasMenu("양송이수프")).isTrue();
    }
}