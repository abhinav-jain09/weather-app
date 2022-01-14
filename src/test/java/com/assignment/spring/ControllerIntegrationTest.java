package com.assignment.spring;

import com.assignment.spring.Exceptions.NotValidInput;
import com.assignment.spring.entity.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"apptest"})
public class ControllerIntegrationTest extends BaseTest {
    //   @LocalServerPort
    private static final int localServerPort = 1080;
    private static final String localhost = "http://127.0.0.1:";
    private static final String context = "/weather?city=";
    @Autowired
    RestTemplate restTemplate;


    @Test
    public void WeatherControllerTestIT() throws Exception {
        ResponseEntity<Weather> forEntity = restTemplate.getForEntity(localhost + localServerPort + context + "Amsterdam", Weather.class);
        System.out.println(forEntity.getBody());
    }

    @Test(expected = NotValidInput.class)
    public void WeatherControllerNotValidResponseTestIT() throws Exception {
        try {
            ResponseEntity<Weather> forEntity = restTemplate.getForEntity(localhost + localServerPort + context + "nocity", Weather.class);


        } catch (HttpStatusCodeException exception) {

            throw new NotValidInput(exception.getMessage());
        }

    }
}
