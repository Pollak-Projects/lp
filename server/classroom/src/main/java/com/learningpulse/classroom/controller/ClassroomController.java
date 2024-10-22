package com.learningpulse.classroom.controller;

import com.learningpulse.classroom.ClassroomCreateRequest;
import com.learningpulse.classroom.entity.Classroom;
import com.learningpulse.classroom.entity.Member;
import com.learningpulse.classroom.repository.ClassroomRepository;
import com.learningpulse.classroom.util.KeycloakJwt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

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
    private final EntityManagerFactory entityManagerFactory;
    private final ClassroomRepository ClassroomRepository;

    @PostMapping
    public ResponseEntity<Classroom> createNewClassroom(@RequestBody ClassroomCreateRequest classroom_model,
            @AuthenticationPrincipal KeycloakJwt jwt) {
        // TODO emmit AMQP ClassroomCreated Event
        logger.info("CreatedBy:" + jwt.getSub());
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

        // Open a transaction
        EntityManager em = entityManagerFactory.createEntityManager();

        Member initmember = new Member();
        initmember.setClassroom_id(classroom);
        initmember.setUser_id(jwt.getSub());

        classroom.getMembers().add(initmember);
        em.persist(classroom);
        em.persist(initmember);
        em.getTransaction().commit();
        em.close();

        // TODO transactional join/create for atomic
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