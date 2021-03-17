package digital.design.repeatin.strings.handler.unit.service;

import digital.design.repeating.strings.handler.service.RepeatingStringsHandler;
import digital.design.repeating.strings.handler.service.impl.DefaultRepeatingStringsHandler;
import digital.design.repeating.strings.handler.validation.impl.DefaultInputValidator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 */
public class DefaultRepeatingStringsHandlerTest {
    private final String VALID_SYMBOLS = "[a-zA-Z0-9\\[\\]]*";
    private final RepeatingStringsHandler handler = new DefaultRepeatingStringsHandler()
        /* По условию задачи, строка валидна -> ее не обязательно валидировать.
         * При задании верифаера, валидацию будет проходить автоматически. */
        /*.setValidator(new DefaultInputValidator(VALID_SYMBOLS))*/;

    @Test
    public void easy() {
        assertEquals("ab", handler.handle("ab"));
    }

    @Test
    public void singleRepeat() {
        assertEquals("cab", handler.handle("ca1[b]"));
    }

    @Test
    public void symbolsAfterInnerRepeat() {
        assertEquals("abcdef", handler.handle("a1[b1[c]def]"));
    }

    @Test
    public void longOutput() {
        assertEquals("abcccccbcccccbcccccbcccccabcccccbcccccbcccccbcccccabcccccbcccccbcccccbccccc", handler.handle("3[a4[b5[c]]]"));
    }
}
