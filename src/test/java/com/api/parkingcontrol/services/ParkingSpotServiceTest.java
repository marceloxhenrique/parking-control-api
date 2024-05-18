package com.api.parkingcontrol.services;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

//@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
class ParkingSpotServiceTest {
    @Autowired
    ParkingSpotRepository parkingSpotRepository;
    @Autowired
    ParkingSpotService parkingSpotService;
    @Test
    @DisplayName("Should Create a new parkingSpotModel")
    void createNewParkingSpot() {
        ParkingSpotDto parkingSpotDto = new ParkingSpotDto();
        parkingSpotDto.setResponsibleName("Marcelo");
        parkingSpotDto.setParkingSpotNumber("123");
        parkingSpotDto.setModelCar("M3");
        parkingSpotDto.setBrandCar("BMW");
        parkingSpotDto.setColorCar("RED");
        parkingSpotDto.setLicensePlateCar("AZE1234");
        parkingSpotDto.setApartment("110");
        parkingSpotDto.setBlock("AB12");
        ParkingSpotModel result = this.createNewParkingSpotModel(parkingSpotDto);

        assertThat(result.getResponsibleName()).isEqualTo("Marcelo");
        assertThat(result.getParkingSpotNumber()).isEqualTo("123");
        assertThat(result.getModelCar()).isEqualTo("M3");
        assertThat(result.getBrandCar()).isEqualTo("BMW");
        assertThat(result.getColorCar()).isEqualTo("RED");
        assertThat(result.getLicensePlateCar()).isEqualTo("AZE1234");
        assertThat(result.getApartment()).isEqualTo("110");
        assertThat(result.getBlock()).isEqualTo("AB12");

    }

    @Test
    @DisplayName("Should not Create a new parkingSpotModel if missing license plate car")
    void noCreateNeParkingSpot() {
        ParkingSpotDto parkingSpotDto = new ParkingSpotDto();
        parkingSpotDto.setResponsibleName("Marcelo");
        parkingSpotDto.setParkingSpotNumber("123");
        parkingSpotDto.setModelCar("M3");
        parkingSpotDto.setBrandCar("BMW");
        parkingSpotDto.setColorCar("RED");
        parkingSpotDto.setApartment("110");
        parkingSpotDto.setBlock("AB12");
        assertThatThrownBy(() -> createNewParkingSpotModelFail(parkingSpotDto))
                .hasMessage("licensePlateCar cannot be null or empty");
    }

    private ParkingSpotModel createNewParkingSpotModel(ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return parkingSpotService.save(parkingSpotModel);
    }

    private ParkingSpotModel createNewParkingSpotModelFail(ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return  parkingSpotService.save(parkingSpotModel);
    }

}