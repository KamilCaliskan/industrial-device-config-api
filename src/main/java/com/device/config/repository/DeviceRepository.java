package com.device.config.repository;

import com.device.config.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findBySerialNumber(String serialNumber);
}
