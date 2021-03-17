package digital.design.repeatin.strings.handler.unit.validation;

import digital.design.repeating.strings.handler.validation.impl.InputValidatorImpl;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

/**
 * @version 1.0
 */
public class InputValidatorImplTest {
    private InputValidatorImpl inputValidator = new InputValidatorImpl();

    @Test
    public void npe() {
        assertThrows(NullPointerException.class, () -> inputValidator.validate(null));
    }

    @Test
    public void onlyOneBracket() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("abs]"));
    }

    @Test
    public void wrongBracketsOrder() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("]abc["));
    }

    @Test
    public void openingAndClosingBracketsAmountDoesntMatch() {
        assertThrows(IllegalArgumentException.class, () -> inputValidator.validate("[  []  abc ] ]"));
    }

    @Test
    public void emptyStringInput() {
        inputValidator.validate("");
    }

    @Test
    public void testValidInput() {
        inputValidator.validate("[abc]");
    }

    @Test
    public void q() {

    }
}
