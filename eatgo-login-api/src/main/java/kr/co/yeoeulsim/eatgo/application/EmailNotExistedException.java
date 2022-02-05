package kr.co.yeoeulsim.eatgo.application;

public class EmailNotExistedException extends RuntimeException{

    EmailNotExistedException(String email){
        super("Email is Not registered: " + email);
    }
}
