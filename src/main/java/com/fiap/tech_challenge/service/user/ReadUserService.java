package com.fiap.tech_challenge.service.user;

import com.fiap.tech_challenge.controller.dto.UserResponseDto;
import com.fiap.tech_challenge.exception.NotFoundException;
import com.fiap.tech_challenge.mapper.UserMapper;
import com.fiap.tech_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReadUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto getUserById(String userId) {
        log.info("Consulting user by ID: {}", userId);
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        return userMapper.toResponseDto(user);
    }

    public Page<UserResponseDto> getAllUsers(final Integer page, final Integer size, final String sort) {
        log.info("Consulting all users");
        Pageable pageable = buildPageable(page, size, sort);
        return userRepository.findAll(pageable).map(userMapper::toResponseDto);
    }

    private Pageable buildPageable(int page, int size, String sort) {
        if (sort == null || sort.isEmpty()) {
            return PageRequest.of(page, size);
        }
        String[] sortParts = sort.split(",");
        String property = sortParts[0].trim();
        Sort.Direction direction = (sortParts.length > 1 && sortParts[1].trim().equalsIgnoreCase("desc"))
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(page, size, Sort.by(new Sort.Order(direction, property)));
    }

}
