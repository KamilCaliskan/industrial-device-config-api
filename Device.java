package com.device.confige.model;

import jakarta.persistence.*;
import java.time.Instant;
import jav.util.UUID;


@Entity
@Table(name = "device")
public class  Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private DeviceType deviceType;

    private Instant createdAt = Instant.now();


}
