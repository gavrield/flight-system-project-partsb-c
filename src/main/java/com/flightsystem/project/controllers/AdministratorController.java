package com.flightsystem.project.controllers;

import com.flightsystem.project.business_logic.AdministratorFacade;
import com.flightsystem.project.business_logic.AnonymousFacade;
import com.flightsystem.project.business_logic.LoginToken;
import com.flightsystem.project.business_logic.Role;
import com.flightsystem.project.models.Administrator;
import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;


import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    AdministratorFacade administratorFacade = null;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestHeader("LoginToken") String token){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        return administratorFacade.getAllCustomers();
    }

    @PostMapping("/airlines")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAirline(@RequestHeader("LoginToken") String token,@RequestBody AirlineCompany airlineCompany){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        administratorFacade.addAirlineCompany(airlineCompany);
    }

    @PostMapping("/add-customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestHeader("LoginToken") String token,@RequestBody Customer customer){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        administratorFacade.addCustomer(customer);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAdministrator(@RequestHeader("LoginToken") String token,@RequestBody Administrator administrator){
        if(administratorFacade == null){
            setAdministratorFacade(token);
        }
        administratorFacade.addAdministrator(administrator);
    }

    @DeleteMapping("/airlines/{id}")
    public void removeAirline(@RequestHeader("LoginToken") String token,@PathVariable long id){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        administratorFacade.removeAirlineCompany(administratorFacade.getAirlineCompanyById(id));
    }

    @DeleteMapping("/customers/{id}")
    public void removeCustomer(@RequestHeader("LoginToken") String token,@PathVariable long id){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        try {
            administratorFacade.removeCustomer(administratorFacade.getCustomerDao().get(id));
        }
        catch (SQLException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public void removeAdministrator(@RequestHeader("LoginToken") String token,@PathVariable int id){
        if(administratorFacade == null)
            setAdministratorFacade(token);
        administratorFacade.removeAdministrator(administratorFacade.getAdministratorDao().get(id));
    }

    private void setAdministratorFacade(String jsonToken){
        LoginToken token = AnonymousFacade.getGson().fromJson(jsonToken, LoginToken.class);
        if(token.role == Role.administrator)
            administratorFacade = new AdministratorFacade(token);
    }
}
