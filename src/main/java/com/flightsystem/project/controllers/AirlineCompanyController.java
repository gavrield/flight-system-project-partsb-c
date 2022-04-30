package com.flightsystem.project.controllers;

import com.flightsystem.project.business_logic.AirlineCompanyFacade;
import com.flightsystem.project.business_logic.AnonymousFacade;
import com.flightsystem.project.business_logic.LoginToken;
import com.flightsystem.project.business_logic.Role;
import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Flight;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline-company")
public class AirlineCompanyController {

    AirlineCompanyFacade airlineFacade = null;

    @GetMapping("/my-flights")
    public List<Flight> getMyFlights(@RequestHeader("LoginToken") String token){
        if(airlineFacade == null)
            setAirlineFacade(token);
        return airlineFacade.getMyFlights();
    }

    @PostMapping("/add-flight")
    public void addFlight(@RequestHeader("LoginToken") String token,@RequestBody Flight flight){
        if(airlineFacade == null)
            setAirlineFacade(token);
        airlineFacade.addFlight(flight);
    }

    @PutMapping("/{id}")
    public void updateAirline(@RequestHeader("LoginToken") String token,@RequestBody AirlineCompany company, @PathVariable long id){
        if(airlineFacade == null)
            setAirlineFacade(token);
        company.id = id;
        airlineFacade.updateAirline(company);
    }

    @PutMapping("/my-flights/{id}")
    public void updateFlight(@RequestHeader("LoginToken") String token,@RequestBody Flight flight, @PathVariable long id){
        if(airlineFacade == null)
            setAirlineFacade(token);
        flight.id = id;
        airlineFacade.updateFlight(flight);
    }

    @DeleteMapping("/my-flights")
    public void removeFlight(@RequestHeader("LoginToken") String token,@PathVariable Flight flight){
        if(airlineFacade == null)
            setAirlineFacade(token);
        airlineFacade.removeFlight(flight);
    }


    private void setAirlineFacade(String jsonToken){
        LoginToken token = AnonymousFacade.getGson().fromJson(jsonToken, LoginToken.class);
        if(token.role == Role.airline_company)
            airlineFacade = new AirlineCompanyFacade(token);
    }

}
