package com.learningpulse.user.user_data;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
@Table(name = "user_data", schema = "user_data")
public class UserData implements Serializable, Persistable<UUID> {
    @Id
    private UUID id;

    private UUID userId;

    private UUID profilePicture;

    // TODO figure out if this actually works
    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public boolean isNew() {
        if (id == null) {
            // HACK TODO this is a hack to get around the fact that the R2DBC auditing doesn't work with UUIDs
            // The correct way:
            // https://stackoverflow.com/questions/72113360/spring-hibernate-generate-uuid-automatically-for-id-column
            this.id = UUID.randomUUID();
            return true;
        }
        return false;
    }
}
