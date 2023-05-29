package by.webproj.carshowroom.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Builder
@EqualsAndHashCode
@ToString
@Getter
public class Org {
    private Long id;
    private String name;
    private String description;
    private Date date;

    private Long userId;
}
