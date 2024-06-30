package ru.aao.consumerservice.common.types.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String name;
    private DateTime birthday;
    private Integer sex;
}
