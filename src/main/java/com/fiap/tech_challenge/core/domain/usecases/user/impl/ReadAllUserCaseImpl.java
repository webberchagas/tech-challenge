package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.user.ReadAllUserCase;
import com.fiap.tech_challenge.core.dto.user.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Slf4j
public class ReadAllUserCaseImpl implements ReadAllUserCase {
    private final UserGateway userGateway;

    public ReadAllUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Page<UserResponseDto> run(final Integer page, final Integer size, final String sort) {
        log.info("Consulting all users");
        Pageable pageable = buildPageable(page, size, sort);
        return userGateway.getAllUsers(pageable);
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
