package exceptions;

public class DivisionByZero extends BusinessException {

    private Reason reason;

    public DivisionByZero(Reason reason) {

        this.reason = reason;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
