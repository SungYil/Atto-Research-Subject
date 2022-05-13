package com.atto.AttoSubject.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
    private Enum state;
    private ZonedDateTime checkTime;
}
