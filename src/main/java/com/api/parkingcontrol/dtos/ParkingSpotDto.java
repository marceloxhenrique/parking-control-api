package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkingSpotDto {

    @NotBlank(message = "Parking spot number is required")
    private String parkingSpotNumber;
    @NotBlank(message = "License plate car is required")
    @Size(max = 7, min = 7, message = "License plate car must be 7 characters")
    private String licensePlateCar;
    @NotBlank(message = "Brand Car is required")
    private String brandCar;
    @NotBlank(message = "Model Car is required")
    private String modelCar;
    @NotBlank(message = "Color Car is Required")
    private String colorCar;
    @NotBlank(message = "Responsible Name is required")
    private String responsibleName;
    @NotBlank(message = "Apartment number is required")
    private String apartment;
    @NotBlank(message = "Block number is required")
    private String block;
}
