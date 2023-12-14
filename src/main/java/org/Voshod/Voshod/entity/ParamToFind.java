package org.Voshod.Voshod.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class ParamToFind extends Region {

    @Getter
    @Setter
    private String name, secondName, login, houseNumber, telephone, mail;

    @Getter
    @Setter
    private String selectParam, pattern, titlePattern, fUser;

}
