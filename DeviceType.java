package com.device.config.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "device_type")
public class DeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

   @Column(columnDefinition = "jsonb")
   private String schemaJson;
 
   @Column(nullable = false, updatable = false)
   private Instant ceratedAt = ınstant.now();
   
   @Column(nullable = false, updatable = false)
   private Instant createdAt = Instant.now();


}
