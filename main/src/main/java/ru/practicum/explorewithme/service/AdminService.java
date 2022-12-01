package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.exceptions.CategoryNotFoundException;
import ru.practicum.explorewithme.exceptions.CompilationNotFoundException;
import ru.practicum.explorewithme.model.category.Category;
import ru.practicum.explorewithme.model.compilation.Compilation;
import ru.practicum.explorewithme.model.compilation.NewCompilationDto;
import ru.practicum.explorewithme.model.event.AdminUpdateEventRequest;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.CategoryJpaRepository;
import ru.practicum.explorewithme.repository.CompilationJpaRepository;
import ru.practicum.explorewithme.repository.EventJpaRepository;
import ru.practicum.explorewithme.repository.UserJpaRepository;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserJpaRepository userJpaRepository;
    private final EventJpaRepository eventJpaRepository;
    private final CategoryJpaRepository categoryJpaRepository;
    private final CompilationJpaRepository compilationJpaRepository;

    private final ModelMapper modelMapper;

    public List<Event> findEvents(List<Long> users, List<State> states, List<Long> categories, LocalDateTime rangeStart
            , LocalDateTime rangeEnd, Integer from, Integer size) {

        if (rangeStart == null) rangeStart = LocalDateTime.MIN;
        if (rangeEnd == null) rangeStart = LocalDateTime.MAX;
        if (rangeEnd.isBefore(rangeStart)) throw new RuntimeException(); // TODO прорботать исключение ("start must be before end")
        Pageable page = OffsetLimitPageable.of(from, size);

        return eventJpaRepository.findEvents(users, states, categories, rangeStart, rangeEnd, page);
    }


    public User addUser(NewUserRequest newUserRequest) {
        User user = modelMapper.map(newUserRequest, User.class);
        return userJpaRepository.save(user);

    }


    public List<User> findUsers(List<Long> ids, Integer from, Integer size) {
        Pageable page = OffsetLimitPageable.of(from, size);
        if (ids == null) {
            return userJpaRepository.findAll(page).getContent();
        } else {
            return userJpaRepository.findAllByIdIn(ids, page);
        }

    }

    public void deleteUsers(Long userId) {
        userJpaRepository.findById(userId).orElseThrow(RuntimeException::new);
        userJpaRepository.deleteById(userId);

    }

    public Category addNewCategory(Category category) {
        return categoryJpaRepository.save(category);
    }

    public Category changeCategory(Category category) {
        return categoryJpaRepository.save(category);
    }

    public void deleteCategory(Long catId) {
            categoryJpaRepository.deleteById(catId);
    }

    public Event editEvent(Long eventId, AdminUpdateEventRequest adminUpdateEventRequest) {
       Event updatedEvent =  updateEvent(eventJpaRepository.findById(eventId)
                .orElseThrow(RuntimeException::new), adminUpdateEventRequest);// TODO ВЫВОД ОШИБКИ
       return eventJpaRepository.save(updatedEvent);
    }

    Event updateEvent(Event event, AdminUpdateEventRequest adminUpdateEventRequest){

        if(adminUpdateEventRequest.getAnnotation() != null) event.setAnnotation(adminUpdateEventRequest
                .getAnnotation());
        if(adminUpdateEventRequest.getCategory() != null) event.setCategory(categoryJpaRepository
                .findById(adminUpdateEventRequest.getCategory()).orElseThrow(RuntimeException::new));// TODO ВЫВОД ОШИБКИ
        if(adminUpdateEventRequest.getDescription() != null) event.setDescription(adminUpdateEventRequest
                .getDescription());
        if(adminUpdateEventRequest.getEventDate() != null) event.setEventDate(adminUpdateEventRequest.getEventDate());
        if(adminUpdateEventRequest.getLocation() != null) event.setLocation(adminUpdateEventRequest.getLocation());
        if(adminUpdateEventRequest.getPaid() != null) event.setPaid(adminUpdateEventRequest.getPaid());
        if(adminUpdateEventRequest.getParticipantLimit() != null) event.setParticipantLimit(adminUpdateEventRequest
                .getParticipantLimit());
        if(adminUpdateEventRequest.getRequestModeration() != null) event.setRequestModeration(adminUpdateEventRequest
                .getRequestModeration());
        if(adminUpdateEventRequest.getTitle() != null) event.setTitle(adminUpdateEventRequest.getTitle());
        return event;
    }

    public Event publishEvent(Long eventId) {
       Event event = eventJpaRepository.findById(eventId).orElseThrow(CategoryNotFoundException::new); // TODO выбрать тип ошибки, не может быть опубликовано
       if (event.getState().equals(State.PENDING) && event.getEventDate().isAfter(LocalDateTime.now().plusHours(1))){
       } else throw new RuntimeException(); // TODO выбрать тип ошибки, не может быть опубликовано
        event.setState(State.PUBLISHED);
        event.setPublishedOn(LocalDateTime.now());
    return eventJpaRepository.save(event);


    }

    public Event rejectEvent(Long eventId) {
        Event event = eventJpaRepository.findById(eventId).orElseThrow(RuntimeException::new); // TODO выбрать тип ошибки, не может быть опубликовано
        event.setState(State.CANCELED);
        return event;
    }

    public Compilation addCompilation(NewCompilationDto newCompilationDto) {
       List<Event> eventsOfCompilation = eventJpaRepository.findAllById(newCompilationDto.getEvents());
       Compilation compilation = modelMapper.map(newCompilationDto, Compilation.class);
       compilation.setEvents(eventsOfCompilation);
       return compilationJpaRepository.save(compilation);
    }

    public void deleteCompilation(Long compId) {
        categoryJpaRepository.deleteById(compId);
    }

    public void unpinCompilation(Long compId) {
        Compilation compilation = compilationJpaRepository.findById(compId).orElseThrow(CompilationNotFoundException::new);
        compilation.setPinned(false);
        compilationJpaRepository.save(compilation);
    }

    public void pinCompilation(Long compId) {
        Compilation compilation = compilationJpaRepository.findById(compId).orElseThrow(CompilationNotFoundException::new);
        compilation.setPinned(true);
        compilationJpaRepository.save(compilation);
    }

    public void deleteEventFromCompilation(Long compId, Long eventId) {
        Compilation compilation = compilationJpaRepository.findById(compId).orElseThrow(CompilationNotFoundException::new);
        Event event = eventJpaRepository.findById(eventId).orElseThrow(CompilationNotFoundException::new);
        compilation.getEvents().remove(event);
        compilationJpaRepository.save(compilation);
    }

    public void addLinkEventToCompilation(Long compId, Long eventId) {
        Compilation compilation = compilationJpaRepository.findById(compId).orElseThrow(CompilationNotFoundException::new);
        Event event = eventJpaRepository.findById(eventId).orElseThrow(CompilationNotFoundException::new);
        compilation.getEvents().add(event);
        compilationJpaRepository.save(compilation);
    }


}
