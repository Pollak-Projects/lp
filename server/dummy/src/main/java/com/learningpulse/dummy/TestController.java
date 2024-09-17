package com.learningpulse.dummy;

import com.learningpulse.dummy.services.GreetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dummy")
@RequiredArgsConstructor
public class TestController {

    private final GreetingService greetingService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello, World!");
    }

    @GetMapping("/greeting")
    public @ResponseBody String greeting() {
        return greetingService.greet();
    }
}
