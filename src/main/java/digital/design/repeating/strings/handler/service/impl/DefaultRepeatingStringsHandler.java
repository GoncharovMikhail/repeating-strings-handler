package digital.design.repeating.strings.handler.service.impl;

import digital.design.repeating.strings.handler.service.AbstractRepeatingStringsHandler;

import java.util.Stack;

/**
 * @version 1.0
 */
public class DefaultRepeatingStringsHandler extends AbstractRepeatingStringsHandler {

    @Override
    public String handle(CharSequence input) {
        handleSettings(input);

        Stack<Integer> counts = new Stack<>();
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

    private void onDigitFound(CharSequence input, int i, Stack<Integer> counts) {
        int start = i;
        while (input.charAt(i + 1) >= '0' && input.charAt(i + 1) <= '9') {
            i++;
        }
        counts.push(Integer.parseInt(input.toString().substring(start, i + 1)));
    }

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
