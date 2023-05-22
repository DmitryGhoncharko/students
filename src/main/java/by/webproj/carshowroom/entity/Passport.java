package by.webproj.carshowroom.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
@Builder
public class Passport {
    private Long id;
    private String customer;
    private String org;
    private String ruk;
    private String gen;
    private String tex;
    private String ots;
    private Long userId;
}
