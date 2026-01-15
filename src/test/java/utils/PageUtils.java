package utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;

public class PageUtils {
    private PageUtils() {
    }

    public static void muteAds() {
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    public static void checkCssValue(SelenideElement element, String css, String value) {
        element.shouldHave(cssValue(css, value));
    }

    public static void checkTextValue(SelenideElement element, String value) {
        element.shouldHave(text(value));
    }

    public static void checkTextValue(SelenideElement outerElement, String innerSelector, String value) {
        outerElement.$(innerSelector).shouldHave(text(value));
    }

    public static void checkAllElementsCssValue(ElementsCollection elements, String css, String value) {
        elements.forEach(element -> element.shouldHave(cssValue(css, value)));
    }
}
