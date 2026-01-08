package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

/**
 * Абстрактный базовый класс для всех тестов, использующих Selenide.
 * Предоставляет общую конфигурацию для всех тестовых классов.
 *
 * <p>Настраивает параметры браузера и базовый URL перед выполнением всех тестов.
 * Все тестовые классы должны наследоваться от этого класса для использования
 * единой конфигурации.
 *
 * <p>Пример использования:
 * <pre>
 * public class RegistrationTest extends BaseTest {
 *     // тестовые методы
 * }
 * </pre>
 *
 * @see Configuration
 */
public abstract class BaseTest {
    private static final String FULL_HD = "1920x1080";
    private static final String BASE_URL = "https://demoqa.com";

    @BeforeAll
    static void configureSelenide() {
        Configuration.browserSize = FULL_HD;
        Configuration.baseUrl = BASE_URL;
    }
}
