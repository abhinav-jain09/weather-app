package com.assignment.spring.services;

import com.assignment.spring.Exceptions.NotValidInput;
import com.assignment.spring.dto.WeatherData;
import com.assignment.spring.entity.Weather;
import com.assignment.spring.mapper.WeatherResponseMapper;
import com.assignment.spring.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherDataProcessing {
    private static final String APPID = "&APPID=";

    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
   private final static Logger logger = LoggerFactory.getLogger(WeatherDataProcessing.class);
    @Value("${baseurl.external.api.call}")
    private String baseUrl;
    @Value("${api.id}")
    private String appid;

    public Weather mapWeatherData(String city) {
        ResponseEntity<WeatherData> response = callToExternalService(city);
        Weather entity = WeatherResponseMapper.mapper.apply(response.getBody());
        weatherRepository.save(entity);
        return entity;
    }

    private ResponseEntity<WeatherData> callToExternalService(String city) {
        String url = baseUrl + city + APPID + appid;
        logger.debug("calling external url " + url);
        try {
            ResponseEntity<WeatherData> response = restTemplate.getForEntity(url, WeatherData.class);
            logger.info("external api call successful");
            return response;
        } catch (HttpStatusCodeException exception) {
            logger.info("external api call not successful");
            throw new NotValidInput(exception.getResponseBodyAsString());
        }
    }

}
