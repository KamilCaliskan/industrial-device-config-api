package com.device.config.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "config_version", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"device_id", "version"})
})
public class ConfigVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private Integer version = 1;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String configData;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public enum Status {
        DRAFT, APPROVED, ACTIVE
    }
}
