package ua.org.gurt.kodyfykator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.springframework.core.io.ClassPathResource;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;

import java.io.IOException;
import java.util.*;

@ToString
public final class Util {

    public static final Map<String, String> OBJECT_TYPES = createMap();

    public static final List<SettlementEntity> SETTLES = createList();

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

    private static List<SettlementEntity> createList() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new ClassPathResource("classpath:kodyfikator.json").getInputStream(),
                    new TypeReference<List<SettlementEntity>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}


