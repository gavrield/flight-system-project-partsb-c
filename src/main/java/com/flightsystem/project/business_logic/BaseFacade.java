package com.flightsystem.project.business_logic;

import com.flightsystem.project.dao.*;
import com.flightsystem.project.models.AirlineCompany;
import com.flightsystem.project.models.Country;
import com.flightsystem.project.models.Flight;
import com.flightsystem.project.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Getter
public abstract class BaseFacade {

    private static Gson gson = new GsonBuilder().create();
    private CountryDao countryDao = CountryDao.getDao();
    private FlightDao flightDao = FlightDao.getDao();
    private AirlineCompanyDao airlineCompanyDao = AirlineCompanyDao.getDao();
    private UserDao userDao = UserDao.getDao();
    private CustomerDao customerDao = CustomerDao.getDao();
    private AdministratorDao administratorDao = AdministratorDao.getDao();
    private TicketsDao ticketsDao = TicketsDao.getDao();

    public static Gson getGson() {
        return gson;
    }

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
