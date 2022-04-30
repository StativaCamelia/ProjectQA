package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class BigInteger {

    private List<Integer> numberValue;
    private String numberAsString;

    public BigInteger(String number) {

        numberAsString = number;
        numberValue = new ArrayList<>();
        numberValue.addAll(number.chars().mapToObj(c -> Integer.parseInt(String.valueOf((char) c))).collect(Collectors.toList()));
    }

    public int size() {

        return numberValue.size();
    }

    public Integer get(int index) {

        return numberValue.get(index);
    }

    @Override
    public String toString() {

        return StringUtils.join(numberValue, "");
    }
}
