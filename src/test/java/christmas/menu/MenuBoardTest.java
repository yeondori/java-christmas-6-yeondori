package christmas.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuBoardTest {
    MenuBoard menuBoard = MenuBoard.loadMenu();
    private static final String 있는_메뉴 = "양송이수프";
    private static final String 없는_메뉴 = "뿌링클";

    @DisplayName("메뉴판에 존재하는 메뉴면 true를 반환한다.")
    @Test
    void hasMenuTrueCase() {
        assertThat(menuBoard.hasMenu(있는_메뉴)).isTrue();
    }

    @DisplayName("메뉴판에 존재하지 않는 메뉴면 false를 반환한다.")
    @Test
    void hasMenuFalseCase() {
        assertThat(menuBoard.hasMenu(없는_메뉴)).isFalse();
    }

    @DisplayName("메뉴판에 존재하면 카테고리를 반환한다.")
    @Test
    void findCategoryIfPresent() {
        assertThat(menuBoard.findCategory(있는_메뉴)).isEqualTo(Category.APPETIZER);
    }

    @DisplayName("메뉴판에 존재하지 않으면 예외를 발생한다.")
    @Test
    void findCategoryIfNotPresent() {
        assertThatThrownBy(() -> menuBoard.findCategory(없는_메뉴))
                .isInstanceOf(IllegalArgumentException.class);
    }
}