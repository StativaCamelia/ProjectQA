package services.operations;

public interface Operations<T> {

    T add(T firstNumber, T secondNumber);

    T minus(T firstNumber, T secondNumber);

    T multiplication(T firstNumber, T secondNumber);

    T division(T firstNumber, T secondNumber);

    T power(T number, int power);

    T squareRoot(T number);

    int compare(T firstNumber, T secondNumber);
}
