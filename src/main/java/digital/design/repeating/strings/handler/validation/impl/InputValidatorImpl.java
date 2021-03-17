package digital.design.repeating.strings.handler.validation.impl;

import digital.design.repeating.strings.handler.validation.InputValidator;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

/**
 * @version 1.0
 */
public class InputValidatorImpl implements InputValidator {

    @SuppressWarnings("ConstantConditions")
    @Override
    public void validate(String allowedCharactersRegExp, CharSequence input) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(input, "Invalid input - can't be null");

        String inputAsString = input.toString();

        /* Check if input contains illegal characters. */
        if (!inputAsString.matches(allowedCharactersRegExp)) {
            throw new IllegalArgumentException("Invalid input - contains illegal characters");
        }

        /* Stack, containing only '[' and ']'. */
        Stack<Character> bracketsStack = new Stack<>();

        for (int i = 0; i < inputAsString.length(); i++) {
            char currentChar = inputAsString.charAt(i);

            if (currentChar == '[') {
                /* If we find an opening bracket - it should be closed somewhere. Add it to the stack. */
                bracketsStack.push(currentChar);
            } else if (currentChar == ']') {
                /* If bracketsStack is already empty, that means that opening and closing brackets stands in pairs,
                 * but there is at least one extra closing bracket.
                 * For example, consider the following Strings:
                 * "[] ]",
                 * "1[abc]abc ]",
                 * "1[a1[b1[c]]] ]]]]]".
                 * Each of them contains at least one extra closing bracket, so, when we'll do bracketsStack.pop(), the
                 * EmptyStackException will be thrown. */
                if (bracketsStack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid input - at least one extra closing bracket", new EmptyStackException());
                }
                /* If we find a closing bracket - it closes some opening. Remove an opening bracket from the stack. */
                bracketsStack.pop();
            }
            /* If stack isn't empty by the of of looping through input -
             * it contains at least one extra opening bracket. */
            if (!bracketsStack.isEmpty()) {
                throw new IllegalArgumentException("Invalid input - contains " + bracketsStack.size() + "extra brackets.");
            }
        }
    }

    public void validate(CharSequence input) throws NullPointerException, IllegalArgumentException {
        validate("[a-zA-Z0-9[]]", input);
    }
}
