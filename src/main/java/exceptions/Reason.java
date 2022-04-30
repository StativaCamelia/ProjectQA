package exceptions;

public enum Reason {

    NEGATIVE_SUBTRACTION_RESULT("Negative subtraction result"),
    DIVISION_BY_ZERO("Division by Zero");

    private String message;

    Reason(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }
}
