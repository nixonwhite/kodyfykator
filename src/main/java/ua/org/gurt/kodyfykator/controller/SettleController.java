package ua.org.gurt.kodyfykator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gurt.kodyfykator.domain.KodyfykatorIllegalException;
import ua.org.gurt.kodyfykator.domain.SettleTypes;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;
import ua.org.gurt.kodyfykator.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@Slf4j
public class SettleController {

    @Autowired
    BuildProperties buildProperties;

    private final ObjectMapper mapper = new ObjectMapper();

    /***
     * @return String version of app
     */
    @GetMapping(value = "version", produces = MediaType.TEXT_PLAIN_VALUE)
    public String version() {
        return buildProperties.getVersion();
    }

    /***
     * Method finds settlements by given name
     * @param name String request name
     * @return JSON array of found objects as String
     */
    @PostMapping(value = "find/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findByName(@PathVariable("name") String name) {

        var filteredCities = Util.SETTLES.stream().filter(e -> e.getName().toLowerCase()
                .startsWith(name.toLowerCase())).toList();

        List<SettlementEntity> preparedForJSON = new ArrayList<>();

        for (SettlementEntity entity : filteredCities) {
            if (entity.getType().equals(SettleTypes.CITY_AREA.getName()))
                continue; //area in city; we don't care about it

            var s = new SettlementEntity();

            if (!entity.getSettlement().isEmpty()) {
                // settlements
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getSettlement().equals(entity.getSettlement()))
                        .filter(e -> e.getType().equals(entity.getType())).findFirst()
                        .orElseThrow(KodyfykatorIllegalException::new).getName());

                s.setRegion(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .filter(e -> e.getType().equals(SettleTypes.REGION.getName())).findFirst()
                        .orElseThrow(KodyfykatorIllegalException::new).getName());

                s.setArea(Util.SETTLES.stream().filter(e -> e.getArea().equals(entity.getArea()))
                        .filter(e -> e.getType().equals(SettleTypes.AREA.getName())).findFirst()
                        .orElseThrow(KodyfykatorIllegalException::new).getName());

                s.setType(Util.SETTLE_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            } else if (!entity.getRegion().isEmpty() && entity.getType().equals(SettleTypes.SPECIAL_CITY.getName())) {
                // cities with special status
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .findFirst().orElseThrow(KodyfykatorIllegalException::new).getName());

                s.setType(Util.SETTLE_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            }
        }

        // sort by types, then by name
        preparedForJSON.sort(Comparator.comparing(SettlementEntity::getType).thenComparing(SettlementEntity::getSettlement));

        return getJSONArrayAsString(preparedForJSON);
    }

    /***
     * @param preparedForJSON list
     * @return String representation of JSON array
     */
    private String getJSONArrayAsString(List<SettlementEntity> preparedForJSON) {
        var jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(preparedForJSON);
        } catch (JsonProcessingException e) {
            log.error("[!] {}", e.getMessage());
        }
        return new JSONArray(jsonString).toString();
    }
}
