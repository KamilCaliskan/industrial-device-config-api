package com.device.config.service;

import com.device.config.dto.DeviceTypeRequest;
import com.device.config.entity.DeviceType; // Assuming standard entity packaging
import com.device.config.repository.DeviceTypeRepository; // Assuming standard repository packaging
import com.device.config.exception.InvalidJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeService {

    private final ObjectMapper objectMapper;
    private final DeviceTypeRepository repository;

    // Constructor injection for both fields
    public DeviceTypeService(ObjectMapper objectMapper, DeviceTypeRepository repository) {
        this.objectMapper = objectMapper;
        this.repository = repository;
    }

    public DeviceType create(DeviceTypeRequest request) {
        // 1. Validate that schemaJson is valid JSON
        try {
            this.objectMapper.readTree(request.getSchemaJson());
        } catch (JsonProcessingException e) {
            throw new InvalidJsonException("Invalid JSON format");
        }

        // 2. Create new DeviceType entity and map fields
        DeviceType deviceType = new DeviceType();
        deviceType.setName(request.getName());
        deviceType.setSchemaJson(request.getSchemaJson());

        // 3. Save via repository and return the entity
        return this.repository.save(deviceType);
    }

    public List<DeviceType> getAll() {
        return this.repository.findAll();
    }
}
