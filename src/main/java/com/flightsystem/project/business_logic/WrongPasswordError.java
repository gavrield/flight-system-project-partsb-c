package com.flightsystem.project.business_logic;

/**
 * raised when the password provided is wrong
 */
public class WrongPasswordError extends RuntimeException{
    public WrongPasswordError(){
        super("password incorrect");
    }
}
