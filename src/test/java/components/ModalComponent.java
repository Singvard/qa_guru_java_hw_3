package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ModalComponent {
    private static final String CSS_MODAL_SHOW = ".modal.show";
    private static final String CSS_MODAL_TITLE = ".modal-title";
    private static final String TITLE = "Thanks for submitting the form";
    private static final String CSS_COLUMN_TITLE = ".modal-body table thead th";
    private static final String LABEL = "Label";
    private static final String VALUES = "Values";
    private static final String XPATH_ROW_VALUES_TEMPLATE = "//td[text()='%s']/following-sibling::td";

    private static final SelenideElement MODAL_SHOW = $(CSS_MODAL_SHOW);
    private static final SelenideElement MODAL_TITLE = $(CSS_MODAL_TITLE);
    private static final ElementsCollection COLUMN_TITLE = $$(CSS_COLUMN_TITLE);

    public ModalComponent checkVisibility() {
        MODAL_SHOW.shouldBe(visible);
        return this;
    }

    public void checkInvisibility() {
        MODAL_SHOW.shouldNotBe(visible);
    }

    public ModalComponent checkTitle() {
        MODAL_TITLE.shouldHave(text(TITLE));
        return this;
    }

    public ModalComponent checkColumnTitles() {
       COLUMN_TITLE.shouldHave(texts(LABEL, VALUES));
        return this;
    }

    public ModalComponent checkCellValue(String rowLabel, String value) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldHave(text(value));
        return this;
    }

    public ModalComponent checkEmptyCell(String rowLabel) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldBe(empty);
        return this;
    }
}
