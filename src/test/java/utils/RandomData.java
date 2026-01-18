package utils;

import models.City;
import models.Gender;
import models.Hobby;
import models.Subject;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomData {
    private static final Faker faker = new Faker();
    private static final int LENGTH_OF_10_DIGITS = 10;
    private static final int MIN_ADDED_SUBJECTS = 1;
    private static final int MAX_ADDED_SUBJECTS = Subject.values().length + 1;
    private static final int MIN_ADDED_HOBBIES = 1;
    private static final int MAX_ADDED_HOBBIES = Hobby.values().length + 1;
    private static final String ADDRESS_TEMPLATE =
            """
            %s,
            %s,
            %s,
            %s,
            %s
            """;

    public String randomFirstName() {
        return faker.name().firstName();
    }

    public String randomLastName() {
        return faker.name().lastName();
    }

    public String randomEmail() {
        return faker.internet().emailAddress();
    }

    public Gender randomGender() {
        return faker.options().option(Gender.class);
    }

    public String random10DigitsPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(LENGTH_OF_10_DIGITS);
    }

    public LocalDate randomBirthdate() {
        return faker.timeAndDate().birthday();
    }

    public List<Subject> randomAddedSubjects() {
        var allSubjects = new ArrayList<>(Arrays.asList(Subject.values()));
        Collections.shuffle(allSubjects);
        var toIndex = faker.number().numberBetween(MIN_ADDED_SUBJECTS, MAX_ADDED_SUBJECTS);
        return Collections.unmodifiableList(allSubjects.subList(0, toIndex));
    }

    public List<Hobby> randomHobbies() {
        var allHobbies = new ArrayList<>(Arrays.asList(Hobby.values()));
        var toIndex = faker.number().numberBetween(MIN_ADDED_HOBBIES, MAX_ADDED_HOBBIES);
        return Collections.unmodifiableList(allHobbies.subList(0, toIndex));
    }

    public String randomAddress() {
        var secondaryAddress = faker.address().secondaryAddress();
        var streetAddress = faker.address().streetAddress();
        var city = faker.address().city();
        var country = faker.address().country();
        var postcode = faker.address().postcode();
        return ADDRESS_TEMPLATE.formatted(secondaryAddress, streetAddress, city, country, postcode);
    }

    public City randomCity() {
        return faker.options().option(City.class);
    }
}
