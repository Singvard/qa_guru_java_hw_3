package models;

import utils.DateFormatter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record Student(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        String phoneNumber,
        LocalDate birthdate,
        List<Subject> addedSubjects,
        List<Hobby> hobbies,
        String picturePath,
        String address,
        State state,
        City city
) {

    public Student(String firstName, String lastName, String email, String address) {
        this(
                firstName,
                lastName,
                email,
                null,
                null,
                null,
                null,
                null,
                null,
                address,
                null,
                null
        );
    }

    public String studentFullName() {
        return firstName + " " + lastName;
    }

    public String studentDateOfBirth() {
        return DateFormatter.formateDateToString(birthdate);
    }

    public String studentSubjects() {
        return addedSubjects.stream()
                .map(Subject::toString)
                .collect(Collectors.joining(", "));
    }

    public String studentHobbies() {
        return hobbies.stream()
                .map(Hobby::toString)
                .collect(Collectors.joining(", "));
    }

    public String studentFlatAddress() {
        return address.lines()
                .map(String::strip)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String studentFullLocation() {
        return state + " " + city;
    }
}
