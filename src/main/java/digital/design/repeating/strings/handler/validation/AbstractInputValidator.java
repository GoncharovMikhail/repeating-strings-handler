package digital.design.repeating.strings.handler.validation;

/**
 * @author Mihovel
 * @version 1.0
 */
public abstract class AbstractInputValidator implements InputValidator {
    protected final String allowedCharactersAsRegExp;

    protected AbstractInputValidator(String allowedCharactersAsRegExp) {
        this.allowedCharactersAsRegExp = allowedCharactersAsRegExp;
    }

    protected String getAllowedCharactersAsRegExp() {
        return allowedCharactersAsRegExp;
    }
}
