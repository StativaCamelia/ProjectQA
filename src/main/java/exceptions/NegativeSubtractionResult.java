package exceptions;

public class NegativeSubtractionResult extends BusinessException {

    private Reason reason;

    public NegativeSubtractionResult(Reason reason) {

        this.reason = reason;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
