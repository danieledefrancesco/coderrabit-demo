package com.tuimm.learningpath.trips.dal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "trips")
public class TripEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    @ElementCollection(targetClass = StagePlanEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Set<StagePlanEntity> stages;
}
