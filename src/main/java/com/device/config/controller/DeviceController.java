package com.device.config.controller;

import com.device.config.dto.DeviceRequest;
import com.device.config.model.Device;
import com.device.config.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Device create(@RequestBody DeviceRequest request) {
        return deviceService.createDevice(
                request.getSerialNumber(),
                request.getDeviceTypeId()
        );
    }

    @GetMapping("/{id}")
    public Device getById(@PathVariable UUID id) {
        return deviceService.getDeviceById(id);
    }

    @GetMapping
    public List<Device> getAll() {
        return deviceService.getAllDevices();
    }
}
