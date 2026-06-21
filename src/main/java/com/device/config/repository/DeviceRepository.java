package com.device.config.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.device.config.model.Device;

public interface DeviceRepository extends JpaRepository<Device, UUID> {

    Optional<Device> findBySerialNumber(String serialNumber);
} 