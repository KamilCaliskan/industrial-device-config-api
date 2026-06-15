package com.device.config.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class CreateConfigVersionRequest {
    private JsonNode configData;
}