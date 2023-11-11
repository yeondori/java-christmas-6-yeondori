package christmas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {
    @Test
    public void 메뉴에_있으면_true를_반환한다() {
        Menu menu = new Menu();

        assertThat(menu.hasMenu("양송이수프")).isTrue();
    }

    @Test
    public void 메뉴에_없으면_false를_반환한다() {
        Menu menu = new Menu();

        assertThat(menu.hasMenu("알리오올리오")).isFalse();
    }

}