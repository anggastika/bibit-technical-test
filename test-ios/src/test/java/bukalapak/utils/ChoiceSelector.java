package bukalapak.utils;

/**
 * Created by sekarayukarindra on 25/09/18.
 */
public class ChoiceSelector {

    private static final ChoiceSelector DONE = new ChoiceSelector(null) {
        @Override
        public ChoiceSelector when(String subString, Runnable r) {
            return this;
        }

        @Override
        public void orElse(Runnable r) {
        }
    };

    private final String str;

    private ChoiceSelector(String str) {
        this.str = str;
    }

    public static ChoiceSelector of(String str) {
        return new ChoiceSelector(str);
    }

    public ChoiceSelector when(String subString, Runnable r) {
        if (str.toLowerCase().equals(subString.toLowerCase())) {
            r.run();
            return DONE;
        }
        return this;
    }

    public void orElse(Runnable r) {
        r.run();
    }

}
