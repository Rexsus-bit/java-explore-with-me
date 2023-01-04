package ru.practicum.explorewithme.mapper;

import ru.practicum.explorewithme.model.Comment.Comment;
import ru.practicum.explorewithme.model.Comment.CommentDto;

public class CommentMapper {

    public static CommentDto toCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .owner(comment.getOwner().getId())
                .event(comment.getEvent().getId())
                .build();
    }
}
