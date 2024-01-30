package ua.org.gurt.kodyfykator.domain;

import lombok.*;

@Data
@NoArgsConstructor
public class SettlementEntity {

    private String region;
    private String area;
    private String territory;
    private String settlement;
    private String city_area;
    private String type;
    private String name;
}
