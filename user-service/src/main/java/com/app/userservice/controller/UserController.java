package com.app.userservice.controller;


import com.app.userservice.entity.User;
import com.app.userservice.model.Bike;
import com.app.userservice.model.Car;
import com.app.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll () {
        List<User> users =  userService.getALL();

        if (users.isEmpty())
            return  ResponseEntity.noContent().build();

        return  ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne (@PathVariable("id") int id) {
        User user =  userService.getUserById(id);

        if (user == null)
            return  ResponseEntity.notFound().build();

        return  ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save (@RequestBody User user) {
        User newUser =  userService.save(user);
        return  ResponseEntity.ok(newUser);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);

        if(user == null)
            return  ResponseEntity.notFound().build();

        List<Car> cars = userService.getCars(userId);
        return  ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);

        if(user == null)
            return  ResponseEntity.notFound().build();

        List<Bike> bikes = userService.getBikes(userId);
        return  ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{userId}")
    public  ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        User user = userService.getUserById(userId);

        if(user == null)
            return  ResponseEntity.notFound().build();

        Car newCar = userService.save(userId, car);
        return  ResponseEntity.ok(newCar);
    }

    @PostMapping("/savebike/{userId}")
    public  ResponseEntity<Bike> saveBike(@PathVariable("userId") int userId, @RequestBody Bike bike) {
        User user = userService.getUserById(userId);
        if(user == null)
            return  ResponseEntity.notFound().build();

        Bike newBike = userService.saveBike(userId, bike);
        return  ResponseEntity.ok(newBike);
    }

    @GetMapping("/getAll/{userId}")
    public  ResponseEntity<Map<String, Object>> getAllVehicles(@PathVariable("userId") int userId) {
        Map<String, Object> result = userService.getUserAndVehicles(userId);
        return  ResponseEntity.ok(result);
    }


}
