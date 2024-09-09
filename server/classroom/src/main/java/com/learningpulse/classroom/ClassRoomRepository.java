package com.learningpulse.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassRoomRepository extends JpaRepository<Classroom, UUID> {

}