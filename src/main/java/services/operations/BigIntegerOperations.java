package services.operations;

import exceptions.NegativeSubtractionResult;
import exceptions.Reason;
import model.BigInteger;

import java.util.List;

public class BigIntegerOperations implements Operations<BigInteger> {

    @Override
    public BigInteger add(BigInteger firstNumber, BigInteger secondNumber) {

        List<Integer> longerNumber = getLongerNumber(firstNumber, secondNumber);
        List<Integer> shorterNumber = getShorterNumber(firstNumber, secondNumber);

        int lengthsDifferences = longerNumber.size() - shorterNumber.size();

        StringBuilder resultString = new StringBuilder();

        int carry = 0;

        for (int index = shorterNumber.size() - 1; index >= 0; index--) {

            int shorterNumberDigit = shorterNumber.get(index);
            int longerNumberDigit = longerNumber.get(index + lengthsDifferences);

            int newDigit = shorterNumberDigit + longerNumberDigit + carry;
            carry = newDigit / 10;
            newDigit = newDigit % 10;

            resultString.append(newDigit);
        }

        for (int index = lengthsDifferences - 1; index >= 0; index--) {

            int currDig = longerNumber.get(index);
            if (currDig + carry == 10) {

                resultString.append(0);
                carry = 1;
            } else {

                resultString.append(currDig + carry);
                carry = 0;
            }
        }

        if (carry > 0)
            resultString.append(carry);

        return new BigInteger(resultString.reverse().toString());
    }

    @Override
    public BigInteger minus(BigInteger firstNumber, BigInteger secondNumber) {

        if (compare(firstNumber, secondNumber) < 0) {

            throw new NegativeSubtractionResult(Reason.NEGATIVE_SUBTRACTION_RESULT);
        }

        int lengthsDifferences = firstNumber.size() - secondNumber.size();

        StringBuilder resultString = new StringBuilder();

        int carry = 0;

        for (int index = secondNumber.size() - 1; index >= 0; index--) {

            int biggerNumDig = firstNumber.get(index + lengthsDifferences) - carry;
            int smallerNumDig = secondNumber.get(index);

            carry = 0;

            if (biggerNumDig < smallerNumDig) {
                carry = 1;
                biggerNumDig += 10;
            }

            resultString.append(biggerNumDig - smallerNumDig);
        }

        for (int index = lengthsDifferences - 1; index >= 0; index--) {
            int currDig = firstNumber.get(index);

            if (carry > currDig) {

                resultString.append(currDig + 10 - carry);
                carry = 1;
            } else {

                resultString.append(currDig - carry);
                carry = 0;
            }
        }

        return new BigInteger(resultString.reverse().toString());
    }

    @Override
    public BigInteger multiplication(BigInteger firstNumber, BigInteger secondNumber) {

        BigInteger finalResult = new BigInteger("0");
        BigInteger currentUnit = new BigInteger("1");

        for (int otherNumIndex = secondNumber.size() - 1; otherNumIndex >= 0; otherNumIndex--) {
            int currentOtherNumDigit = secondNumber.get(otherNumIndex);

            BigInteger currentResult = new BigInteger("0");
            BigInteger currentDigitUnit = new BigInteger(currentUnit.toString());

            for (int index = firstNumber.size() - 1; index >= 0; index--) {
                int currentDigit = firstNumber.get(index);
                int digitsMultiplication = currentDigit * currentOtherNumDigit;

                currentResult = multiplyUnit(currentDigitUnit, digitsMultiplication);
                multiplyByTen(currentDigitUnit);
            }

            multiplyByTen(currentUnit);
            finalResult = add(finalResult, currentResult);
        }

        return finalResult;
    }

    private boolean isBigIntZero(BigInteger number) {

        return number.toString().replace("0", "").equals("");
    }

    private BigInteger multiplyUnit(BigInteger currentDigitUnit, int majorUnits) {

        String majorUnitsString = String.valueOf(majorUnits);
        String newNumber = majorUnitsString + currentDigitUnit.toString().substring(1);

        return new BigInteger(newNumber);
    }

    private void multiplyByTen(BigInteger currentDigitUnit) {
        currentDigitUnit.getNumberValue().add(0);
    }

    private List<Integer> getLongerNumber(BigInteger firstNumber, BigInteger secondNumber) {

        return firstNumber.size() >= secondNumber.size() ? firstNumber.getNumberValue() : secondNumber.getNumberValue();
    }

    private List<Integer> getShorterNumber(BigInteger firstNumber, BigInteger secondNumber) {

        return firstNumber.size() < secondNumber.size() ? firstNumber.getNumberValue() : secondNumber.getNumberValue();
    }


    @Override
    public BigInteger division(BigInteger firstNumber, BigInteger secondNumber) {

        if (isBigIntZero(secondNumber)) {

            throw new ArithmeticException();
        }

        int compareResult = compare(firstNumber, secondNumber);

        if (compareResult == 0) {

            return new BigInteger("1");
        } else if (compareResult < 0) {

            return new BigInteger("0");
        }

        BigInteger result = new BigInteger("0");
        BigInteger tempNumber = new BigInteger("0");

        while (compare(tempNumber, firstNumber) < 0) {

            tempNumber = add(tempNumber, secondNumber);

            if (compare(tempNumber, firstNumber) < 0) {
                result = add(result, new BigInteger("1"));
            }
        }

        return result;
    }


    @Override
    public BigInteger power(BigInteger number, int power) {
        return null;
    }

    @Override
    public BigInteger squareRoot(BigInteger number) {
        return null;
    }

    @Override
    public int compare(BigInteger firstNumber, BigInteger secondNumber) {

        int numbersLengthDifference = compareNumberLength(firstNumber.size(), secondNumber.size());
        if (numbersLengthDifference != 0) {

            return numbersLengthDifference;
        } else {

            for (int index = 0; index < firstNumber.size(); index++) {

                if (firstNumber.get(index) > secondNumber.get(index))
                    return 1;

                else if (firstNumber.get(index) < secondNumber.get(index))
                    return -1;
            }

            return 0;
        }
    }

    private int compareNumberLength(int firstLength, int secondLength) {

        if (firstLength > secondLength) {

            return 1;
        } else if (firstLength < secondLength) {

            return -1;
        }

        return 0;
    }


}