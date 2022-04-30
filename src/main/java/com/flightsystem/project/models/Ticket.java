package com.flightsystem.project.models;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Ticket implements POCO{
    public Long id;
    public Flight flight;
    public Customer customer;

    public Ticket(Long id, Flight flight, Customer customer) {
        this.id = id;
        this.flight = flight;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flight=" + flight +
                ", customer=" + customer +
                '}';
    }
}
