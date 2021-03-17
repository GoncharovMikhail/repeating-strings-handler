package digital.design.repeatin.strings.handler.unit.validation;

import digital.design.repeating.strings.handler.validation.impl.InputValidatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * @version 1.0
 */
public class InputValidatorImplTest {
    private InputValidatorImpl inputValidator = new InputValidatorImpl();

    /* INVALID INPUTS */

    @Test
    public void npe() {
        assertThrows(NullPointerException.class, () -> inputValidator.validate(null));
    }

    @Test
    public void containsIllegalCyrillicCharacters() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("aбвгcde[p]"));
    }

    @Test
    public void containsIllegalSymbols() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("_$123[]"));
    }

    @Test
    public void startsWithOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("[10[abc]"));
    }

    @Test
    public void onlyOneClosingBracket() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("abs10]"));
    }

    @Test
    public void onlyOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("aa1["));
    }

    @Test
    public void digitsBetweenBrackets() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("a10[a1b10[a]]"));
    }

    @Test
    public void wrongBracketsOrder() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("]abc["));
    }

    @Test
    public void openingAndClosingBracketsAmountDoesntMatch() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("[[]abс]extraClosingBracket]"));
    }

    @Test
    public void noNumberBeforeOpeningBracket() {
        assertThrows(IllegalArgumentException.class, () ->inputValidator.validate("abc[abc]"));
    }

    /* VALID INPUTS */

    @Test
    public void emptyStringInput() {
        inputValidator.validate("");
    }
}
