package ru.practicum.explorewithme.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("Category was not found");
    }
}
