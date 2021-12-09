package ua.org.gurt.kodyfykator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;
import ua.org.gurt.kodyfykator.util.Util;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class MyController {

    @GetMapping("version")
    public String version() {
        return "v1.0";
    }

    @PostMapping("find/{name}")
    public String findByName(@PathVariable String name) {

        List<SettlementEntity> filteredCities = Util.SETTLES.stream().filter(e -> e.getName().toLowerCase()
                .startsWith(name.toLowerCase())).collect(Collectors.toList());

        List<SettlementEntity> preparedForJSON = new ArrayList<>();

        for (SettlementEntity entity : filteredCities) {
            SettlementEntity s = new SettlementEntity();
            if (entity.getType().equals("B")) continue; //area in city
            if (!entity.getSettlement().isEmpty()) {
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getSettlement().equals(entity.getSettlement()))
                        .filter(e -> e.getType().equals(entity.getType())).collect(Collectors.toList()).get(0).getName());

                s.setRegion(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .filter(e -> e.getType().equals("O")).collect(Collectors.toList()).get(0).getName());

                s.setArea(Util.SETTLES.stream().filter(e -> e.getArea().equals(entity.getArea()))
                        .filter(e -> e.getType().equals("P")).collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            } else if (!entity.getRegion().isEmpty() && entity.getType().equals("K")) {
                s.setSettlement(Util.SETTLES.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPES.get(entity.getType()));
                preparedForJSON.add(s);
            }
        }

        preparedForJSON.sort(Comparator.comparing(SettlementEntity::getType).thenComparing(SettlementEntity::getSettlement));
        return getJSONArrayAsString(preparedForJSON);
    }

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
