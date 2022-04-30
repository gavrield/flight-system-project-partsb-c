package com.flightsystem.project.business_logic;

import com.flightsystem.project.dao.AirlineCompanyDao;
import com.flightsystem.project.dao.CountryDao;
import com.flightsystem.project.dao.FlightDao;
import com.flightsystem.project.dao.UserDao;
import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Country;
import com.flightsystem.project.models.Flight;
import com.flightsystem.project.models.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseFacade {

    private CountryDao countryDao = new CountryDao();
    private FlightDao flightDao = new FlightDao();
    private AirlineCompanyDao airlineCompanyDao = new AirlineCompanyDao();
    private UserDao userDao = new UserDao();

    public UserDao getUserDao() {
        return userDao;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public FlightDao getFlightDao() {
        return flightDao;
    }

    public AirlineCompanyDao getAirlineCompanyDao() {
        return airlineCompanyDao;
    }

    public List<Flight> getAllFlights(){
        return getFlightDao().getAll();
    }
    public Flight getFlightById(Long id){
        return getFlightDao().get(id);
    }

    public List<Flight> getFlightsByParameters(int originCountryId, int destCountryId, Date date){

        return getFlightDao().getFlightsByParameters(originCountryId,destCountryId,date);
    }

    public List<AirlineCompany> getAllAirlineCompanies(){
        return getAirlineCompanyDao().getAll();
    }

    public AirlineCompany getAirlineCompanyById(Long id){
        return getAirlineCompanyDao().get(id);
    }

    public List<Country> getAllCountries(){
        return getCountryDao().getAll();
    }

    public Country getCountryById(int id){
        return getCountryDao().get(id);
    }

    public void createNewUser(User user) throws RuntimeException{
        if(user.password.length() < 6)
            throw new RuntimeException("Invalid password length");
        try {
            getUserDao().add(user);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
