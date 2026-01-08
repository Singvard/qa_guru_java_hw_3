package models;

public enum City {
    DELHI,
    GURGAON,
    NOIDA,
    AGRA,
    LUCKNOW,
    MERRUT,
    KARNAL,
    PANIPAT,
    JAIPUR,
    JAISELMER;

    @Override
    public String toString() {
        var name = name();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
