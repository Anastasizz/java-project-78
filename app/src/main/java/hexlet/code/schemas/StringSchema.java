package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addCheck("required", (str) -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        addCheck("minLength", (str) -> str.length() >= min);
        return this;
    }

    public StringSchema contains(String subStr) {
        addCheck("contains", (str) -> str.contains(subStr));
        return this;
    }

}
