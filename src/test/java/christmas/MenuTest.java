package christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @DisplayName("메뉴에 존재하는 음식이면 true를 반환한다")
    @Test
    public void hasMenuTrueCase() {
        Menu menu = new Menu();

        assertThat(menu.hasMenu("양송이수프")).isTrue();
    }

    @DisplayName("메뉴에 존재하지 않는 음식이면 false를 반환한다")
    @Test
    public void hasMenuFalseCase() {
        Menu menu = new Menu();

        assertThat(menu.hasMenu("알리오올리오")).isFalse();
    }

    @DisplayName("메뉴와 이름이 같은 음식을 반환한다")
    @Test
    public void findMenu() {
        Menu menu = new Menu();

        assertThat(menu.findMenu("양송이수프")).isInstanceOf(Food.class);
    }

    @DisplayName("메뉴의 카테고리를 반환한다")
    @Test
    public void findCategory() {
        Menu menu = new Menu();

        assertThat(menu.findCategory("티본스테이크")).isEqualTo(Category.MAIN);
    }
}