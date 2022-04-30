package com.flightsystem.project.controllers;

import com.flightsystem.project.business_logic.AnonymousFacade;
import com.flightsystem.project.business_logic.CustomerFacade;
import com.flightsystem.project.business_logic.LoginToken;
import com.flightsystem.project.business_logic.Role;
import com.flightsystem.project.models.Customer;
import com.flightsystem.project.models.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerFacade customerFacade = null;

    @GetMapping("/tickets")
    public List<Ticket> getMyFlights(@RequestHeader("LoginToken") String token){
        if(customerFacade == null)
            setCustomerFacade(token);
        return customerFacade.getMyTickets();
    }

    @PostMapping("/add-ticket")
    public void addTicket(@RequestHeader("LoginToken") String token, @RequestBody Ticket ticket){
        if(customerFacade == null)
            setCustomerFacade(token);
        customerFacade.addTicket(ticket);
    }

    @PutMapping("/")
    public void updateCustomer(@RequestHeader("LoginToken") String token,@RequestBody Customer customer){
        if(customerFacade == null)
            setCustomerFacade(token);
        customer.id = customerFacade.getToken().id;
        customerFacade.updateCustomer(customer);
    }

    @DeleteMapping("/tickets")
    public void removeTicket(@RequestHeader("LoginToken") String token,@RequestBody Ticket ticket){
        if(customerFacade == null)
            setCustomerFacade(token);
        customerFacade.removeTicket(ticket);
    }

    private void setCustomerFacade(String jsonToken){
        LoginToken token = AnonymousFacade.getGson().fromJson(jsonToken, LoginToken.class);
        if(token.role == Role.customer)
            customerFacade = new CustomerFacade(token);
    }
}
