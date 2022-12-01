package ru.practicum.explorewithme.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User was not found");
    }
}
