package com.device.config.service;

import com.device.config.exception.ResourceNotFoundException;
import com.device.config.model.Device;
import com.device.config.model.DeviceType;
import com.device.config.repository.DeviceRepository;
import com.device.config.repository.DeviceTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceTypeRepository deviceTypeRepository;

    public DeviceService(DeviceRepository deviceRepository, DeviceTypeRepository deviceTypeRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Transactional
    public Device createDevice(String serialNumber, UUID deviceTypeId) {
        if (deviceRepository.findBySerialNumber(serialNumber).isPresent()) {
            throw new IllegalArgumentException("Serial number already exists: " + serialNumber);
        }

        DeviceType deviceType = deviceTypeRepository.findById(deviceTypeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Device type not found with id: " + deviceTypeId));

        Device device = new Device();
        device.setSerialNumber(serialNumber);
        device.setDeviceType(deviceType);

        return deviceRepository.save(device);
    }

    public Device getDeviceById(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Device not found with id: " + deviceId));
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }
}
