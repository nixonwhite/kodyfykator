package ua.org.gurt.kodyfykator.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ResourceUtils;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;

import java.io.IOException;
import java.util.List;

public class Codes {

    public static List<SettlementEntity> settles;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            settles = mapper.readValue(ResourceUtils.getFile("classpath:kodyfikator.json"),
                    new TypeReference<List<SettlementEntity>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
