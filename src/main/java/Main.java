import model.BigInteger;
import services.operations.BigIntegerOperations;

public class Main {

    public static void main(String[] args) {

        BigInteger bigInteger = new BigInteger("45");

        BigIntegerOperations operations = new BigIntegerOperations();

        BigInteger second = new BigInteger("6");

        System.out.println(operations.division(bigInteger, second));
    }
}
