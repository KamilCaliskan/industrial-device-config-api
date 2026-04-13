package com.device.config.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "config_version")
public class configVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @joinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String configData;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;
   
    private Instant createdAt = Instant.now();
    
    public enum Status {
        DRAFT, APPROVED, ACTİVE
   
  }
}
