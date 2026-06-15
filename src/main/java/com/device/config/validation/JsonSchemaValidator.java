package com.device.config.validation;

import com.device.config.exception.InvalidConfigException; 
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class JsonSchemaValidator {

    public void validate(JsonNode configData, String schemaJson) {
        // Step 1: Create schema factory
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        
        // Step 2: Build JsonSchema object from schemaJson string
        JsonSchema schema = factory.getSchema(schemaJson);
        
        // Step 3: Validate configData against schema
        Set<ValidationMessage> errors = schema.validate(configData);
        
        // Step 4: If errors exist, throw exception with first error message
        if (!errors.isEmpty()) {
            String firstError = errors.iterator().next().getMessage();
            throw new InvalidConfigException(firstError);
        }
    }
}