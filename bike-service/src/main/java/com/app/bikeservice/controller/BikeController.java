package com.app.bikeservice.controller;

import com.app.bikeservice.entity.Bike;
import com.app.bikeservice.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();

        if(bikes.isEmpty())
            return  ResponseEntity.noContent().build();

        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Bike>> getAllByUserId(@PathVariable("userId") int userId) {
        List<Bike> bikes = bikeService.getByUserId(userId);
        if(bikes.isEmpty())
            return  ResponseEntity.noContent().build();

        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getOne(@PathVariable("id") int id) {
        Bike bike = bikeService.getOne(id);

        if(bike == null)
            return  ResponseEntity.notFound().build();

        return ResponseEntity.ok(bike);
    }

    @PostMapping
    public ResponseEntity<Bike> save(@RequestBody Bike bike) {
        Bike newBike = bikeService.save(bike);
        return ResponseEntity.ok(newBike);
    }
}
