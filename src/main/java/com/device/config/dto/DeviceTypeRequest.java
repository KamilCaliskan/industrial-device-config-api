package com.device.config.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceTypeRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "schemaJson is required")
    private String schemaJson;

    
}