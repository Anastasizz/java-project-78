package hexlet.code.schemas;

import hexlet.code.Check;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addCheck("required", (number) -> number != null);
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", (number) -> number == null || number > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("range", (number) -> number >= min && number <= max);
        return this;
    }

}
