package ru.manko.FirstRestApp.util;

public class PersonNotCreateException  extends RuntimeException{

    public PersonNotCreateException(String message) {
        super(message);
    }
}
