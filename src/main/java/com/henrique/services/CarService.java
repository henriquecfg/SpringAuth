package com.henrique.services;

import com.henrique.models.Car;
import com.henrique.modelsDTO.CarDTO;
import com.henrique.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car findOne(Long id){
        Optional<Car> car = carRepository.findById(id);
        return car.orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Car with ID "+ id +" not found."));
    }

    public List<CarDTO> findAll(){
        List<CarDTO> carList = carRepository.findAll().stream().map(car -> new CarDTO(car)).collect(Collectors.toList());
        return carList;
    }

    public Car save(Car car){
        car.setId(null);
        car.setCreatedAt(LocalDateTime.now());
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public Car update(Car car){
        findOne(car.getId());
        car.setUpdatedAt(LocalDateTime.now());
        Car updatedCar = carRepository.save(car);
        return updatedCar;
    }

    public void delete(Long id){
        try {
            carRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(NOT_FOUND, "Car with ID "+ id +" not found.");
        }
    }
}
