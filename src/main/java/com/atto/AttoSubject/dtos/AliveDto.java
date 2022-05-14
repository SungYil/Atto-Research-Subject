package com.atto.AttoSubject.dtos;

import com.atto.AttoSubject.enums.AliveState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AliveDto {
    private AliveState aliveState;
    private ZonedDateTime checkTime;
}
