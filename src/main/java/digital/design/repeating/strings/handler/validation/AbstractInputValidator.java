package digital.design.repeating.strings.handler.validation;

/**
 * A 'builder' class to define allowed symbols as a regular expression.
 * <p>
 * P.S. У меня ломбок не работает:( Пишу свои костыли.
 *
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