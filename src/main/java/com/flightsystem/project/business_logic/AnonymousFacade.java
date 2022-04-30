package com.flightsystem.project.business_logic;

import com.flightsystem.project.dao.CustomerDao;
import com.flightsystem.project.models.Customer;
import com.flightsystem.project.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.SQLException;

public class AnonymousFacade extends BaseFacade {

    private static Gson gson = new GsonBuilder().create();
    private CustomerDao customerDao = new CustomerDao();
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public static Gson getGson() {
        return gson;
    }

    /**
     * checks that the user is registered and returns her authorization token
     * @param username
     * @param password
     * @return LoginToken
     * @throws WrongPasswordError
     */
    public LoginToken login(String username, String password) throws WrongPasswordError {
        LoginToken token = null;

        User user = super.getUserDao().getUserByUsername(username);
        if (!(user != null && user.password.equals(password)))
            throw new WrongPasswordError();
        if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.administrator)))
            token = new LoginToken(user.id, user.username, Role.administrator);
        else if (user.userRole.roleName.equalsIgnoreCase(String.valueOf(Role.airline_company)))
            token = new LoginToken(user.id, username, Role.airline_company);
        else
            token = new LoginToken(user.id,user.username,Role.customer);
        return token;
    }

    /**
     * letting anonymous user add itself as a customer user
     * @param customer
     */
    public void addCustomer(Customer customer){
        super.createNewUser(customer.user);
        User user = getUserDao().getUserByUsername(customer.user.username);
        customer.user.id = user.id;
        try {
            getCustomerDao().add(customer);
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
