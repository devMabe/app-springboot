package com.app.userservice.service;


import com.app.userservice.entity.User;
import com.app.userservice.feignclients.BikeClient;
import com.app.userservice.feignclients.CarClient;
import com.app.userservice.model.Bike;
import com.app.userservice.model.Car;
import com.app.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CarClient carClient;

    @Autowired
    BikeClient bikeClient;

    public List<User> getALL () {
        return  userRepository.findAll();
    }

    public User getUserById(int id) {
        return  userRepository.findById(id).orElse(null);
    }

    public  User save(User user) {
        return userRepository.save((user));
    }

    public List<Car> getCars(int userId) {
        List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/byuser/"+userId, List.class);
        return  cars;
    }

    public List<Bike> getBikes(int userId) {
        List<Bike> bikes = restTemplate.getForObject("http://localhost:8003/bike/byuser/"+userId, List.class);
        return  bikes;
    }

    public Car save(int userId, Car car) {
        car.setUserId(userId);
        return carClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        return bikeClient.save(bike);
    }


    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            result.put("Mensaje", "no existe el usuario");
            return  result;
        }
        result.put("User", user);
        List<Car> cars = carClient.getCars(userId);

        if (cars.isEmpty())
            result.put("Cars", "El usuario no tiene coches");
        else
            result.put("Cars", cars);

        List<Bike> bikes = bikeClient.getBikes(userId);

        if (bikes.isEmpty())
            result.put("Bikes", "El usuario no tiene motos");
        else
            result.put("Bikes", bikes);

        return result;
    }

}
