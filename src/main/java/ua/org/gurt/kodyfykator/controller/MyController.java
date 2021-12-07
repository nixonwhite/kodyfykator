package ua.org.gurt.kodyfykator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.gurt.kodyfykator.domain.SettlementEntity;
import ua.org.gurt.kodyfykator.util.Codes;
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

        List<SettlementEntity> filteredCities = Codes.settles.stream().filter(e -> e.getName().toLowerCase()
                .startsWith(name.toLowerCase())).collect(Collectors.toList());

        List<SettlementEntity> preparedForJSON = new ArrayList<>();

        for (SettlementEntity entity : filteredCities) {
            if (entity.getType().equals("B")) continue; //area in city
            SettlementEntity s = new SettlementEntity();
            if (!entity.getSettlement().isEmpty()) {
                s.setSettlement(Codes.settles.stream().filter(e -> e.getSettlement().equals(entity.getSettlement()))
                        .filter(e -> e.getType().equals(entity.getType())).collect(Collectors.toList()).get(0).getName());

                s.setRegion(Codes.settles.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .filter(e -> e.getType().equals("O")).collect(Collectors.toList()).get(0).getName());

                s.setArea(Codes.settles.stream().filter(e -> e.getArea().equals(entity.getArea()))
                        .filter(e -> e.getType().equals("P")).collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPE.get(entity.getType()));
                preparedForJSON.add(s);
            } else if (!entity.getRegion().isEmpty() && entity.getType().equals("K")) {
                s.setSettlement(Codes.settles.stream().filter(e -> e.getRegion().equals(entity.getRegion()))
                        .collect(Collectors.toList()).get(0).getName());

                s.setType(Util.OBJECT_TYPE.get(entity.getType()));
                preparedForJSON.add(s);
            }
        }

        // Sort them all
        preparedForJSON.sort(Comparator.comparing(SettlementEntity::getType).thenComparing(SettlementEntity::getSettlement));

        // Return values as String JSON array
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
