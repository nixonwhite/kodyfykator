package ua.org.gurt.kodyfykator.domain;

public class KodyfykatorIllegalException extends IllegalArgumentException {

    public KodyfykatorIllegalException() {
        super("No such element!");
    }
}
