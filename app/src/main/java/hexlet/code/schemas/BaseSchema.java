package hexlet.code.schemas;

import hexlet.code.Check;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {
    protected Map<String, Check<T>> checks = new HashMap<>();

    protected void addCheck(String name, Check<T> check) {
        checks.put(name, check);
    }

    public boolean isValid(T value) {
        for (var check : checks.values()) {
            if (value == null) {
                return !checks.containsKey("required");
            }
            if (!check.apply(value)) {
                return false;
            }
        }
        return true;

    }
}
