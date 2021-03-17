package digital.design.repeating.strings.handler.service;

import digital.design.repeating.strings.handler.validation.AbstractInputValidator;
import digital.design.repeating.strings.handler.validation.impl.DefaultInputValidator;

/**
 * A 'builder' class for any {@link RepeatingStringsHandler}.
 * P.S. У меня ломбок не работает:( Пишу свои костыли.
 *
 * @version 1.0
 */
public abstract class AbstractRepeatingStringsHandler implements RepeatingStringsHandler {
    protected boolean enableValidation = false;
    protected AbstractInputValidator validator = new DefaultInputValidator(".*");

    protected AbstractRepeatingStringsHandler() {
    }

    protected AbstractRepeatingStringsHandler(boolean enableValidation, AbstractInputValidator validator) {
        this.enableValidation = enableValidation;
        this.validator = validator;
    }

    public AbstractRepeatingStringsHandler setEnableValidation(boolean enableValidation) {
        this.enableValidation = enableValidation;
        return this;
    }

    public AbstractRepeatingStringsHandler setValidator(AbstractInputValidator validator) {
        this.validator = validator;
        this.enableValidation = true;
        return this;
    }
}
