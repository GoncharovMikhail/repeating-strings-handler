package digital.design.repeating.strings.decode.validation;

/**
 * An root interface for validating input.
 *
 * @version 1.0
 */
public interface InputValidator {

    /**
     * Set allowed characters as regexp.
     *
     * @param allowedCharactersAsRegExp allowed characters as a regexp.
     * @return configured instance.
     */
    InputValidator setAllowedCharactersAsRegExp(String allowedCharactersAsRegExp);

    /**
     * @param input input.
     * @throws NullPointerException iff input == null.
     * @throws IllegalArgumentException iff input is invalid. Developer defines when it should be thrown.
     */
    void validate(CharSequence input);
}
