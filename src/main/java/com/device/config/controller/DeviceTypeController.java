package com.device.config.controller;

import com.device.config.dto.DeviceTypeRequest;
import com.device.config.model.DeviceType;
import com.device.config.service.DeviceTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device-types")
@RequiredArgsConstructor
public class DeviceTypeController {

    private final DeviceTypeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceType create(@Valid @RequestBody DeviceTypeRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<DeviceType> getAll() {
        return service.getAll();
    }
}
