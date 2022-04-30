package com.flightsystem.project.dao;

import com.flightsystem.project.models.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends AbstractDao<Flight, Long>{

    private String[] labels ={
            "flight_id","airline_id","airline_name","country_id","country_name",
            "user_id","username","password","email","role_id",
            "role_name","origin_country_id","origin_country_name",
            "destination_country_id","destination_country_name",
            "departure_time","landing_time","remaining_tickets"};


    @Override
    public Flight get(Long id) {
        setConnection();
        Flight flight = null;
        try {
            ResultSet resultSet = statement.executeQuery(Queries.sqlGetFlightById(id));
            resultSet.next();
            flight = flightFactory(resultSet);
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(Queries.SQL_GET_FLIGHTS);
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
            resultSet.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    @Override
    public void add(Flight flight) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlInsertFlight(flight));
        }catch (SQLException e){
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void remove(Flight flight) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlDeleteFlight(flight));
        }catch (Exception e){
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    @Override
    public void update(Flight flight) throws SQLException{
        setConnection();
        try {
            statement.executeUpdate(Queries.sqlUpdateFlight(flight));
        }catch (Exception e){
            throw e;
        }
        finally {
            closeConnection();
        }
    }

    /**
     * get a list of flights of a specific airline company
     * @param airlineId
     * @return list of flight POCO
     */
    public List<Flight> getFlightsByAirlineId(Long airlineId){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByAirlineId(airlineId));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * get a list of flights by the parameters
     * @param originCountryId
     * @param destCountryId
     * @param date
     * @return list of flight POCO
     */
    public List<Flight> getFlightsByParameters(int originCountryId, int destCountryId, Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByParameters
                            (originCountryId, destCountryId,date.toString()));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * get flights by origin country
     * @param country_id
     * @return list of flight POCO
     */
    public List<Flight> getFlightsByOriginCountryId(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByParameters
                            (country_id, 0, ""));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * get flights by destination country
     * @param country_id
     * @return list of flight POCO
     */
    public List<Flight> getFlightsByDestinationCountryId(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByParameters
                            (0, country_id, ""));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * get all the flight departure in a specific date
     * @param date
     * @return list of flight POCO
     */
    public List<Flight>  getFlightsByDepartureDate(Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByParameters
                            (0, 0, date.toString()));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * get all the flight landing in a specific date
     * @param date
     * @return list of flight POCO
     */
    public List<Flight> getFlightsByLandingDate(Date date){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlGetFlightsByLandingDate(date));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * returning all the flight that departure from the country represented by country_id
     * in the next 12 hours
     * @param country_id
     * @return list of flight POCO
     */
    public List<Flight> getDepartureFlights(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlDepartureFlights(country_id));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            closeConnection();
        }
        return flights;
    }

    /**
     * returning all the flight that arrives to the country represented by country_id
     * in the next 12 hours
     * @param country_id
     * @return list of flight POCO
     */
    public List<Flight> getArrivalFlights(int country_id){
        setConnection();
        List<Flight> flights = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery
                    (Queries.sqlArrivalFlights(country_id));
            while (resultSet.next()){
                flights.add(flightFactory(resultSet));
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {closeConnection();}
        return flights;
    }

    /**
     * creating flight POCO from database record
     * @param resultSet
     * @return flight POCO
     */
    private Flight flightFactory(ResultSet resultSet) throws SQLException {
        Flight flight=null;
        try {
            flight = new Flight(
                    resultSet.getLong(this.labels[0]),
                    new AirlineCompany(
                            resultSet.getLong(this.labels[1]),
                            resultSet.getString(labels[2]),
                            new Country(
                                    resultSet.getInt(labels[3]),
                                    resultSet.getString(labels[4])
                            ),
                            new User(
                                    resultSet.getLong(labels[5]),
                                    resultSet.getString(labels[6]),
                                    resultSet.getString(labels[7]),
                                    resultSet.getString(labels[8]),
                                    new UserRole(
                                            resultSet.getInt(labels[9]),
                                            resultSet.getString(labels[10])
                                    )
                            )
                    ),
                    new Country(
                            resultSet.getInt(labels[11]),
                            resultSet.getString(labels[12])
                    ),
                    new Country(
                            resultSet.getInt(labels[13]),
                            resultSet.getString(labels[14])
                    ),
                    resultSet.getTimestamp(labels[15]),
                    resultSet.getTimestamp(labels[16]),
                    resultSet.getInt(labels[17])
            );
        }catch (Exception e){
            throw e;
        }
        return flight;
    }
}
