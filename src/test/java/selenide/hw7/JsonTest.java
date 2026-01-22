package selenide.hw7;

import models.hw7.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

class JsonTest {

    private static final String JSON_PATH = "library.json";

    @Test
    void parseRussianLibraryJson() throws Exception {
        var mapper = new ObjectMapper();
        mapper.registeredModules();

        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(JSON_PATH)) {

            Assertions.assertThat(inputStream)
                    .as("JSON file '%s' not found in resources", JSON_PATH)
                    .isNotNull();

            // Deserialization
            Library library = mapper.readValue(inputStream, Library.class);

            // Assertions about Russian library
            Assertions.assertThat(library.name())
                    .as("Название библиотеки")
                    .isEqualTo("Центральная городская библиотека им. А.С. Пушкина");

            Assertions.assertThat(library.address())
                    .as("Library address")
                    .contains("ул. Читательская, д. 15, г. Москва, 101000");

            Assertions.assertThat(library.establishedYear())
                    .as("Established year")
                    .isEqualTo(1925);

            // Check Russian literature books
            Assertions.assertThat(library.books())
                    .as("Number of books")
                    .hasSize(4);

            // Find Russian authors
            long russianAuthorsCount = library.books().stream()
                    .filter(book -> "Русский".equals(book.author().nationality()))
                    .count();

            Assertions.assertThat(russianAuthorsCount)
                    .as("Number of books by Russian authors")
                    .isEqualTo(2);

            // Check specific Russian books
            Book bulgakovBook = library.books().stream()
                    .filter(book -> "Булгаков".equals(book.author().lastName()))
                    .findFirst()
                    .orElseThrow();

            Assertions.assertThat(bulgakovBook.title())
                    .as("Bulgakov book title")
                    .isEqualTo("Мастер и Маргарита");

            Assertions.assertThat(bulgakovBook.genre())
                    .as("Bulgakov book genre")
                    .isEqualTo(Genre.FICTION);

            // Check Tolstoy
            Book tolstoyBook = library.books().stream()
                    .filter(book -> "Преступление и наказание".equals(book.author().lastName()))
                    .findFirst()
                    .orElseThrow();

            Assertions.assertThat(tolstoyBook.pages())
                    .as("War and Peace pages")
                    .isEqualTo(608);

            Assertions.assertThat(tolstoyBook.genre())
                    .as("War and Peace genre")
                    .isEqualTo(Genre.FICTION);

            // Check employees
            Assertions.assertThat(library.employees())
                    .as("Number of employees")
                    .hasSize(3);

            Employee director = library.employees().stream()
                    .filter(e -> e.position() == Position.MANAGER)
                    .findFirst()
                    .orElseThrow();

            Assertions.assertThat(director.fullName())
                    .as("ФИО директора")
                    .isEqualTo("Иванова Анна Петровна");

            // Check opening hours
            Assertions.assertThat(library.openingHours().mondayToFriday())
                    .as("Режим работы по будням")
                    .isEqualTo("09:00 - 21:00");

            // Check Russian holidays
            Assertions.assertThat(library.openingHours().holidays())
                    .as("Праздничные дни")
                    .contains("Victory Day", "International Women's Day");
        }
    }
}
