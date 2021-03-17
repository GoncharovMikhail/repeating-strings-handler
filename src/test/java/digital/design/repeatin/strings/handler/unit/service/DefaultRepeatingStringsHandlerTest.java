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
        .setValidator(new DefaultInputValidator(VALID_SYMBOLS));

    @Test
    public void easy() {
        assertEquals("ab", handler.handle("ab"));
    }

    @Test
    public void singleRepeat() {
        assertEquals("cabddbddbdd", handler.handle("ca3[b2[d]]"));
    }

    @Test
    public void symbolsAfterInnerRepeat() {
        assertEquals("abcdef", handler.handle("a1[b1[c]def]"));
    }

    @Test
    public void sq() {

    }
}
