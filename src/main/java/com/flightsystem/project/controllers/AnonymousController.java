package com.flightsystem.project.controllers;

import com.flightsystem.project.business_logic.AnonymousFacade;
import com.flightsystem.project.business_logic.LoginToken;
import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Country;
import com.flightsystem.project.models.Customer;
import com.flightsystem.project.models.Flight;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.util.List;


@RestController
public class AnonymousController {

    AnonymousFacade anonymousFacade = new AnonymousFacade();

    @GetMapping("/auth")
    public LoginToken login(@RequestParam String username, @RequestParam String password){
        return anonymousFacade.login(username,password);
    }
    @GetMapping("/flights")
    public List<Flight> getAllFlights() {
        return anonymousFacade.getAllFlights();
    }
    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable long id){
        return anonymousFacade.getFlightById(id);
    }
    @GetMapping("/flights/by-parameters")
    public List<Flight> getFlightsByParameters(
            @RequestParam (value = "origin-country-id", defaultValue = "1") Integer ocid,
            @RequestParam(value = "dest-country-id", defaultValue = "2") Integer dcid,
            @RequestParam(value = "date", defaultValue = "2022-01-01 00:00:00") Date dateTime
    ){
        return anonymousFacade.getFlightsByParameters(ocid,dcid,dateTime);
    }
    @GetMapping("/airlines")
    public List<AirlineCompany> getAllAirlines(){
        return anonymousFacade.getAllAirlineCompanies();
    }
    @GetMapping("/airlines/{id}")
    public AirlineCompany getAirlineById(@PathVariable long id){
        return anonymousFacade.getAirlineCompanyById(id);
    }
    @GetMapping("/countries")
    public List<Country> getAllCountries(){
        return anonymousFacade.getAllCountries();
    }
    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable int id){
        return anonymousFacade.getCountryById(id);
    }

    @PostMapping("/")
    public void addCustomer(@RequestBody Customer customer){
        anonymousFacade.addCustomer(customer);
    }
}
