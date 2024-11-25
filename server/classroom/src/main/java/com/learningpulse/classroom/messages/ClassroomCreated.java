package com.learningpulse.classroom.messages;

import java.sql.Timestamp;
import java.util.UUID;

import com.learningpulse.classroom.entity.Classroom;

public class ClassroomCreated {
    private UUID id;
    private String name;
    private Timestamp createdAt;
    private String joinCode;
    private UUID createdBy;

    public ClassroomCreated(Classroom classroom) {
        this.id = classroom.getId();
        this.name = classroom.getName();
        this.createdAt = classroom.getCreatedAt();
        this.joinCode = classroom.getJoinCode();
        this.createdBy = classroom.getCreatedBy();
    }
}
