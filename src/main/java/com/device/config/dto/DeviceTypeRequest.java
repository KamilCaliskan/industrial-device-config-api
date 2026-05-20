package com.device.config.dto;

import jakarta.validation.constraints.NotBlank;

public class DeviceTypeRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String schemaJson;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSchemaJson() { return schemaJson; }
    public void setSchemaJson(String schemaJson) { this.schemaJson = schemaJson; }
}