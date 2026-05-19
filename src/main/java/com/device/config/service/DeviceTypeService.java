package com.device.config.service;

import com.device.config.dto.DeviceTypeRequest;
import com.device.config.model.DeviceType;
import com.device.config.repository.DeviceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceTypeService {
    private final DeviceTypeRepository repository;

    public DeviceType create(DeviceTypeRequest request) {
        DeviceType dt = new DeviceType();
        dt.setName(request.getName());
        dt.setSchemaJson(request.getSchemaJson());
        return repository.save(dt);
    }
}