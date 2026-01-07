package utils;

import com.codeborne.selenide.Selenide;

public class PageUtils {
    private PageUtils() {
    }

    public static void muteAds() {
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }
}
