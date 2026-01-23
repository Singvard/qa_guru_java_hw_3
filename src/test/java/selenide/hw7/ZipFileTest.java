package selenide.hw7;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.FileProcessor;

import java.io.InputStreamReader;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class ZipFileTest {
    private static final String ZIP_PATH = "archive.zip";
    private static final String CSV_FILENAME = "username.csv";
    private static final String PDF_FILENAME = "dummy_1.pdf";
    private static final String XLSX_FILENAME = "file_example_XLSX_50.xlsx";
    private static final String EXPECTED_PDF_TEXT = "Dummy PDF file";
    private static final int ZERO = 0;
    private static final int EXPECTED_NUMBER_OF_XLSX_ROWS = 51;
    private static final int XLSX_NUMBER_OF_COLUMNS = 8;
    private static final int XLSX_LAST_ROW_NUMBER = 50;
    private static final String[] EXPECTED_XLSX_HEADER_ROW_DATA = {
            "0", "First Name", "Last Name", "Gender", "Country", "Age", "Date", "Id"};
    private static final String[] EXPECTED_XLSX_LAST_DATA_ROW = {
            "50", "Rasheeda", "Alkire", "Female", "United States", "29", "16/08/2016", "6125"};
    private static final int EXPECTED_NUMBER_OF_CSV_ROWS = 6;
    private static final String[] EXPECTED_CSV_HEADER = {"Username", "Identifier", "First name", "Last name"};
    private static final String[] EXPECTED_CSV_LAST_ROW = {"smith79", "5079", "Jamie", "Smith"};

    private final ClassLoader loader = getClass().getClassLoader();

    @Test
    void testParsingPdfFileIntoZipArchive() throws Exception {
        processZippedFile(PDF_FILENAME, fileStream -> {
            var pdf = new PDF(fileStream);
            var actualPdfText = pdf.text;
            Assertions.assertThat(actualPdfText)
                    .as("Фактический текст PDF-файла должен соответствовать ожидаемому!")
                    .isEqualToIgnoringNewLines(EXPECTED_PDF_TEXT);
        });
    }

    @Test
    void testParsingXlsxFileIntoZipArchive() throws Exception {
        processZippedFile(XLSX_FILENAME, fileStream -> {
            var xls = new XLS(fileStream);
            var sheet = xls.excel
                    .getSheetAt(ZERO);

            var actualRowCount = sheet.getPhysicalNumberOfRows();
            Assertions.assertThat(actualRowCount)
                    .as("Фактическое кол-во строк в XLSX-файле должно совпадать с ожидаемым!")
                    .isEqualTo(EXPECTED_NUMBER_OF_XLSX_ROWS);

            var firstRow = sheet.getRow(ZERO);
            var firstRowData = extractRowValues(firstRow, XLSX_NUMBER_OF_COLUMNS);
            Assertions.assertThat(firstRowData)
                    .as("Фактическое значение первой строки должно совпадать с ожидаемым!")
                    .isEqualTo(EXPECTED_XLSX_HEADER_ROW_DATA);

            var lastRow = sheet.getRow(XLSX_LAST_ROW_NUMBER);
            var lastRowData = extractRowValues(lastRow, XLSX_NUMBER_OF_COLUMNS);
            Assertions.assertThat(lastRowData)
                    .as("Фактическое значение последней строки должно совпадать с ожидаемым!")
                    .isEqualTo(EXPECTED_XLSX_LAST_DATA_ROW);
        });
    }

    @Test
    void testParsingCsvFileIntoZipArchive() throws Exception {
        processZippedFile(CSV_FILENAME, fileStream -> {
            var csvReader = new CSVReader(new InputStreamReader(fileStream));
            var data = csvReader.readAll();

            Assertions.assertThat(data)
                    .as("Фактическое кол-во строк в CSV-файле должно совпадать с ожидаемым!")
                    .hasSize(EXPECTED_NUMBER_OF_CSV_ROWS);

            Assertions.assertThat(data.getFirst())
                    .as("Фактическое значение первой строки должно совпадать с ожидаемым!")
                    .isEqualTo(EXPECTED_CSV_HEADER);

            Assertions.assertThat(data.getLast())
                    .as("Фактическое значение последней строки должно совпадать с ожидаемым!")
                    .isEqualTo(EXPECTED_CSV_LAST_ROW);
        });
    }

    private void processZippedFile(String fileName, FileProcessor processor) throws Exception {
        try (var inputStream = loader.getResourceAsStream(ZIP_PATH)) {
            Assertions.assertThat(inputStream)
                    .as("Файл архива '%s' не найден в ресурсах!", ZIP_PATH)
                    .isNotNull();
            try (var zip = new ZipInputStream(inputStream)) {
                ZipEntry entry;
                var found = false;

                while (Objects.nonNull(entry = zip.getNextEntry())) {
                    if (entry.getName().equals(fileName)) {
                        found = true;
                        processor.process(zip);
                        break;
                    }
                }

                Assertions.assertThat(found)
                        .as("Файл '%s' не найден в архиве!", fileName)
                        .isTrue();
            }
        }
    }

    private String[] extractRowValues(Row row, int columnCount) {
        var values = new String[columnCount];
        var formatter = new DataFormatter();
        for (int i = 0; i < columnCount; i++) {
            var cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            values[i] = formatter.formatCellValue(cell);
        }
        return values;
    }
}
