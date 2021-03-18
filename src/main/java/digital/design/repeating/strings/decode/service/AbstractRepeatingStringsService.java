package digital.design.repeating.strings.decode.service;

import digital.design.repeating.strings.decode.validation.InputValidator;
import digital.design.repeating.strings.decode.validation.impl.DefaultInputValidator;
import lombok.NoArgsConstructor;

/**
 * A 'builder' class for any {@link RepeatingStringsService}.
 * Used for configuring validation.
 *
 * @version 1.0
 */
@NoArgsConstructor
public abstract class AbstractRepeatingStringsService implements RepeatingStringsService {
    /**
     * Defaults.
     * Can be configured by calling {@link #enableValidation(boolean)} and {@link #setValidator(InputValidator)} methods.
     */
    protected boolean enableValidation = false;
    protected InputValidator validator = new DefaultInputValidator();

    /**
     * Lazily init if validation should be executed.
     *
     * @param validator validator instance.
     * @return configured instance.
     * @apiNote the {@link InputValidator#(boolean)} invoked automatically
     * and enables validation by default.
     */
    @Override
    public RepeatingStringsService setValidator(InputValidator validator) {
        this.validator = validator;
        return this.enableValidation(true);
    }

    /**
     * Lazily init if validation should be executed.
     *
     * @param enableValidation enable validation.
     * @return configured instance.
     */
    @Override
    public RepeatingStringsService enableValidation(boolean enableValidation) {
        this.enableValidation = enableValidation;
        return this;
    }
}