package com.atto.AttoSubject.dtos;

import com.atto.AttoSubject.enums.AliveState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HostAliveHistoryResponse {
    private long hostId;
    private String ip;
    private String name;
    private AliveState state;
    private ZonedDateTime checkTime;
}
