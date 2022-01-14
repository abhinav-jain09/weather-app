
package com.assignment.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class WeatherData {
    private Main main;
    private Sys sys;
    private String name;
}
