package hexlet.code.schemas;

import hexlet.code.Rule;

import java.util.Objects;

public class StringSchema {
    private Rule required = (str) -> true;
    private Rule minLength = (str) -> true;
    private Rule contains = (str) -> true;

    public StringSchema required() {
        required = (str) -> str != null && !str.isEmpty();
        return this;
    }

    public StringSchema minLength(int min) {
        minLength = (str) -> str.length() >= min;
        return this;
    }

    public StringSchema contains(String subStr) {
        contains = (str) -> str.contains(subStr);
        return this;
    }

    public boolean isValid(Object obj) {
        var str = Objects.toString(obj, "");
        return contains.apply(str) && minLength.apply(str) && required.apply(str);
    }
}
