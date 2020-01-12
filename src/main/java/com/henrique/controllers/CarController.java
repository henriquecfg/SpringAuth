package com.henrique.controllers;

import com.henrique.models.Car;
import com.henrique.modelsDTO.CarDTO;
import com.henrique.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value="/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    @ResponseStatus(CREATED)
    public Car create(@RequestBody Car car) {
        return carService.save(car);
    }

    @PutMapping
    @ResponseStatus(OK)
    public Car update(@RequestBody Car car) {
        return carService.save(car);
    }

    @GetMapping("/{car.id}")
    @ResponseStatus(OK)
    public ResponseEntity<Car> get(@PathVariable("car.id") Long carId) {
        return ResponseEntity.status(OK).body(carService.findOne(carId));
    }

    @GetMapping("/")
    @ResponseStatus(OK)
    public ResponseEntity<List<CarDTO>> getAll() {
        return ResponseEntity.status(OK).body(carService.findAll());
    }

    @DeleteMapping("/{car.id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("car.id") Long carId) {
            carService.delete(carId);
    }
}
