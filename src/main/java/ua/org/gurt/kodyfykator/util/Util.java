package ua.org.gurt.kodyfykator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import ua.org.gurt.kodyfykator.domain.SettleTypes;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
public final class Util {

    private Util() {
    }

    public static final Map<String, String> SETTLE_TYPES = createMap();

    public static final List<SettlementEntity> SETTLES = createList();

    private static Map<String, String> createMap() {
        return Map.of(
                SettleTypes.REGION.getName(), "область",
                SettleTypes.SPECIAL_CITY.getName(), "місто",
                SettleTypes.AREA.getName(), "район",
                SettleTypes.TERRITORY.getName(), "територія територіальної громади",
                SettleTypes.CITY.getName(), "місто",
                SettleTypes.URBAN_VILLAGE.getName(), "селище міського типу",
                SettleTypes.VILLAGE.getName(), "село",
                SettleTypes.TOWNSHIP.getName(), "селище",
                SettleTypes.CITY_AREA.getName(), "район в місті");
    }

    private static List<SettlementEntity> createList() {
        try (InputStream is = new ClassPathResource("classpath:kodyfikator.json").getInputStream()) {
            return new ObjectMapper().readValue(is, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("[!] {}", e.getMessage());
        }
        return new ArrayList<>();
    }
}


