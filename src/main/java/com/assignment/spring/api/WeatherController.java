package com.assignment.spring.api;

import com.assignment.spring.entity.Response;
import com.assignment.spring.entity.Weather;
import com.assignment.spring.services.WeatherDataProcessing;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherController {
    Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private final WeatherDataProcessing weatherDataProcessing;


    @ApiOperation(value = "REST API for weather", tags = {"Click here to call the API"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Weather.class)})
    @GetMapping("/weather")
    @ResponseBody
    public Response weather(@RequestParam("city") String city) {
        logger.info("recieved request for city " + city);
        return weatherDataProcessing.mapWeatherData(city);
    }
}
