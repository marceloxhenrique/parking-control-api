package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class ParkingSpotServiceTest {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @MockBean
    private ParkingSpotRepository parkingSpotRepository;

    private ParkingSpotModel parkingSpotModel;

    @BeforeEach
    public void setUp() {
        parkingSpotModel = new ParkingSpotModel();
        parkingSpotModel.setId(UUID.randomUUID());
        parkingSpotModel.setResponsibleName("Marcelo");
        parkingSpotModel.setParkingSpotNumber("123");
        parkingSpotModel.setModelCar("M3");
        parkingSpotModel.setBrandCar("BMW");
        parkingSpotModel.setColorCar("RED");
        parkingSpotModel.setLicensePlateCar("AZE1234");
        parkingSpotModel.setApartment("110");
        parkingSpotModel.setBlock("AB12");
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
    }

    @Test
    @DisplayName("Should Create a new parkingSpotModel")
    void testSave() {
        when(parkingSpotRepository.save(any(ParkingSpotModel.class))).thenReturn(parkingSpotModel);

        ParkingSpotModel savedParkingSpot = parkingSpotService.save(parkingSpotModel);

        assertThat(savedParkingSpot).isNotNull();
        assertThat(savedParkingSpot.getParkingSpotNumber()).isEqualTo(parkingSpotModel.getParkingSpotNumber());
        verify(parkingSpotRepository, times(1)).save(parkingSpotModel);
    }

    @Test
    @DisplayName("Should throw an exception when saving an invalid parking spot")
    void testSaveFailed() {
        parkingSpotModel.setParkingSpotNumber(null);
        assertThatThrownBy(() -> parkingSpotService.save(parkingSpotModel))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("parkingSpotNumber cannot be null or empty");
        verify(parkingSpotRepository, times(0)).save(any(ParkingSpotModel.class));
    }

    @Test
    @DisplayName("Should find license plate car")
    void testExistLicensePlateCar(){
        when(parkingSpotRepository.existsByLicensePlateCar(any())).thenReturn(true);

        boolean exists = parkingSpotService.existsByLicensePlateCar("AZE1234");
        assertThat(exists).isTrue();
        verify(parkingSpotRepository, times(1)).existsByLicensePlateCar("AZE1234");
    }

    @Test
    @DisplayName("Should find parking spot number")
    void testExistParkingSpotNumber(){
        when(parkingSpotRepository.existsByParkingSpotNumber(any())).thenReturn(true);

        boolean exists = parkingSpotService.existsByParkingSpotNumber("110");
        assertThat(exists).isTrue();
        verify(parkingSpotRepository, times(1)).existsByParkingSpotNumber("110");
    }

}