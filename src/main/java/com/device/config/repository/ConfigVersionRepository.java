package com.device.config.repository;

import com.device.config.model.ConfigVersion;
import com.device.config.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface ConfigVersionRepository extends JpaRepository<ConfigVersion, UUID> {
    Optional<ConfigVersion> findTopByDeviceOrderByVersionDesc(Device device);
}
