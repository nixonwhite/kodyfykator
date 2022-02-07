package ua.org.gurt.kodyfykator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;
import ua.org.gurt.kodyfykator.util.Util;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyController {

    @Autowired
    BuildProperties buildProperties;

    /***
     *
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
    public String findByName(@PathVariable String name) {

        List<SettlementEntity> filteredCities = Util.SETTLES.stream().filter(e -> e.getName().toLowerCase()
                .startsWith(name.toLowerCase())).collect(Collectors.toList());

        List<SettlementEntity> preparedForJSON = new ArrayList<>();

        for (SettlementEntity entity : filteredCities) {
            if (entity.getType().equals("B")) continue; //area in city; we don't care about it

            SettlementEntity s = new SettlementEntity();
            if (!entity.getSettlement().isEmpty()) {
                // settlements
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getSettlement().equals(entity.getSettlement()))
                        .filter(e -> e.getType().equals(entity.getType())).collect(Collectors.toList()).get(0).getName());

                s.setRegion(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .filter(e -> e.getType().equals("O")).collect(Collectors.toList()).get(0).getName());

                s.setArea(Util.SETTLES.stream().filter(e -> e.getArea().equals(entity.getArea()))
                        .filter(e -> e.getType().equals("P")).collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            } else if (!entity.getRegion().isEmpty() && entity.getType().equals("K")) {
                // cities with special status
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            }
        }

        // sort by types, then by name
        preparedForJSON.sort(Comparator.comparing(SettlementEntity::getType).thenComparing(SettlementEntity::getSettlement));

        return getJSONArrayAsString(preparedForJSON);
    }

    /***
     *
     * @param preparedForJSON list
     * @return String representation of JSON array
     */
    private String getJSONArrayAsString(List<SettlementEntity> preparedForJSON) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ObjectMapper mapper = new ObjectMapper();
        ListIterator<SettlementEntity> iter = preparedForJSON.listIterator();
        while (iter.hasNext()) {
            try {
                sb.append(mapper.writeValueAsString(iter.next()));
                if (iter.hasNext()) sb.append(",");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
