package models;

public enum Subject {
    ACCOUNTING("Accounting"),
    ARTS("Arts"),
    BIOLOGY("Biology"),
    CHEMISTRY("Chemistry"),
    CIVICS("Civics"),
    COMMERCE("Commerce"),
    COMPUTER_SCIENCE("Computer Science"),
    ECONOMICS("Economics"),
    ENGLISH("English"),
    HINDI("Hindi"),
    HISTORY("History"),
    MATHS("Maths"),
    PHYSICS("Physics"),
    SOCIAL_STUDIES("Social Studies");

    private final String displayName;

    Subject(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}