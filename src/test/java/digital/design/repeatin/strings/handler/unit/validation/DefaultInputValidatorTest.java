package digital.design.repeatin.strings.handler.unit.validation;

import digital.design.repeating.strings.decode.validation.InputValidator;
import digital.design.repeating.strings.decode.validation.impl.DefaultInputValidator;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * @version 1.0
 */
public class DefaultInputValidatorTest {
    private final String ALLOWED_CHARACTERS = "[a-zA-Z0-9\\[\\]]*";
    private final InputValidator validator = new DefaultInputValidator()
        .setAllowedCharactersAsRegExp(ALLOWED_CHARACTERS);

    /* INVALID INPUTS */

    @Test
    public void npe() {
        assertThrows(NullPointerException.class, () -> validator.validate(null));
    }

    @Test
    public void containsIllegalCyrillicCharacters() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("aбвгcde[p]"));
    }

    @Test
    public void containsIllegalSymbols() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("_$123[]"));
    }

    @Test
    public void startsWithOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("[10[abc]"));
    }

    @Test
    public void onlyOneClosingBracket() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("abs10]"));
    }

    @Test
    public void onlyOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("aa1["));
    }

    @Test
    public void digitsBetweenBrackets() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("a10[a1b10[a]]"));
    }

    @Test
    public void wrongBracketsOrder() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("]abc["));
    }

    @Test
    public void openingAndClosingBracketsAmountDoesntMatch() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("[[]abс]extraClosingBracket]"));
    }

    @Test
    public void noDigitsBeforeOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("abc[abc]"));
    }

    @Test
    public void emptyStringInput() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(""));
    }

    @Test
    public void singleSlash() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate("/"));
    }

    /* VALID INPUTS */

    @Test
    public void validInputNoBrackets() {
        validator.validate("abcdefghijklmnopqrstuvwxyABCDEFGHIJKLOMNOPQRSTUVWXUZ");
    }

    @Test
    public void easyValidInput() {
        validator.validate("ab1[c]");
    }

    @Test
    public void hard() {
        validator.validate("a1[b2[c]d4[d5[ef6[g]]]]");
    }
}