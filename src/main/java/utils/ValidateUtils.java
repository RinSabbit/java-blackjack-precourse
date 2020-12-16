package utils;

import exception.BlackJackException;
import java.util.Arrays;

public class ValidateUtils {

    public static int isValidMoney(String input) {
        int count = isNumber(input);
        isNaturalNumber(count);
        return count;
    }

    private static int isNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new BlackJackException("배팅금액은 숫자이어야 합니다.");
        }
    }

    private static void isNaturalNumber(int count) {
        if (count < 1) {
            throw new BlackJackException("배팅금액은 1원 이상이어야 합니다.");
        }
    }

    public static void hasValidNames(String[] names) {
        Arrays.stream(names)
            .map(String::trim)
            .forEach(ValidateUtils::hasProperLength);
    }

    private static void hasProperLength(String name) {
        if (name.length() > 0) {
            return;
        }
        throw new BlackJackException("이름은 1자 이상이어야 합니다.");
    }

    public static boolean isValidChoice(String input){
        if(input.toLowerCase().equals("y")){
            return true;
        }
        if(input.toLowerCase().equals("n")){
            return false;
        }
        throw new BlackJackException("옳지 않은 값을 입력했습니다");
    }
}