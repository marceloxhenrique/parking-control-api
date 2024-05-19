package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ParkingSpotRepositoryTest {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    private ParkingSpotDto parkingSpotDto;
    private ParkingSpotModel savedParkingSpotModel;
    @BeforeEach
    void setUp() {
        parkingSpotDto = new ParkingSpotDto();
        parkingSpotDto.setResponsibleName("Marcelo");
        parkingSpotDto.setParkingSpotNumber("123");
        parkingSpotDto.setModelCar("M3");
        parkingSpotDto.setBrandCar("BMW");
        parkingSpotDto.setColorCar("RED");
        parkingSpotDto.setLicensePlateCar("AXC1238");
        parkingSpotDto.setApartment("110");
        parkingSpotDto.setBlock("AB12");
        savedParkingSpotModel = createNewParkingSpotModel(parkingSpotDto);
    }

    @AfterEach
    void deleteSavedParkingSpotModel(){
        deleteParkingSpotModel(savedParkingSpotModel);
    }

    @Test
    @DisplayName("License plate car should exist")
    void findLicensePlateCar() {
        //createNewParkingSpotModel(parkingSpotDto);
        assertThat(parkingSpotRepository.existsByLicensePlateCar(
                savedParkingSpotModel.getLicensePlateCar())
        ).isTrue();
    }

    @Test
    @DisplayName("Parking spot number should exist")
    void findParkSpotNumber() {
        assertThat(parkingSpotRepository.existsByParkingSpotNumber(
                savedParkingSpotModel.getParkingSpotNumber())
        ).isTrue();
    }

    @Test
    @DisplayName("Apartment and block should exist")
    void findApartmentAndBlock() {
        assertThat(parkingSpotRepository.existsByApartmentAndBlock(
                savedParkingSpotModel.getApartment(),
                savedParkingSpotModel.getBlock())
        ).isTrue();
    }

    @Test
    @DisplayName("Find parking spot by responsibleName")
    void findResponsibleName(){
        Optional<List<ParkingSpotModel>> result = parkingSpotRepository.findByResponsibleName(savedParkingSpotModel.getResponsibleName());
        assertThat(result).isNotEmpty();
        assertThat(result.get().get(0).getResponsibleName()).isEqualTo("Marcelo");
    }

    private ParkingSpotModel createNewParkingSpotModel(ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return parkingSpotRepository.save(parkingSpotModel);
    }

    private void deleteParkingSpotModel(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }
}