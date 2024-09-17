package com.learningpulse.dummy.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingServiceTest {

    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        greetingService = new GreetingService();
    }

    @Test
    void whenGreet_thenReturnGreet() {
        assertEquals("I work!", greetingService.greet());
    }
}
