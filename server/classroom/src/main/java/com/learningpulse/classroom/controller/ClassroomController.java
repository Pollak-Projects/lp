package com.learningpulse.classroom.controller;

import com.learningpulse.classroom.ClassroomCreateRequest;
import com.learningpulse.classroom.config.RabbitConfig;
import com.learningpulse.classroom.entity.Classroom;
import com.learningpulse.classroom.messages.ClassroomCreated;
import com.learningpulse.classroom.repository.ClassroomRepository;
import com.learningpulse.classroom.util.KeycloakJwt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Example;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
public class ClassroomController {

    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);
    private final Random random = new Random();

    private final RabbitTemplate rabbitTemplate;
    private final ClassroomRepository ClassroomRepository;

    @PostMapping
    public ResponseEntity<Classroom> createNewClassroom(@RequestBody ClassroomCreateRequest classroom_model,
            @AuthenticationPrincipal KeycloakJwt jwt) {
        logger.info("CreatedBy:" + jwt.getSub());
        // TODO a better implementation of this
        String joinCode = generateJoinCode(4);
        Classroom classroom = Classroom.builder().joinCode(joinCode).name(classroom_model.getName())
                .createdBy(jwt.getSub()).build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("join_code", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Classroom> classroomExample = Example.of(classroom, matcher);

        while (ClassroomRepository.exists(classroomExample)) {
            joinCode = generateJoinCode(4);
            classroom.setJoinCode(joinCode);
        }

        // TODO send message createChat where a chat is created and creator is included
        classroom.addMember(jwt.getSub());
        ClassroomRepository.save(classroom);

        return ResponseEntity.ok(classroom);
    }

    private String generateJoinCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        return result.toString().toLowerCase();

    }

}