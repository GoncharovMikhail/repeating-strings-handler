package digital.design.repeating.strings.handler.service;

import digital.design.repeating.strings.handler.validation.AbstractInputValidator;
import digital.design.repeating.strings.handler.validation.InputValidator;
import digital.design.repeating.strings.handler.validation.impl.DefaultInputValidator;

/**
 * A 'builder' class for any {@link RepeatingStringsHandler}.
 * Used for configuring validation.
 * <p>
 * P.S. У меня ломбок не работает:( Пишу свои костыли.
 *
 * @version 1.0
 */
public abstract class AbstractRepeatingStringsHandler implements RepeatingStringsHandler {
    protected boolean enableValidation;
    protected InputValidator validator;

    /**
     * Call when no addition setting needed.
     */
    protected AbstractRepeatingStringsHandler() {
        enableValidation = false;
        validator = new DefaultInputValidator();
    }

    /**
     * Call when you want to define additional settings immediately.
     *
     * @param enableValidation enable validation.
     * @param validator        validator instance.
     */
    protected AbstractRepeatingStringsHandler(boolean enableValidation, AbstractInputValidator validator) {
        this.enableValidation = enableValidation;
        this.validator = validator;
    }

    /**
     * Lazily init if validation should be executed.
     *
     * @param enableValidation enable validation.
     * @return configured instance.
     */
    public AbstractRepeatingStringsHandler setEnableValidation(boolean enableValidation) {
        this.enableValidation = enableValidation;
        return this;
    }

    /**
     * Lazily init if validation should be executed.
     *
     * @param validator validator instance.
     * @return configured instance.
     * @apiNote the {@link AbstractInputValidator#setEnableValidation(boolean)} invoked automatically
     * and enables validation by default.
     */
    public AbstractRepeatingStringsHandler setValidator(AbstractInputValidator validator) {
        this.validator = validator;
        return this.setEnableValidation(true);
    }
}