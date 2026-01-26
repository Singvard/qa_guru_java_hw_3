package selenide.hw8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.PageUtils;

import java.util.List;

class LambdaStepsTest {
    private static final String BASE_URL = "https://github.com/Singvard/";
    private static final String SEARCH_TEXT = "hw_3";
    private static final String USER_REPO_NAME = "Singvard/qa_guru_java_hw_3";
    private static final List<String> ISSUES = List.of("Coacervate Liqiud", "Next Phase");

    @Test
    void testIssueSearch() {
        Allure.step("Открыть стартовую страницу: " + BASE_URL,
                () -> Selenide.open(BASE_URL));

        Allure.step("Кликнуть кнопку поиска.",
                () -> Selenide.$(".header-search-button")
                        .shouldBe(Condition.visible)
                        .click());

        Allure.step("Ввести поисковый текст: " + SEARCH_TEXT,
                () -> Selenide.$("#query-builder-test")
                        .shouldBe(Condition.visible)
                        .sendKeys(SEARCH_TEXT));

        Allure.step("Нажать Enter в поле поиска.",
                () -> Selenide.$("#query-builder-test")
                        .submit());

        Allure.step("Кликнуть на репозитории с названием: " + USER_REPO_NAME,
                () -> Selenide.$(By.linkText(USER_REPO_NAME))
                        .shouldBe(Condition.visible)
                        .click());

        Allure.step("Перейти на вкладку `Issues`",
                () -> Selenide.$("#issues-tab")
                        .shouldBe(Condition.visible)
                        .click());

        Allure.step("Проверить, что текст 'No results' не отображается.", () -> {
            var isIssuesExists = Selenide.$(".blankslate-heading")
                    .isDisplayed();
            Assertions.assertThat(isIssuesExists)
                    .as("Текст 'No results' не должен отображаться!")
                    .isFalse();
        });

        Allure.step("Проверить, что названия задач состоят из `Coacervate Liqiud` и `Next Phase`.", () -> {
            var issueNames = Selenide.$$(PageUtils.dataTestId("issue-pr-title-link"))
                    .shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0))
                    .texts();
            Assertions.assertThat(issueNames)
                    .as("Названия задач должны соответствовать ожидаемому списку!")
                    .containsExactlyInAnyOrderElementsOf(ISSUES);
        });
    }
}
