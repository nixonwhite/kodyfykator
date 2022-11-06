package ua.org.gurt.kodyfykator.domain;

public enum SettleTypes {
    CITY_AREA("B"),
    REGION("O"),
    SPECIAL_CITY("K"),
    AREA("P"),
    CITY("M"),
    TERRITORY("H"),
    URBAN_VILLAGE("T"),
    VILLAGE("C"),
    TOWNSHIP("X");

    private final String name;
    SettleTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
