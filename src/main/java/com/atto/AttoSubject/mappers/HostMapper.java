package com.atto.AttoSubject.mappers;

import com.atto.AttoSubject.dtos.HostDto;
import com.atto.AttoSubject.dtos.HostRegisterRequest;
import com.atto.AttoSubject.dtos.HostRegisterResponse;
import com.atto.AttoSubject.entities.Host;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface HostMapper {
    Host map(HostDto host);
    HostRegisterResponse map(Host host);
    Host map(HostRegisterRequest host);
    HostDto toDto(Host host);
}
