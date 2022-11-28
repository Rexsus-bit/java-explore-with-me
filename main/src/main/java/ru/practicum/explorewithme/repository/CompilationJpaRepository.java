package ru.practicum.explorewithme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explorewithme.model.compilation.Compilation;

@Repository
public interface CompilationJpaRepository extends JpaRepository<Compilation, Long> {



}
