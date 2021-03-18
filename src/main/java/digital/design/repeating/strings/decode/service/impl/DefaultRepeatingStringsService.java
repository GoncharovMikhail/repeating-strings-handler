package digital.design.repeating.strings.decode.service.impl;

import digital.design.repeating.strings.decode.service.AbstractRepeatingStringsService;

import java.util.Stack;

/**
 * @version 1.0
 */
public class DefaultRepeatingStringsService extends AbstractRepeatingStringsService {

    @Override
    public String decode(CharSequence input) {
        handleSettings(input);

        /* Numbers before opening brackets */
        Stack<Integer> counts = new Stack<>();
        /* Resulting string stack */
        Stack<String> result = new Stack<>();

        int i = 0;
        result.push("");
        while (i < input.length()) {
            char currentChar = input.charAt(i);

            if (currentChar >= '0' && currentChar <= '9') {
                onDigitFound(input, i, counts);
            } else if (currentChar == '[') {
                result.push("");
            } else if (currentChar == ']') {
                onClosingBracketFound(result, counts);
            } else {
                result.push(result.pop() + currentChar);
            }

            i++;
        }
        return result.pop();
    }

    /**
     * Gets a numeric value of a substring before opening bracket and
     * pushes it to counts stack().
     *
     * @param input  input.
     * @param i      current position.
     * @param counts stack of counts.
     */
    private void onDigitFound(CharSequence input, int i, Stack<Integer> counts) {
        int start = i;
        while (input.charAt(i + 1) >= '0' && input.charAt(i + 1) <= '9') {
            i++;
        }
        counts.push(Integer.parseInt(input.toString().substring(start, i + 1)));
    }

    /**
     * If a closing bracket was found -
     * get substring, enclosed in brackets, and iterate it corresponding number of times.
     *
     * @param result resulting stack.
     * @param counts stack of counts.
     */
    private void onClosingBracketFound(Stack<String> result, Stack<Integer> counts) {
        String str = result.pop();
        StringBuilder tempBuilder = new StringBuilder();
        int times = counts.pop();
        for (int j = 0; j < times; j++) {
            tempBuilder.append(str);
        }
        result.push(result.pop() + tempBuilder.toString());
    }

    private void handleSettings(CharSequence input) {
        if (enableValidation) {
            validator.validate(input);
        }
    }
}