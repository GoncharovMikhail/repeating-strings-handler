package digital.design.repeating.strings.handler;

import digital.design.repeating.strings.handler.service.RepeatingStringsHandler;
import digital.design.repeating.strings.handler.service.impl.DefaultRepeatingStringsHandler;
import digital.design.repeating.strings.handler.validation.impl.DefaultInputValidator;

/**
 * @version 1.0
 */
public class RepeatingStringsHandlerApplicationRunner {

    public static void main(String[] args) {
        RepeatingStringsHandler handler = new DefaultRepeatingStringsHandler()
            .setValidator(new DefaultInputValidator("[a-zA-Z0-9\\[\\]]*"));

        String handled = handler.handle("inputStringHere");
    }
}
