package ru.practicum.explorewithme.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("Comment was not found");
    }
}
