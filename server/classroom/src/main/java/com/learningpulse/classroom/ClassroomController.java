package com.learningpulse.classroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/classroom")
public class ClassroomController {
    private final ClassroomService ClassroomService;
    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);

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
            String token) {
        logger.debug(String.format("Create:/token: %s", token));
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