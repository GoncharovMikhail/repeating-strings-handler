package digital.design.repeating.strings.handler;

import digital.design.repeating.strings.handler.service.RepeatingStringsHandler;
import digital.design.repeating.strings.handler.service.impl.DefaultRepeatingStringsHandler;

/**
 * @version 1.0
 */
public class RepeatingStringsHandlerApplicationRunner {

    public static void main(String[] args) {
        RepeatingStringsHandler handler = new DefaultRepeatingStringsHandler();

        String handled = handler.handle("inputStringHere");
    }
}
