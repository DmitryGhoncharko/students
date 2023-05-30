package by.webproj.carshowroom.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
@Getter
public class Student {
    private Long id;
    private String name;
    private String group;
    private Integer mark;
}
