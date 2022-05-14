package com.atto.AttoSubject.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Host extends BaseEntity{
    private String ip;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
