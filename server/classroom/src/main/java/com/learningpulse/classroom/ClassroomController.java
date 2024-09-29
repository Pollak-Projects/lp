package com.learningpulse.classroom;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
public class ClassroomController {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);
    private final ClassroomService ClassroomService;

    @GetMapping
    public ResponseEntity<List<Classroom>> findAll() {
        // TODO add error handling if Classrooms are nonexistent
        return ResponseEntity.ok(ClassroomService.findAll());
    }

    @GetMapping("/q")
    public ResponseEntity<Classroom> findById(@RequestParam("id") UUID uuid) {
        // TODO add error handling if Classroom is nonexistent
        return ResponseEntity.ok(ClassroomService.findByID(uuid));
    }

    @PostMapping
    public ResponseEntity<Classroom> createNewClassroom(@RequestBody ClassroomCreateRequest classroom_model,
                                                        @RequestHeader("Authorization") String token) {
        // TODO add a middleware implementation to this instead of doing it here
        // FIXME errors out due to not being base64? Idk
        DecodedJWT decodedJWT = JWT.decode(token);
        logger.debug(String.format("Create:/token: %s", decodedJWT.getId()));
        Classroom classroom = ClassroomService.createClassroom(classroom_model.getName());
        return ResponseEntity.ok(classroom);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestParam("user_id") UUID uuid, @RequestParam("classroom") UUID classroom) {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateClassroom(@RequestBody Classroom Classroom) {
        ClassroomService.updateClassroom(Classroom);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteClassroom(@RequestParam("id") UUID id) {
        ClassroomService.delete(id);
        return ResponseEntity.ok().build();
    }

}