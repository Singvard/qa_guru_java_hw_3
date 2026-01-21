package selenide.hw6;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import models.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SauceDemoCartPage;
import pages.SauceDemoLoginPage;
import pages.SauceDemoProductsPage;

import java.util.stream.Stream;

class Homework6Test extends BaseTest {
    private static final String STANDARD_USER = "standard_user";
    private static final String PROBLEM_USER = "problem_user";
    private static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    private static final String VALID_PASSWORD = "secret_sauce";
    private static final int ONE = 1;

    @BeforeEach
    void clearCartAndSession() {
        clearCart();
        clearSession();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            STANDARD_USER,
            PROBLEM_USER,
            PERFORMANCE_GLITCH_USER
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
    @CsvFileSource(resources = "/hw6.csv", numLinesToSkip = ONE)
    void testInvalidLoginScenariosFromFile(String username, String password, String expectedErrorMessage) {
        var loginPage = new SauceDemoLoginPage().open()
                .fillUsername(username)
                .fillPassword(password)
                .loginWithError();

        Assertions.assertThat(loginPage.getErrorMessage())
                .as("Сообщение об ошибке должно соответствовать ожидаемому")
                .isEqualTo(expectedErrorMessage);
    }


    @ParameterizedTest
    @MethodSource("provideProductData")
    void testAddingItemToCart(Item item, String expectedPrice) {
        var productsPage = new SauceDemoProductsPage()
                .openWithUser(STANDARD_USER, VALID_PASSWORD)
                .addItemToToCart(item);
        Assertions.assertThat(productsPage.getCartItemCount())
                .as("Количество товаров в корзине должно быть равно 1")
                .isEqualTo(ONE);
        var cartPage = productsPage.goToCart();
        Assertions.assertThat(cartPage.getCartItemPrice(item))
                .as("Цена товара в корзине должна совпадать с ожидаемой ценой")
                .isEqualTo(expectedPrice);
    }

    private void clearCart() {
        new SauceDemoCartPage()
                .openWithUser(STANDARD_USER, VALID_PASSWORD)
                .clearCart();
    }

    private void clearSession() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            Selenide.executeJavaScript("window.localStorage.clear();");
            Selenide.executeJavaScript("window.sessionStorage.clear();");
        }
    }

    private static Stream<Object[]> provideProductData() {
        return Stream.of(
                new Object[]{Item.BACKPACK, "$29.99"},
                new Object[]{Item.BIKE_LIGHT, "$9.99"},
                new Object[]{Item.BOLT_T_SHIRT, "$15.99"}
        );
    }
}
