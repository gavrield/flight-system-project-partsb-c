package com.flightsystem.project.business_logic;

import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Flight;
import com.flightsystem.project.models.User;

import java.sql.SQLException;
import java.util.List;

public class AirlineCompanyFacade extends AnonymousFacade{
    private LoginToken token;

    public AirlineCompanyFacade(LoginToken token){
        this.token = token;
    }

    public LoginToken getToken(){
        return this.token;
    }

    /**
     * only this company can update its details
     * @param airlineCompany
     */
    public void updateAirline(AirlineCompany airlineCompany){
        if(airlineCompany.user.id == token.id && token.role == Role.airline_company)
            try {
            getAirlineCompanyDao().update(airlineCompany);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * @return List of flights added by this company
     */
    public List<Flight> getMyFlights(){
        AirlineCompany airlineCompany = super.getAirlineCompanyDao().getAirlinesByUsername(token.username);
        return super.getFlightDao().getFlightsByAirlineId(airlineCompany.id);
    }

    /**
     * adding new flight
     * @param flight
     */
    public void addFlight(Flight flight){
        if(flightValidator(flight) && token.role == Role.airline_company)
            try{
            getFlightDao().add(flight);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * update flight
     * @param flight
     */
    public void updateFlight(Flight flight){
        if(flightValidator(flight)&& token.role == Role.airline_company)
            try {
                getFlightDao().update(flight);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * delete a flight
     * @param flight
     */
    public void removeFlight(Flight flight){
        if(flightValidator(flight)&& token.role == Role.airline_company)
            try{
            getFlightDao().remove(flight);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
    }

    /**
     * checks if a flight is concise with the business logic:
     * 1) landing time is after departure time
     * 2) the remainingTickets field is positive
     * 3) the destination country is different from the origin country
     * 4) the airline company is the same as this company
     */
    private boolean flightValidator(Flight flight){
        if(flight.departureTime.after(flight.landingTime))
            return false;
        if (flight.remainingTickets < 0)
            return false;
        if(flight.destinationCountry.id == flight.originCountry.id)
            return false;
        if (flight.airlineCompany.user.id != token.id)
            return false;
        return true;
    }
}
