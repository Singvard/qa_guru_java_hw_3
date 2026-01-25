package selenide.hw7;

import models.hw7.MissionList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.json.JsonMapper;

import java.time.LocalDateTime;

class JsonTest {

    private static final String JSON_PATH = "space_missions.json";

    @Test
    void parseSpaceMissionsJson() throws Exception {
        var mapper = JsonMapper.builder().build();

        try (var inputStream = getClass().getClassLoader()
                .getResourceAsStream(JSON_PATH)) {

            Assertions.assertThat(inputStream)
                    .as("Не удалось обнаружить JSON-файл '%s' в ресурсах!", JSON_PATH)
                    .isNotNull();

            var missionList = mapper.readValue(inputStream, MissionList.class);
            var missions = missionList.missions();

            Assertions.assertThat(missions)
                    .as("Актуальное количество миссий должно совпадать с ожидаемым!")
                    .hasSize(2);

            // Проверка Восход-2
            var voskhod2 = missions.stream()
                    .filter(m -> "Voskhod-2".equals(m.missionName()))
                    .findFirst()
                    .orElseThrow();

            var leonov = voskhod2.crewMembers().getFirst();

            Assertions.assertThat(voskhod2.country())
                    .as("Актуальная страна для миссии Восход-2 должна совпадать с ожидаемой!")
                    .isEqualTo("Soviet Union");

            Assertions.assertThat(voskhod2.launchDateTime())
                    .as("Актуальная дата для миссии Восход-2 должна совпадать с ожидаемой!")
                    .isEqualTo(LocalDateTime.of(1965, 3, 18, 7, 0, 0));

            Assertions.assertThat(voskhod2.evaPerformed())
                    .as("Целью Восход-2 был выход в открытый космос!")
                    .isTrue();

            Assertions.assertThat(voskhod2.missionObjective())
                    .as("Целью Восход-2 был выход в открытый космос!")
                    .contains("First spacewalk");

            Assertions.assertThat(voskhod2.crewMembers())
                    .as("Актуальный размер экипажа Восход-2 не совпадает с ожидаемым!")
                    .hasSize(2);


            Assertions.assertThat(leonov.name())
                    .as("Имя и фамилия первого члена экипажа не совпадают с ожидаемыми!")
                    .isEqualTo("Alexei Leonov");

            Assertions.assertThat(leonov.evaMinutes())
                    .as("Время, проведённое в открытом космосе, не соответствует ожидаемому!")
                    .isEqualTo(12);

            Assertions.assertThat(leonov.specialNotes())
                    .as("Достижения Леонова не содержат ожидаемую строку!")
                    .contains("First human to perform extravehicular activity");

            // Проверка Gemini 4
            var gemini4 = missions.stream()
                    .filter(m -> "Gemini 4".equals(m.missionName()))
                    .findFirst()
                    .orElseThrow();

            var edwardWhite = gemini4.crewMembers().stream()
                    .filter(a -> "Edward White".equals(a.name()))
                    .findFirst()
                    .orElseThrow();

            Assertions.assertThat(gemini4.durationMinutes())
                    .as("Время полёта Гемини 4 должно соответствовать ожидаемому!")
                    .isEqualTo(5776); // ~4 days

            Assertions.assertThat(gemini4.orbitAltitudeKm())
                    .as("Высота орбиты Гемини 4 должно соответствовать ожидаемому!")
                    .isEqualTo(296);

            Assertions.assertThat(edwardWhite.evaMinutes())
                    .as("Время, проведённое в открытом космосе, не соответствует ожидаемому!")
                    .isEqualTo(23);

            Assertions.assertThat(edwardWhite.specialNotes())
                    .as("Достижения Уайта не содержат ожидаемую строку!")
                    .contains("First American to perform EVA");

            // Сравнение миссий
            Assertions.assertThat(voskhod2.launchDateTime())
                    .as("Восход-2 был запущен раньше Гемини 4!")
                    .isBefore(gemini4.launchDateTime());

            Assertions.assertThat(leonov.evaMinutes())
                    .as("Леонов пробыл в космосе меньше, чем Уайт!")
                    .isLessThan(edwardWhite.evaMinutes());
        }
    }
}
