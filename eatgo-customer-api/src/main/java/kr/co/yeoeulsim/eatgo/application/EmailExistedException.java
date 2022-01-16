package kr.co.yeoeulsim.eatgo.application;

public class EmailExistedException extends RuntimeException{

    EmailExistedException(String email){
        super("Email is already registered: " + email);
    }
}
