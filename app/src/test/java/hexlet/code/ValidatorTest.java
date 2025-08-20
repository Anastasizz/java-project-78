package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {
    private static Validator validator;

    @BeforeAll
    public static void init() {
        validator = new Validator();
    }
    @Test
    public void stringSchemaTest() {
        var schema = validator.string();

        assertTrue(schema.isValid(""), ""); // true
        assertTrue(schema.isValid(null), ""); // true
        assertTrue(schema.isValid("Hexlet"), ""); // true

        schema.required();
        assertFalse(schema.isValid(""), ""); // false
        assertFalse(schema.isValid(null), ""); // false

        schema.minLength(7);
        assertFalse(schema.isValid("Hexlet"), ""); // false

        schema.contains("fox");
        assertFalse(schema.isValid("Hexlet hexlet"), ""); // false
        assertTrue(schema.isValid("Hexlet fox hexlet"), ""); // true

    }

    @Test
    public void simpleTest() {
        assertTrue(true);
    }
}
