package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", (map) -> map != null);
        return this;
    }

    public MapSchema sizeof(int length) {
        addCheck("sizeof", (map) -> map != null && map.size() == length);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> void shape(Map<String, BaseSchema<T>> schemas) {
        for (String mapKey : schemas.keySet()) {
            addCheck(mapKey, (map) -> schemas.get(mapKey).isValid((T) map.get(mapKey)));
        }
    }


}
