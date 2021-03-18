package digital.design.repeating.strings.decode;

import digital.design.repeating.strings.decode.service.RepeatingStringsService;
import digital.design.repeating.strings.decode.service.impl.DefaultRepeatingStringsService;
import digital.design.repeating.strings.decode.validation.impl.DefaultInputValidator;

/**
 * @version 1.0
 */
public class RepeatingStringsHandlerApplicationRunner {

    public static void main(String[] args) {
        RepeatingStringsService handler = new DefaultRepeatingStringsService()
            .setValidator(new DefaultInputValidator());

        String handled = handler.decode("inputStringHere");
    }
}
