package kr.co.yeoeulsim.eatgo.application;

public class PasswordWrongException extends RuntimeException {

    PasswordWrongException() {
        super("Password is Wrong");
    }
}
