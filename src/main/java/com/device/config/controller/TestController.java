package com.device.config.controller;

import com.device.config.model.DeviceType;
import com.device.config.repository.DeviceTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TestController {

    private final DeviceTypeRepository deviceTypeRepository;

    public TestController(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @GetMapping("/test/device-types")
    public List<DeviceType> getAllDeviceTypes() {
        return deviceTypeRepository.findAll();
    }
}