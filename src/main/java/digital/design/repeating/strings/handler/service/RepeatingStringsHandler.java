package digital.design.repeating.strings.handler.service;

import digital.design.repeating.strings.handler.validation.InputValidator;

/**
 * A service for handling input.
 *
 * @version 1.0
 */
public interface RepeatingStringsHandler {

    /**
     * Set validator instance.
     *
     * @param validator implemented validator.
     * @return handled string.
     */
    RepeatingStringsHandler setValidator(InputValidator validator);

    /**
     * Define if validation should be invoked.
     *
     * @return configured instance.
     */
    RepeatingStringsHandler enableValidation(boolean enableValidation);

    /**
     * A method for handling input.
     *
     * @param input input.
     * @return handled string.
     */
    String handle(CharSequence input);
}