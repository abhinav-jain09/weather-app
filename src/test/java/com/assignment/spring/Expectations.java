package com.assignment.spring;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;

import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class Expectations {


    public static void createDefaultExpectations(ClientAndServer mockServer) {

        // GET
        createSucessfulResponseExpectation(mockServer);

        // GET
        createInvalidCityNameExpectation(mockServer);

    }

    private static void createSucessfulResponseExpectation(ClientAndServer mockServer) {

        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/weather")
                                .withQueryStringParameter("city", "Amsterdam"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"),
                                        new Header("Cache-Control", "public, max-age=86400"))
                                .withBody(getBody())
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    private static void createInvalidCityNameExpectation(ClientAndServer mockServer) {
        mockServer
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/weather")
                                .withQueryStringParameter("city", "nocity"),
                        exactly(1))
                .respond(
                        response()
                                .withStatusCode(404)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"),
                                        new Header("Cache-Control", "public, max-age=86400"))
                                .withBody(getCityNotFoundResponse())
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }

    private static byte[] getBody() {
        String jsonResponse = "{\n" +
                "    \"coord\": {\n" +
                "        \"lon\": 4.89,\n" +
                "        \"lat\": 52.37\n" +
                "    },\n" +
                "    \"weather\": [\n" +
                "        {\n" +
                "            \"id\": 310,\n" +
                "            \"main\": \"Drizzle\",\n" +
                "            \"description\": \"light intensity drizzle rain\",\n" +
                "            \"icon\": \"09d\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"base\": \"stations\",\n" +
                "    \"main\": {\n" +
                "        \"temp\": 286.24,\n" +
                "        \"feels_like\": 281.67,\n" +
                "        \"temp_min\": 285.93,\n" +
                "        \"temp_max\": 287.04,\n" +
                "        \"pressure\": 1009,\n" +
                "        \"humidity\": 76\n" +
                "    },\n" +
                "    \"visibility\": 10000,\n" +
                "    \"wind\": {\n" +
                "        \"speed\": 6.2,\n" +
                "        \"deg\": 250\n" +
                "    },\n" +
                "    \"clouds\": {\n" +
                "        \"all\": 75\n" +
                "    },\n" +
                "    \"dt\": 1591552704,\n" +
                "    \"sys\": {\n" +
                "        \"type\": 1,\n" +
                "        \"id\": 1524,\n" +
                "        \"country\": \"NL\",\n" +
                "        \"sunrise\": 1591500016,\n" +
                "        \"sunset\": 1591559925\n" +
                "    },\n" +
                "    \"timezone\": 7200,\n" +
                "    \"id\": 2759794,\n" +
                "    \"name\": \"Amsterdam\",\n" +
                "    \"cod\": 200\n" +
                "}";

        return jsonResponse.getBytes();
    }

    private static byte[] getCityNotFoundResponse() {

        String jsonResponse = "{\n" +
                "    \"cod\": \"404\",\n" +
                "    \"message\": \"city not found\"\n" +
                "}";
        return jsonResponse.getBytes();

    }
}
