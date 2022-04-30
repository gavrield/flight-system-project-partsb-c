package com.flightsystem.project;

import com.flightsystem.project.dao.CustomerDao;
import com.flightsystem.project.dao.FlightDao;
import com.flightsystem.project.dao.TicketsDao;
import com.flightsystem.project.models.Customer;
import com.flightsystem.project.models.Flight;
import com.flightsystem.project.models.Ticket;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class HttpRequestsTests {

    @Test
    public void anonymousHttpGetFlightByIdTest(){
        /* checks if a returned Flight poco from http GET
        request is the actual flight from the db*/

        // Given
        long flightId = 1l;
        String url = "http://localhost:8080/flights/" + flightId;
        FlightDao dao = new FlightDao();
        Flight expectedFlight = dao.get(flightId);

        // When
        var actualFlight = ResponseFromServer.getObject(Flight.class, url);

        // Then
        Assert.assertEquals(actualFlight, expectedFlight);
    }

    @Test
    public void administratorHttpGetAllCustomersTest(){
        // Given
        var expectedCustomers = new CustomerDao().getAll().toArray();
        String url = "http://localhost:8080/admin/customers";
        // When
        var actualCustomers = ResponseFromServer.getObject(Customer[].class, url);
        // Then
        Assert.assertArrayEquals(expectedCustomers, actualCustomers);
    }

    @Test
    public  void airlineCompanyHttpGetMyFlightsTest(){
        // Given
        long airlineId = 3l;
        var expectedFlights = new FlightDao().getFlightsByAirlineId(airlineId);
        String url = "http://localhost:8080/airline/flights";
        // When
        var actualFlights = ResponseFromServer.getObject(Flight[].class, url);
        // Then
        Assert.assertEquals(expectedFlights.size(), actualFlights.length);
    }

    @Test
    public void customerHttpGetMyTicketsTest(){
        // Given
        long customerId = 2l;
        Customer customer = new CustomerDao().get(customerId);
        var expectedTickets = new TicketsDao().getTicketsByCustomer(customer);
        String url = "http://localhost:8080/customer/tickets";
        // When
        var actualTickets = ResponseFromServer.getObject(Ticket[].class, url);
        // Then
        Assert.assertEquals(expectedTickets.size(), actualTickets.length);
    }
}
