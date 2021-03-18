package digital.design.repeating.strings.handler.validation;

import lombok.NoArgsConstructor;

/**
 * @version 1.0
 */
@NoArgsConstructor
public abstract class AbstractInputValidator implements InputValidator {
    /**
     * By default, all characters are allowed.
     */
    protected String allowedCharactersAsRegexp = ".*";

    @Override
    public InputValidator setAllowedCharactersAsRegExp(String allowedCharactersAsRegExp) {
        this.allowedCharactersAsRegexp = allowedCharactersAsRegExp;
        return this;
    }
}
