package selenide.hw8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.PageUtils;

import java.util.List;

class AnnotationStepsTest {
    private static final String BASE_URL = "https://github.com/Singvard/";
    private static final String SEARCH_TEXT = "hw_3";
    private static final String USER_REPO_NAME = "Singvard/qa_guru_java_hw_3";
    private static final List<String> ISSUES = List.of("Coacervate Liqiud", "Next Phase");

    @Test
    void testIssueSearch() {
        openPage(BASE_URL);
        clickSearch();
        inputSearchText(SEARCH_TEXT);
        pressEnter();
        clickOnRepoName(USER_REPO_NAME);
        goToIssueTab();
        verifyNoResultsInvisibility();
        verifyIssieList(ISSUES);
    }

    @Step("Открыть стартовую страницу: {url}")
    void openPage(String url) {
        Selenide.open(url);
    }

    @Step("Кликнуть кнопку поиска.")
    void clickSearch() {
        Selenide.$(".header-search-button")
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Ввести поисковый текст: {searchText}")
    void inputSearchText(String searchText) {
        Selenide.$("#query-builder-test")
                .shouldBe(Condition.visible)
                .sendKeys(searchText);
    }

    @Step("Нажать Enter в поле поиска.")
    void pressEnter() {
        Selenide.$("#query-builder-test").submit();
    }

    @Step("Кликнуть на репозитории с названием: {repoName}")
    void clickOnRepoName(String repoName) {
        Selenide.$(By.linkText(repoName))
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Перейти на вкладку `Issues`")
    void goToIssueTab() {
        Selenide.$("#issues-tab")
                .shouldBe(Condition.visible)
                .click();
    }

    @Step("Проверить, что текст 'No results' не отображается.")
    void verifyNoResultsInvisibility() {
        var isIssuesExists = Selenide.$(".blankslate-heading")
                .isDisplayed();
        Assertions.assertThat(isIssuesExists)
                .as("Текст 'No results' не должен отображаться!")
                .isFalse();
    }

    @Step("Проверить, что названия задач состоят из {expected}.")
    void verifyIssieList(List<String> expected) {
        var issueNames = Selenide.$$(PageUtils.dataTestId("issue-pr-title-link"))
                .shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0))
                .texts();
        Assertions.assertThat(issueNames)
                .as("Названия задач должны соответствовать ожидаемому списку!")
                .containsExactlyInAnyOrderElementsOf(expected);
    }
}