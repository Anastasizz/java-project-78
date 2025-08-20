package hexlet.code.schemas;

import hexlet.code.Check;

import java.util.HashMap;
import java.util.Map;

abstract class BaseSchema<T> {
    protected Map<String, Check<T>> checks = new HashMap<>();

    protected void addCheck(String name, Check<T> check) {
        checks.put(name, check);
    }

    public boolean isValid(T value) {
        for (var check : checks.values()) {
            if (!check.apply(value)) {
                return false;
            }
        }
        return true;
    }
}
