package ru.practicum.explorewithme.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.user.User;

import java.util.List;

@Repository
public interface CompilationJpaRepository extends JpaRepository<Compilation, Long> {

    List<Compilation> findCompilationByPinned(Boolean pinned, Pageable page);

}
