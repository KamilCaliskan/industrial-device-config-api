package com.device.config.repository;

import com.device.config.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, UUID> {
}