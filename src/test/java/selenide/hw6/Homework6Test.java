package selenide.hw6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SauceDemoLoginPage;
import pages.SauceDemoProductsPage;

class Homework6Test extends BaseTest {
    private static final String VALID_PASSWORD = "secret_sauce";

    @ParameterizedTest
    @ValueSource(strings = {
            "standard_user",
            "problem_user",
            "performance_glitch_user"
    })
    void loginWithDifferentUsers(String username) {
        var loginPage = new SauceDemoLoginPage();
        var productsPage = loginPage.open()
                .fillUsername(username)
                .fillPassword(VALID_PASSWORD)
                .login();

        Assertions.assertThat(productsPage.getTitle())
                .as("Фактический заголовок страницы не соответствует ожидаемому.")
                .isEqualTo("Products");

        Assertions.assertThat(productsPage.isCartVisible())
                .as("Корзина покупок должна быть видимой.")
                .isTrue();

        Assertions.assertThat(productsPage.isInventoryVisible())
                .as("Ассортимент должен быть видимым.")
                .isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideProductData")
    void testAddingItemToCart(String item, String expectedPrice) {
        var productsPage = new SauceDemoProductsPage()
                .openWithUser("standard_user", VALID_PASSWORD);
        Assertions.assertThat(productsPage.getTitle())
                .as("Фактический заголовок страницы не соответствует ожидаемому.")
                .isEqualTo("Products");
    }
}
