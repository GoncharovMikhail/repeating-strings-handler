package digital.design.repeating.strings.handler.validation;

/**
 * An root interface for validating input.
 *
 * @version 1.0
 */
public interface InputValidator {

    /**
     * @param input input.
     * @throws NullPointerException iff input == null.
     * @throws IllegalArgumentException iff input is incorrect. Contract is defined by developer.
     */
    void validate(CharSequence input);
}
