package testdata;

import models.*;

import java.time.LocalDate;
import java.util.List;

public class StudentBuilder {
    private String firstName = "Имя";
    private String lastName = "Фамилия";
    private String email = "test@test.test";
    private Gender gender = Gender.MALE;
    private String phoneNumber = "9876543210";
    private LocalDate birthdate = LocalDate.of(1990, 8, 12);
    private List<String> addedSubjects = List.of("Maths", "English", "Computer Science", "Arts");
    private List<String> deletedSubjects = List.of("Arts");
    private List<Hobby> hobbies = List.of(Hobby.SPORTS, Hobby.MUSIC);
    private String picturePath = "avatar.png";
    private String address =
                    """
                    123456,
                    г. Москва,
                    ул. Пушкина,
                    д. 5,
                    кв. 109
                    """;
    private State state = State.RAJASTHAN;
    private City city = City.JAISELMER;

    public StudentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public StudentBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public StudentBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public StudentBuilder withBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public StudentBuilder withAddedSubjects(List<String> addedSubjects) {
        this.addedSubjects = addedSubjects;
        return this;
    }

    public StudentBuilder withDeletedSubjects(List<String> deletedSubjects) {
        this.deletedSubjects = deletedSubjects;
        return this;
    }

    public StudentBuilder withHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public static StudentBuilder defaultStudent() {
        return new StudentBuilder();
    }

    public StudentBuilder withPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    public StudentBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public StudentBuilder withState(State state) {
        this.state = state;
        return this;
    }

    public StudentBuilder withCity(City city) {
        this.city = city;
        return this;
    }

    public Student build() {
        return new Student(
                firstName,
                lastName,
                email,
                gender,
                phoneNumber,
                birthdate,
                addedSubjects,
                deletedSubjects,
                hobbies,
                picturePath,
                address,
                state,
                city
        );
    }
}
