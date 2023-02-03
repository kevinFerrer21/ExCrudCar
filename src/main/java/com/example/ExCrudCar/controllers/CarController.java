package com.example.ExCrudCar.controllers;

import com.example.ExCrudCar.entities.Car;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.ExCrudCar.repositories.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;


    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.saveAndFlush(car);
    }

    @GetMapping
    public List<Car> getAllcar() {
        List<Car> allCars = carRepository.findAll();
        return allCars;
    }

    @GetMapping("/{id}")
    public Car getByIdCar(@PathVariable long id) {
        boolean car = carRepository.existsById(id);
        if (car == true) {
            return carRepository.getReferenceById(id);
        } else return new Car();

    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable long id, @RequestBody Car car) {
        if(carRepository.existsById(id)){
        car.setId(id);
        return carRepository.saveAndFlush(car);}
        else return new Car();

    }
    @DeleteMapping("/{id}")
    public void deleteSingleCar(@PathVariable long id){
        carRepository.existsById(id);
        carRepository.deleteById(id);
    }
    
    @DeleteMapping
    public void deleteAllCars(){
        carRepository.deleteAllInBatch();
    }
}
