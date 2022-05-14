package com.atto.AttoSubject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HostDto {
    private String id;
    private String ip;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
