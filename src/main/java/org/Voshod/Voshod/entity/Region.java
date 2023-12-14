package org.Voshod.Voshod.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name  = "region_model", indexes = {
        @Index(name = "region_model_id_index", columnList = "id", unique = true),
        @Index(name = "region_model_login_index", columnList = "login"),
        @Index(name = "region_model_password_index", columnList = "password"),
        @Index(name = "region_model_name_index", columnList = "name"),
        @Index(name = "region_model_secondName_index", columnList = "secondName"),
        @Index(name = "region_model_age_index", columnList = "dateOfBirth"),
        @Index(name = "region_model_gender_index", columnList = "houseNumber"),
        @Index(name = "region_model_series_index", columnList = "seriesPassport"),
        @Index(name = "region_model_number_index", columnList = "numberPassport"),
        @Index(name = "region_model_telephone_index", columnList = "telephone"),
        @Index(name = "region_model_about_index", columnList = "mail")
//        @Index(name = "region_model_selectParam_index", columnList = "selectParam"),
//        @Index(name = "region_model_pattern_index", columnList = "pattern"),
//        @Index(name = "region_model_titlePattern_index", columnList = "titlePattern")
})

public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String login, password;

    @Getter
    @Setter
    private String name, secondName;

    @Getter
    @Setter
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date dateOfBirth;

    @Getter
    @Setter
    private String houseNumber, seriesPassport, numberPassport, telephone, mail, er, foundError, pattern, titlePattern;

}
