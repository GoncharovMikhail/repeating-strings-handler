package digital.design.repeating.strings.decode.service;

import digital.design.repeating.strings.decode.validation.InputValidator;

/**
 * A service for handling input.
 *
 * @version 1.0
 */
public interface RepeatingStringsService {

    /**
     * Set validator instance.
     *
     * @param validator implemented validator.
     * @return handled string.
     */
    RepeatingStringsService setValidator(InputValidator validator);

    /**
     * Define if validation should be invoked.
     *
     * @return configured instance.
     */
    RepeatingStringsService enableValidation(boolean enableValidation);

    /**
     * A method for handling input.
     *
     * @param input input.
     * @return handled string.
     */
    String decode(CharSequence input);
}