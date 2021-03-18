package digital.design.repeatin.strings.handler.unit.service;

import digital.design.repeating.strings.decode.service.RepeatingStringsService;
import digital.design.repeating.strings.decode.service.impl.DefaultRepeatingStringsService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 */
public class DefaultRepeatingStringsServiceTest {
    private final String ALLOWED_CHARACTERS = "[a-zA-Z0-9\\[\\]]*";

    private final RepeatingStringsService handler = new DefaultRepeatingStringsService()
        /* По условию задачи, строка валидна -> ее не обязательно валидировать.
         * При задании верифаера, валидацию будет проходить автоматически. */
        /*.setValidator(new DefaultInputValidator().setAllowedCharactersAsRegExp(ALLOWED_CHARACTERS))*/
    ;

    @Test
    public void easy() {
        assertEquals("ab", handler.decode("ab"));
    }

    @Test
    public void singleRepeat() {
        assertEquals("cab", handler.decode("ca1[b]"));
    }

    @Test
    public void symbolsAfterInnerRepeat() {
        assertEquals("abcdef", handler.decode("a1[b1[c]def]"));
    }

    @Test
    public void longOutput() {
        assertEquals("abcccccbcccccbcccccbcccccabcccccbcccccbcccccbcccccabcccccbcccccbcccccbccccc", handler.decode("3[a4[b5[c]]]"));
    }
}
