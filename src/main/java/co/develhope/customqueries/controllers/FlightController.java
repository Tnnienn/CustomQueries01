package co.develhope.customqueries.controllers;

import co.develhope.customqueries.entities.Flight;
import co.develhope.customqueries.entities.Status;
import co.develhope.customqueries.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @PostMapping
    public HttpStatus postFlights() {
        Random random = new Random();
        for (long i = 1; i <= 50; i++) {
            String description = String.valueOf(random.ints());
            String fromAirport = String.valueOf(random.ints());
            String toAirport = String.valueOf(random.ints());
            Status status = Status.ONTIME;
            Flight flight = new Flight(i, description, fromAirport, toAirport, status);
            flightRepository.saveAndFlush(flight);
        }
        return HttpStatus.OK;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
}
