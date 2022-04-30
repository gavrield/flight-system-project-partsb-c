package com.flightsystem.project.business_logic;

import com.flightsystem.project.dao.*;
import com.flightsystem.project.models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorFacade extends AnonymousFacade {

    private LoginToken token;


    public AdministratorFacade(LoginToken token) {
        this.token = token;
    }

    public LoginToken getToken() {
        return this.token;
    }

    /**
     * list of all customers
     *
     * @return List<Customer>
     */
    public List<Customer> getAllCustomers() {
        if (token.role == Role.administrator)
            return getCustomerDao().getAll();
        return new ArrayList<Customer>();
    }

    /**
     * add user of type AirlineCompany
     *
     * @param company
     */
    public void addAirlineCompany(AirlineCompany company) {
        if (token.role == Role.administrator) {
            super.createNewUser(company.user);
            company.user.id = getUserDao().getUserByUsername(company.user.username).id;
            try {
                super.getAirlineCompanyDao().add(company);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

    }

    /**
     * add user of type Administrator
     *
     * @param administrator
     */
    public void addAdministrator(Administrator administrator) {
        if (token.role == Role.administrator) {
            super.createNewUser(administrator.user);
            administrator.user.id = getUserDao().getUserByUsername(administrator.user.username).id;
            try {
                this.getAdministratorDao().add(administrator);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * remove user of type customer
     *
     * @param customer
     */
    public void removeCustomer(Customer customer) throws SQLException {
        if (token.role == Role.administrator) {
            try {
                super.getCustomerDao().remove(customer);
                super.getUserDao().remove(customer.user);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw e;
            }
        }

    }

    /**
     * remove user of type AirlineCompany
     *
     * @param company
     */
    public void removeAirlineCompany(AirlineCompany company) {
        if (token.role == Role.administrator) {
            this.getAirlineCompanyDao().remove(company);
            try {
                super.getUserDao().remove(company.user);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * remove user of type Administrator
     *
     * @param administrator
     */
    public void removeAdministrator(Administrator administrator) {
        if (token.role == Role.administrator) {
            try {
            this.getAdministratorDao().remove(administrator);
            super.getUserDao().remove(administrator.user);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }


}
