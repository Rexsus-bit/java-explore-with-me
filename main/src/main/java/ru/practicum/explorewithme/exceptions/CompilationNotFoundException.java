package ru.practicum.explorewithme.exceptions;

public class CompilationNotFoundException extends RuntimeException {
    public CompilationNotFoundException() {
        super("Compilation was not found");
    }
}
