package com.learningpulse.dummy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebFluxTest
public class DummyApplicationSmokeTest {

    @MockBean
    private TestController testController;

    @MockBean
    private DummyApplication dummyApplication;

    @Test
    void TestControllerExist() {
        assertThat(testController).isNotNull();
    }

    @Test
    void DummyApplicationExist() {
        assertThat(dummyApplication).isNotNull();
    }
}
