package utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PageUtils {
    private PageUtils() {
    }

    public static void muteAds() {
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    public static void checkCssValue(String selector, String css, String value) {
        $(selector).shouldHave(cssValue(css, value));
    }

    public static void checkTextValue(String selector, String value) {
        $(selector).shouldHave(text(value));
    }

    public static void checkAllElementsCssValue(String selector, String css, String value) {
        $$(selector)
                .forEach(element -> element.shouldHave(cssValue(css, value)));
    }
}
