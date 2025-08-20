package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addCheck("required", (map) -> map != null); //map instanceof Map
        return this;
    }

    public MapSchema sizeof(int length) {
        addCheck("sizeof", (map) -> map != null && map.size() == length);
        return this;
    }
}
