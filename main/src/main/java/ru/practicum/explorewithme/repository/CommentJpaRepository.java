package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.Comment.Comment;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

    Comment findCommentByOwnerIdAndEventIdAndComment(Long owner_id, Long event_id, String comment);


}
