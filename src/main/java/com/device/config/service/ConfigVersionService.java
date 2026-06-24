package com.device.config.service;

import com.device.config.exception.ResourceNotFoundException;
import com.device.config.model.ConfigVersion;
import com.device.config.model.Device;
import com.device.config.repository.ConfigVersionRepository;
import com.device.config.repository.DeviceRepository;
import com.device.config.validator.JsonSchemaValidator;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ConfigVersionService {

    private final DeviceRepository deviceRepository;
    private final JsonSchemaValidator jsonSchemaValidator;
    private final ConfigVersionRepository configVersionRepository;

    public ConfigVersionService(
            DeviceRepository deviceRepository,
            JsonSchemaValidator jsonSchemaValidator,
            ConfigVersionRepository configVersionRepository) {

        this.deviceRepository = deviceRepository;
        this.jsonSchemaValidator = jsonSchemaValidator;
        this.configVersionRepository = configVersionRepository;
    }

    @Transactional
    public ConfigVersion createConfigVersion(
            UUID deviceId,
            JsonNode configData) {

        // Step 1: Find device
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Device not found with id: " + deviceId));

        // Step 2: Load schema
        String schema = device.getDeviceType().getSchemaJson();

        // Step 3: Validate configuration
        jsonSchemaValidator.validate(configData, schema);

        // Step 4: Calculate next version number
        int nextVersion = configVersionRepository
                .findTopByDeviceOrderByVersionDesc(device)
                .map(ConfigVersion::getVersion)
                .orElse(0) + 1;

        // Step 5: Create new config version
        ConfigVersion configVersion = new ConfigVersion();

        configVersion.setDevice(device);
        configVersion.setVersion(nextVersion);
        configVersion.setConfigData(configData);
        configVersion.setStatus("DRAFT");

        // Step 6: Save and return
        return configVersionRepository.save(configVersion);
    }
}