package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.user.User;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable page);
    List<User> findAllByIdIn(List<Long> ids, Pageable page);

}
