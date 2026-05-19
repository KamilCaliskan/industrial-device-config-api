package com.device.config.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceTypeRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String schemaJson; // JSON schema as string
}