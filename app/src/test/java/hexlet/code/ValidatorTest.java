package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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

        schema.minLength(2).minLength(7);
        assertFalse(schema.isValid("Hexlet"), ""); // false

        schema.contains("fox");
        assertFalse(schema.isValid("Hexlet hexlet"), ""); // false
        assertTrue(schema.isValid("Hexlet fox hexlet"), ""); // true

    }

    @Test
    public void numberSchemaTest() {
        var schema = validator.number();

        assertTrue(schema.isValid(null), ""); // true
        assertTrue(schema.isValid(0), ""); // true
        assertTrue(schema.isValid(5), ""); // true
        assertTrue(schema.isValid(-7), ""); // true

        schema.positive();
        assertTrue(schema.isValid(null), ""); // true
        assertFalse(schema.isValid(0), ""); // false
        assertTrue(schema.isValid(5), ""); // true
        assertFalse(schema.isValid(-7), ""); // false

        schema.required();
        assertFalse(schema.isValid(null), ""); // false
        assertTrue(schema.isValid(5), ""); // true

        schema.range(5, 10);
        assertTrue(schema.isValid(5), ""); // true
        assertFalse(schema.isValid(12), ""); // false

    }

    @Test
    public void MapSchemaTest() {
        var schema = validator.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);
        assertFalse(schema.isValid(data));  // false

        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true
    }
}
