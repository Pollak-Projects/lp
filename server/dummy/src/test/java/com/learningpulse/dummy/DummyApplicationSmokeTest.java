package com.learningpulse.dummy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DummyApplicationSmokeTest {

    @Autowired
    private TestController testController;

    @Autowired
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
