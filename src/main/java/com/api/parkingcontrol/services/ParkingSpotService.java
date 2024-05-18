package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import jakarta.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService {

    final ParkingSpotRepository parkingSpotRepository;


    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
        this.validateParkingSpot(parkingSpotModel);
        return parkingSpotRepository.save(parkingSpotModel);
    }

    private void validateParkingSpot(ParkingSpotModel parkingSpotModel) {
        String licensePlate = parkingSpotModel.getLicensePlateCar();
        for(Field field: parkingSpotModel.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                Object value = field.get(parkingSpotModel);
                if(value == null && !field.getName().equals("id")){
                    throw new DataIntegrityViolationException(field.getName() + " cannot be null or empty");
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return parkingSpotRepository.existsByApartmentAndBlock(apartment, block);
    }

    public Page<ParkingSpotModel> findAll(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }
    public Optional<ParkingSpotModel> findById(UUID parkingSpotId){
        return parkingSpotRepository.findById(parkingSpotId);
    };

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

}
