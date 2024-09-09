package com.learningpulse.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassroomService {
    private final ClassRoomRepository repo;

    public List<Classroom> findAll() {
        return repo.findAll();
    }

    public void createClassroom(String name) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            result.append(characters.charAt(index));
        }
        String joinCode = result.toString();
        Classroom classroom = Classroom.builder()
                .joinCode(joinCode)
                .build();
        repo.save(classroom);
    }

    public void updateClassroom(Classroom Classroom_data) {
        repo.findById(Classroom_data.getId())
                .orElseThrow(() -> new RuntimeException("No Classroom under this id: " + Classroom_data.getId()));
        repo.save(Classroom_data);
    }

    public Classroom findByID(UUID uuid) {
        // FIXME handle unwrap errors
        return repo.findById(uuid).get();
    }

    public void delete(UUID id) {
        // FIXME handle errors
        repo.findById(id);
    }
}