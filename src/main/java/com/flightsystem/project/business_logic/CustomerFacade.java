package com.flightsystem.project.business_logic;

import com.flightsystem.project.dao.CustomerDao;
import com.flightsystem.project.dao.TicketsDao;
import com.flightsystem.project.models.Customer;
import com.flightsystem.project.models.Ticket;

import java.sql.SQLException;
import java.util.List;

public class CustomerFacade extends AnonymousFacade {
    private LoginToken token;


    public CustomerFacade(LoginToken token) {
        this.token = token;
    }

    public LoginToken getToken(){
        return this.token;
    }
    /**
     * @param customer
     * lets only this customer to update itself
     */
    public void updateCustomer(Customer customer){
        if(customer.user.id == token.id && token.role == Role.customer)
            try {
                getCustomerDao().update(customer);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }

    }


    /**
     * only this customer adds its own tickets,
     * the method checks if there are enough tickets on this flight
     * @param ticket
     *
     */
    public void addTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.customer){
            if(getFlightDao().get(ticket.flight.id).remainingTickets > 0)
                try {
                    getTicketsDao().add(ticket);
                } catch (SQLException e){
                    System.err.println(e.getMessage());
                }
        }

    }

    /**
     * only this customer can remove its own tickets
     * @param ticket
     */
    public void removeTicket(Ticket ticket){
        if(ticket.customer.user.id == token.id && token.role == Role.customer)
            try {
                getTicketsDao().remove(ticket);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * @return List of tickets that this customer added
     */
    public List<Ticket> getMyTickets(){
        return getTicketsDao().getTicketsByCustomer(getCustomerDao().getCustomerByUsername(token.username));
    }


}
