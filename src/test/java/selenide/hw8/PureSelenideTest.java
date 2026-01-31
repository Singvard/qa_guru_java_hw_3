package selenide.hw8;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.PageUtils;

import java.util.List;

class PureSelenideTest {
    private static final String BASE_URL = "https://github.com/Singvard/";
    private static final String SEARCH_TEXT = "hw_3";
    private static final String USER_REPO_NAME = "Singvard/qa_guru_java_hw_3";
    private static final List<String> ISSUES = List.of("Coacervate Liqiud", "Next Phase");

    @Test
    void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Selenide.open(BASE_URL);

        Selenide.$(".header-search-button").click();
        Selenide.$("#query-builder-test").sendKeys(SEARCH_TEXT);
        Selenide.$("#query-builder-test").submit();

        Selenide.$(By.linkText(USER_REPO_NAME)).click();

        Selenide.$("#issues-tab").click();
        var isIssuesExists = Selenide.$(".blankslate-heading")
                .isDisplayed();
        Assertions.assertThat(isIssuesExists)
                .as("Текст 'No results' не должен отображаться!")
                .isFalse();

        var issueNames = Selenide.$$(PageUtils.dataTestId("issue-pr-title-link"))
                .shouldHave(com.codeborne.selenide.CollectionCondition.sizeGreaterThan(0))
                .texts();
        Assertions.assertThat(issueNames)
                .as("Названия задач должны соотвествовать ожидаемому списку!")
                .containsExactlyInAnyOrderElementsOf(ISSUES);
    }
}
