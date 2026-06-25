package com.device.config.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.UUID;

@Data
public class DeviceRequest {
    @NotBlank
    private String serialNumber;

    @NotNull
    private UUID deviceTypeId;
}
