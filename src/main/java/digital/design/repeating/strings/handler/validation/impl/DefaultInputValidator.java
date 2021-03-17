package digital.design.repeating.strings.handler.validation.impl;

import digital.design.repeating.strings.handler.validation.AbstractInputValidator;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

import static java.lang.Character.isDigit;

/**
 * @version 1.0
 */
public class DefaultInputValidator extends AbstractInputValidator {
    private static final String INVALID_INPUT = "Invalid input";

    public DefaultInputValidator(String allowedCharactersAsRegExp) {
        super(allowedCharactersAsRegExp);
    }

    @Override
    public void validate(CharSequence input) {
        Objects.requireNonNull(input, INVALID_INPUT + " - can't be null.");

        String inputAsString = input.toString();

        /* todo Empty string valid(?) */
        if (inputAsString.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT + " - input is blank.");
        }
        checkIfContainsIllegalCharacters(this.allowedCharactersAsRegExp, inputAsString);
        checkIfStartsWithOpeningBracket(inputAsString);

        /* Stack, containing only '[' and ']'. */
        Stack<Character> bracketsStack = new Stack<>();
        int i = 0;
        while (i < inputAsString.length()) {
            if (isDigit(inputAsString.charAt(i))) {
                countDownTillAnyBracketOrThrowIllegalArgumentException(inputAsString, i);
            }

            /* Handling brackets. */
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof DefaultInputValidator) {
            DefaultInputValidator d = (DefaultInputValidator) o;
            return Objects.equals(d.getAllowedCharactersAsRegExp(), this.allowedCharactersAsRegExp);
        }
        return false;
    }

    private void checkIfContainsIllegalCharacters(String allowedCharactersRegExp, String inputAsString) {
        if (!inputAsString.matches(allowedCharactersRegExp)) {
            throw new IllegalArgumentException(INVALID_INPUT + " - contains illegal characters.");
        }
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
        if ((inputAsString.charAt(i) != ']') && (inputAsString.charAt(i) != '[')) {
            throw new IllegalArgumentException(INVALID_INPUT + " - only brackets can stand after digits");
        }
    }

    private void onClosingBracketFound(Stack<Character> bracketsStack) {
        /* If bracketsStack is already empty, that means that opening and closing brackets stands in pairs,
         * but there is at least one extra closing bracket.
         * For example, consider the following strings:
         * "1[a]followingBracketIsExtra]",
         * "1[abc]abc1[abc]followingBracketIsExtra]",
         * "1[a1[b1[c]]]followingBracketsAreExtras]]]]]".
         *
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
