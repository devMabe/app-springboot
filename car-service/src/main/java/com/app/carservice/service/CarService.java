package com.app.carservice.service;

import com.app.carservice.entity.Car;
import com.app.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> getAll() {
        return  carRepository.findAll();
    }

    public Car getOne (int id) {
        return  carRepository.findById(id).orElse(null);
    }

    public Car save(Car car) {
        return carRepository.save((car));
    }

    public List<Car> byUserId (int userId) {
        return carRepository.findByUserId(userId);
    }
}
