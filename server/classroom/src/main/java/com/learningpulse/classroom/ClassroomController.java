package com.learningpulse.classroom;

import com.learningpulse.classroom.config.KeycloakJwt;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.springframework.data.domain.Example;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
public class ClassroomController {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);
    private final ClassroomRepository ClassroomService;
    private final Random random = new Random();

    @PostMapping
    public ResponseEntity<Classroom> createNewClassroom(@RequestBody ClassroomCreateRequest classroom_model,
            @AuthenticationPrincipal KeycloakJwt jwt) {
        // TODO emmit AMQP ClassroomCreated Event
        logger.info("CreatedBy:" + jwt.getSub());
        String joinCode = generateJoinCode(4);
        new Classroom();
        Classroom classroom = Classroom.builder().joinCode(joinCode).name(classroom_model.getName())
                .createdBy(jwt.getSub()).build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("join_code", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Classroom> classroomExample = Example.of(classroom, matcher);

        while (ClassroomService.exists(classroomExample)) {
            joinCode = generateJoinCode(4);
            classroom.setJoinCode(joinCode);
        }

        // Open a transaction
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("classroom");
        EntityManager em = emf.createEntityManager();
        // create member
        ClassroomMember initmember = new ClassroomMember();
        initmember.setClassroom_id(classroom);
        initmember.setUser_id(jwt.getSub());

        classroom.getMembers().add(initmember);
        em.persist(classroom);
        em.persist(initmember);
        em.getTransaction().commit();
        em.close();
        emf.close();

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