package ru.practicum.explorewithme.model.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class CommentDto {
    private Long id;
    private String comment;
    private Long owner;
    private Long event;
    private LocalDateTime creationTime;
}
