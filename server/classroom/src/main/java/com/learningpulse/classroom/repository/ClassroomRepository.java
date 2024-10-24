package com.learningpulse.classroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.learningpulse.classroom.entity.Classroom;
import java.util.UUID;

public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    Classroom findByJoinCode(String joinCode);
}