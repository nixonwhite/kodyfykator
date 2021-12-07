package ua.org.gurt.kodyfykator.util;

import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ToString
public final class Util {

    public static final Map<String, String> OBJECT_TYPE = createMap();

    private static Map<String, String> createMap() {
        Map<String, String> objectMap = new HashMap<>();
        objectMap.put("O", "область");
        objectMap.put("K", "місто");
        objectMap.put("P", "район");
        objectMap.put("H", "територія територіальної громади");
        objectMap.put("M", "місто");
        objectMap.put("T", "селище міського типу");
        objectMap.put("C", "село");
        objectMap.put("X", "селище");
        objectMap.put("B", "район в місті");
        return Collections.unmodifiableMap(objectMap);
    }
}


