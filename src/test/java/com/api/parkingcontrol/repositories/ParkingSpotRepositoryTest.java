package com.api.parkingcontrol.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class ParkingSpotRepositoryTest {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;
    @Test
    void existsByLicensePlateCar() {
    }
}