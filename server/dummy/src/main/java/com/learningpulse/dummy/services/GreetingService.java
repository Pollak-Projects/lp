package com.learningpulse.dummy.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class GreetingService {
        public String greet() {
            return "I work!";
        }
}
