package ru.practicum.explorewithme.service.publicservices;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.CompilationNotFoundException;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.CompilationDto;
import ru.practicum.explorewithme.repository.CompilationJpaRepository;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompilationService {

    private final CompilationJpaRepository compilationJpaRepository;

    public Compilation getCompilationId(Long compId) {
        return compilationJpaRepository.findById(compId)
                .orElseThrow(CompilationNotFoundException::new);
    }

    public List<Compilation> getCompilations(Boolean pinned, Integer from, Integer size) {
        Pageable page = OffsetLimitPageable.of(from, size);
        return compilationJpaRepository.findCompilationByPinned(pinned, page);
    }
}
