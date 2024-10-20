package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("덧셈할 문자열을 입력해 주세요.:");
            String inputString = Console.readLine();
            inputString = inputString.replace("\\n", "\n");

            String delimiter = "[,:]";

            if (inputString.startsWith("//")) {
                int delimiterEndIndex = inputString.indexOf("\n");
                if (delimiterEndIndex == -1) {
                    throw new IllegalArgumentException("커스텀 구분자가 미완성되었습니다.");
                }
                String customDelimiter = inputString.substring(2, delimiterEndIndex);
                if (customDelimiter.isEmpty()) {
                    throw new IllegalArgumentException("커스텀 구분자를 입력해주세요.");
                }
                // 커스텀 구분자와 기존 구분자를 함께 사용하도록 함
                delimiter = Pattern.quote(customDelimiter) + "|,|:";
                inputString = inputString.substring(delimiterEndIndex + 1);
            }

            String[] tokens = inputString.split(delimiter);

            List<Integer> numbers = convertToIntegers(tokens);

            int sum = calculateSumWithForLoop(numbers);

            System.out.println("합계: " + sum);

        } catch (IllegalArgumentException e) {
            System.err.println("입력 값이 잘못되었습니다: " + e.getMessage());
        }
    }

    private static List<Integer> convertToIntegers(String[] tokens) {
        List<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            String trimmedToken = token.trim();
            if (trimmedToken.isEmpty()) {
                continue;
            }
            try {
                int number = Integer.parseInt(trimmedToken);
                if (number < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
                }
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: '" + trimmedToken + "'");
            }
        }
        return numbers;
    }

    private static int calculateSumWithForLoop(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}