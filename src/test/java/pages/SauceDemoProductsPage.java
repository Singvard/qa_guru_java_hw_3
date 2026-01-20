package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import utils.PageUtils;
import utils.SessionManager;

public class SauceDemoProductsPage {
    private static final String ADDRESS = "/inventory.html";
    private static final SelenideElement TITLE = Selenide.$(PageUtils.testId("title"));
    private static final SelenideElement CART = Selenide.$(PageUtils.testId("shopping-cart-link"));
    private static final SelenideElement INVENTORY_CONTAINER = Selenide.$(PageUtils.testId("inventory-container"));
    private static final ElementsCollection ITEM_NAME = Selenide.$$(PageUtils.testId("inventory-item-name"));

    private final SessionManager sessionManager = SessionManager.getInstance();

    public String getTitle() {
        return TITLE.getText();
    }

    public boolean isCartVisible() {
        return CART.isDisplayed();
    }

    public boolean isInventoryVisible() {
        return INVENTORY_CONTAINER.isDisplayed();
    }

    public SauceDemoProductsPage openWithUser(String username, String password) {
        sessionManager.openPageWithSession(sessionManager.getOrCreateSession(username, password), ADDRESS);
        return this;
    }

    public SauceDemoProductsPage addItemToToCart(String item) {
        ITEM_NAME.findBy(Condition.text(item));
    }
}
