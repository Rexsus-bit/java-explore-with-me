package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.event.Event;
import ru.practicum.explorewithme.model.event.State;
import ru.practicum.explorewithme.model.user.NewUserRequest;
import ru.practicum.explorewithme.model.user.User;
import ru.practicum.explorewithme.repository.UserJpaRepository;
import ru.practicum.explorewithme.util.OffsetLimitPageable;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserJpaRepository userJpaRepository;

    private final ModelMapper modelMapper;

    public List<Event> findEvents(List<Long> users, List<State> states, List<Long> categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Integer from, Integer size) {
        return null;
    }


    public User addUser(NewUserRequest newUserRequest) {
        User user = modelMapper.map(newUserRequest, User.class);
        return userJpaRepository.save(user);

    }


    public List<User> findUsers(List<Long> ids, Integer from, Integer size) {
//        Pageable page = OffsetLimitPageable.of(from, size, Sort.by(Sort.Direction.DESC, "start"));
        Pageable page = OffsetLimitPageable.of(from, size);
        if (ids == null) {
            return userJpaRepository.findAll(page).getContent();
        } else {
            return userJpaRepository.findAllByIdIn(ids, page);
        }

    }
}
