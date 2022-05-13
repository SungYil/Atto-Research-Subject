package com.atto.AttoSubject.entities;

import com.atto.AttoSubject.enums.AliveState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Entity
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Alive extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "ip")
    private Host host;
    @Enumerated(EnumType.STRING)
    private AliveState state;
    private ZonedDateTime checkTime;
}
