package com.device.config.service;

import com.device.config.model.Device;
import com.device.config.model.DeviceType;
import com.device.config.repository.DeviceRepository;
import com.device.config.repository.DeviceTypeRepository;
import com.device.config.validator.JsonSchemaValidator; 
import com.device.config.exception.InvalidConfigException;
import com.device.config.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;
    private final JsonSchemaValidator jsonSchemaValidator; 

    // Constructor Injection for core dependencies
    public DeviceService(
            DeviceRepository deviceRepository, 
            DeviceTypeRepository deviceTypeRepository,
            JsonSchemaValidator jsonSchemaValidator) {
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
        this.jsonSchemaValidator = jsonSchemaValidator;
    }

    /**
     * 1. Fix: Provisions and creates a brand new hardware device entity mapping.
     */
    @Transactional
    public Device createDevice(String serialNumber, UUID deviceTypeId) {
        // Guard check for unique serial constraints
        if (deviceRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new InvalidConfigException("Device with serial number " + serialNumber + " already registered.");
        }

        // Fetch target device type schema template rules
        DeviceType deviceType = deviceTypeRepository.findById(deviceTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("DeviceType not found for ID: " + deviceTypeId));

        Device newDevice = new Device();
        newDevice.setSerialNumber(serialNumber);
        newDevice.setDeviceType(deviceType);

        return deviceRepository.save(newDevice);
    }

    /**
     * Secure operational read lookup.
     */
    @Transactional(readOnly = true)
    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    /**
     * Operational read lookup using physical hardware tag.
     */
    @Transactional(readOnly = true)
    public Optional<Device> getDeviceBySerial(String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }

    /**
     * Fetch every registered system hardware entry point.
     */
    @Transactional(readOnly = true)
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    
    @Transactional(readOnly = true)
    public void validateNewConfiguration(String serialNumber, String incomingJsonConfig) {
        Device device = deviceRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found for serial: " + serialNumber));

        // Pull down the active JSON schema from the parent Device Type rule profile
        String schemaBlueprint = device.getDeviceType().getSchemaJson();

        // 3. Fix: Invoke the active Networknt engine instance directly
        jsonSchemaValidator.validate(incomingJsonConfig, schemaBlueprint);
    }
}
