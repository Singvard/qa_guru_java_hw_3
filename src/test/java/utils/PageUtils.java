package utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Утилитарный класс для работы с веб-страницами в тестах.
 * Предоставляет методы для выполнения общих операций с элементами страницы.
 *
 * <p>Класс содержит методы для:
 * <ul>
 *   <li>Удаления рекламных баннеров</li>
 *   <li>Проверки CSS-свойств элементов</li>
 *   <li>Проверки текстового содержимого элементов</li>
 * </ul>
 *
 * <p>Класс имеет приватный конструктор, так как предназначен только для
 * статического использования и не должен инстанциироваться.
 *
 * <p>Пример использования:
 * <pre>
 * // Убрать рекламу
 * PageUtils.muteAds();
 *
 * // Проверить CSS-свойство
 * PageUtils.checkCssValue(".button", "background-color", "#ff0000");
 *
 * // Проверить текст
 * PageUtils.checkTextValue(".header", "Welcome");
 * </pre>
 *
 */
public class PageUtils {
    private PageUtils() {
    }

    /**
     * Удаляет рекламные баннеры со страницы с помощью JavaScript.
     * Удаляет элементы с id="fixedban" и все footer элементы.
     *
     * <p>Метод полезен для устранения мешающих элементов во время тестирования.
     */
    public static void muteAds() {
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    /**
     * Проверяет CSS-свойство элемента по селектору.
     *
     * @param selector CSS-селектор элемента
     * @param css имя CSS-свойства для проверки
     * @param value ожидаемое значение CSS-свойства
     * @throws com.codeborne.selenide.ex.ElementNotFound если элемент не найден
     */
    public static void checkCssValue(String selector, String css, String value) {
        $(selector).shouldHave(cssValue(css, value));
    }

    /**
     * Проверяет текстовое содержимое элемента по селектору.
     *
     * @param selector CSS-селектор элемента
     * @param value ожидаемый текст
     * @throws com.codeborne.selenide.ex.ElementNotFound если элемент не найден
     */
    public static void checkTextValue(String selector, String value) {
        $(selector).shouldHave(text(value));
    }

    /**
     * Проверяет текстовое содержимое вложенного элемента.
     *
     * @param outerSelector CSS-селектор внешнего элемента
     * @param innerSelector CSS-селектор внутреннего элемента (относительно внешнего)
     * @param value ожидаемый текст
     * @throws com.codeborne.selenide.ex.ElementNotFound если элемент не найден
     */
    public static void checkTextValue(String outerSelector, String innerSelector, String value) {
        $(outerSelector).$(innerSelector).shouldHave(text(value));
    }

    /**
     * Проверяет CSS-свойство для всех элементов, соответствующих селектору.
     *
     * @param selector CSS-селектор элементов
     * @param css имя CSS-свойства для проверки
     * @param value ожидаемое значение CSS-свойства
     */
    public static void checkAllElementsCssValue(String selector, String css, String value) {
        $$(selector)
                .forEach(element -> element.shouldHave(cssValue(css, value)));
    }
}
