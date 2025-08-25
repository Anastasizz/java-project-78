package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
        assertFalse(schema.isValid(null), ""); // false

    }

    @Test
    public void mapSchemaTest() {
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


        var schema2 = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema2.shape(schemas);


        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema2.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema2.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema2.isValid(human3)); // false
    }
}
