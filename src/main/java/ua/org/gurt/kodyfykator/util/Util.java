package ua.org.gurt.kodyfykator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;

import java.io.IOException;
import java.util.*;

@Slf4j
public final class Util {

    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    public static final Map<String, String> OBJECT_TYPES = createMap();

    public static final List<SettlementEntity> SETTLES = createList();

    private static Map<String, String> createMap() {
        return Map.of("O", "область", "K", "місто", "P", "район", "H", "територія територіальної громади", "M", "місто", "T", "селище міського типу", "C", "село", "X", "селище", "B", "район в місті");
    }

    private static List<SettlementEntity> createList() {
        try {
            var mapper = new ObjectMapper();
            return mapper.readValue(new ClassPathResource("classpath:kodyfikator.json").getInputStream(),
                    new TypeReference<List<SettlementEntity>>() {});
        } catch (IOException e) {
            LOGGER.error("[!]" + e.getMessage());
        }
        return new ArrayList<>();
    }
}


