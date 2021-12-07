package ua.org.gurt.kodyfykator.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SettlementEntity {

    private String region;
    private String area;
    private String territory;
    private String settlement;
    private String city_area;
    private String type;
    private String name;
}
