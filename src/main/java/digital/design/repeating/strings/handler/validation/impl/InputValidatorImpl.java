package digital.design.repeating.strings.handler.validation.impl;

import digital.design.repeating.strings.handler.validation.InputValidator;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

import static java.lang.Character.*;

/**
 * @version 1.0
 */
public class InputValidatorImpl implements InputValidator {
    private static final String INVALID_INPUT = "Invalid input";

    @Override
    public void validate(String allowedCharactersRegExp, CharSequence input) {
        Objects.requireNonNull(input, INVALID_INPUT + " - can't be null.");

        String inputAsString = input.toString();

        /*todo Empty string is valid(?) */
        if (checkIfEmpty(inputAsString)) {
            return;
        }
        checkIfContainsIllegalCharacters(allowedCharactersRegExp, inputAsString);
        checkIfStartsWithOpeningBracket(inputAsString);

        /* Stack, containing only '[' and ']'. */
        Stack<Character> bracketsStack = new Stack<>();
        int i = 0;
        while (i < inputAsString.length()) {
            if (isDigit(inputAsString.charAt(i))) {
                countDownTillAnyBracketOrThrowIllegalArgumentException(inputAsString, i);
            }

            if (inputAsString.charAt(i) == '[') {
                bracketsStack.push(input.charAt(i));
            }
            if (input.charAt(i) == ']') {
                onClosingBracketFound(bracketsStack);
            }
            i++;
        }
        checkIfNoExtraClosingBrackets(bracketsStack);
    }

    //todo как быть с реэкспом?
    /**
     * Method for testing with default regex.
     */
    public void validate(CharSequence input) throws NullPointerException, IllegalArgumentException {
        validate("[a-zA-Z0-9/[/]]*", input);
    }

    private void checkIfContainsIllegalCharacters(String allowedCharactersRegExp, String inputAsString) {
        if (!inputAsString.matches(allowedCharactersRegExp)) {
            throw new IllegalArgumentException(INVALID_INPUT + " - contains illegal characters.");
        }
    }

    private boolean checkIfEmpty(String inputAsString) {
        return inputAsString.equals("");
    }

    private void checkIfStartsWithOpeningBracket(String inputAsString) {
        if (inputAsString.charAt(0) == '[') {
            throw new IllegalArgumentException(INVALID_INPUT + " - can't start with '['");
        }
    }

    private void countDownTillAnyBracketOrThrowIllegalArgumentException(String inputAsString, int i) {
        while (isDigit(inputAsString.charAt(i))) {
            i++;
        }
        if (inputAsString.charAt(i) != ']' || inputAsString.charAt(i) != '[') {
            throw new IllegalArgumentException(INVALID_INPUT + " - only brackets can stand after digits");
        }
    }

    private void onClosingBracketFound(Stack<Character> bracketsStack) {
        /* If bracketsStack is already empty, that means that opening and closing brackets stands in pairs,
         * but there is at least one extra closing bracket.
         * For example, consider the following strings:
         * "[] ]",
         * "1[abc]abc ]",
         * "1[a1[b1[c]]] ]]]]]".
         * Each of them contains at least one extra closing bracket, so, when we'll do bracketsStack.pop(), the
         * EmptyStackException will be thrown. */
        if (bracketsStack.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT + " - at least one extra closing bracket", new EmptyStackException());
        }
        /* If we find a closing bracket - it closes some opening. Remove an opening bracket from the stack. */
        bracketsStack.pop();
    }

    private void checkIfNoExtraClosingBrackets(Stack<Character> bracketsStack) {
        /* If stack isn't empty by the of of looping through input - it contains at least one extra opening bracket. */
        if (!bracketsStack.isEmpty()) {
            throw new IllegalArgumentException(INVALID_INPUT + " - contains " + bracketsStack.size() + "extra brackets.");
        }
    }
}
