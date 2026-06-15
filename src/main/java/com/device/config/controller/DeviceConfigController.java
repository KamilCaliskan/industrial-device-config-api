package com.device.config.controller;

import com.device.config.model.ConfigVersion;
import com.device.config.dto.CreateConfigVersionRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
public class DeviceConfigController {

    @PostMapping("/{deviceId}/config-versions")
    @ResponseStatus(HttpStatus.CREATED)
    public ConfigVersion create(
            @PathVariable UUID deviceId,
            @RequestBody CreateConfigVersionRequest request) {
        
        return null;
    }
}