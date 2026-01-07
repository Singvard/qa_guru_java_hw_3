package components;

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

    /**
     * Проверить отображение модального окна.
     *
     * @return Текущий объект ModalComponent для цепочки вызовов
     */
    public ModalComponent checkVisibility() {
        $(CSS_MODAL_SHOW).shouldBe(visible);
        return this;
    }

    /**
     * Проверить скрытость модального окна.
     */
    public void checkInvisibility() {
        $(CSS_MODAL_SHOW).shouldNotBe(visible);
    }

    /**
     * Проверить заголовок модального окна.
     *
     * @return Текущий объект ModalComponent для цепочки вызовов
     */
    public ModalComponent checkTitle() {
        $(CSS_MODAL_TITLE).shouldHave(text(TITLE));
        return this;
    }

    /**
     * Проверить шапку таблицы (названия столбцов).
     *
     * @return Текущий объект ModalComponent для цепочки вызовов
     */
    public ModalComponent checkColumnTitles() {
        $$(CSS_COLUMN_TITLE).shouldHave(texts(LABEL, VALUES));
        return this;
    }

    /**
     * Проверить ячейки строки (метка и значение).
     *
     * @param rowLabel Метка строки
     * @param value    Значение
     * @return Текущий объект ModalComponent для цепочки вызовов
     */
    public ModalComponent checkCellValue(String rowLabel, String value) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldHave(text(value));
        return this;
    }

    /**
     * Проверить, что ячейка строки пуста.
     *
     * @param rowLabel Метка строки
     * @return Текущий объект ModalComponent для цепочки вызовов
     */
    public ModalComponent checkEmptyCell(String rowLabel) {
        $x(XPATH_ROW_VALUES_TEMPLATE.formatted(rowLabel))
                .shouldBe(empty);
        return this;
    }
}
