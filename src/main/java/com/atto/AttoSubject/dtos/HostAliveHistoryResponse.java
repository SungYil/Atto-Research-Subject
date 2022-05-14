package com.atto.AttoSubject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HostAliveHistoryResponse {
    private long hostId;
    private String ip;
    private String name;
    private String state;
    private String checkTime;
}
