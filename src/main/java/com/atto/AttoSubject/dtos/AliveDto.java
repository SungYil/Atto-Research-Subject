package com.atto.AttoSubject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AliveDto {
    private Enum AliveState;
    private ZonedDateTime checkTime;
}
