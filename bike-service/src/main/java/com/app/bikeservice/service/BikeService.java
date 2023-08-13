package com.app.bikeservice.service;

import com.app.bikeservice.entity.Bike;
import com.app.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return  bikeRepository.findAll();
    }

    public List<Bike> getByUserId(int userId) {
        return  bikeRepository.findByUserId(userId);
    }

    public  Bike getOne(int id) {
        return  bikeRepository.findById(id).orElse(null);
    }

    public  Bike save(Bike bike) {
        return bikeRepository.save((bike));
    }
}
