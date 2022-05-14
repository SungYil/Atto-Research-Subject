package com.atto.AttoSubject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HostRegisterResponse {
    private long id;
    private String ip;
    private String name;
    private String createdAt;
    private String updatedAt;
}
