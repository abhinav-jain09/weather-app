package com.assignment.spring;


import org.junit.After;
import org.junit.Before;
import org.mockserver.integration.ClientAndServer;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class BaseTest {
    private static ClientAndServer mockServer;


    @Before
    public void setupMockServer() throws IOException, JAXBException {
        mockServer = mockServer.startClientAndServer(1080);

        Expectations.createDefaultExpectations(mockServer);
    }

    @After
    public void after() {
        mockServer.stop();
    }
}
