package com.fiap.tech_challenge.core.domain.usecases.user.impl;

import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.model.UserDomain;
import com.fiap.tech_challenge.core.domain.model.type.UserType;
import com.fiap.tech_challenge.core.domain.usecases.user.UpdateUserCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpdateUserCaseImpl implements UpdateUserCase {

    private final UserGateway userGateway;

    public UpdateUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public UserDomain run(String id, UserDomain user) {
        log.info("Searching for user by ID: {} for update", id);
        var findUser = userGateway.getUserById(id);

        if (isChangingToClient(user) && isChangingType(user, findUser)) {
            userGateway.ensureUserIsNotRestaurantOwner(id);
        }

        if (isChangingDocumentNumber(user, findUser)) {
            userGateway.ensureUserDocumentNumberIsNotAlreadyRegistered(user.getDocumentNumber());
        }

        if(isChangingEmail(user, findUser)){
            userGateway.ensureUserEmailIsNotAlreadyRegistered(user.getEmail());
        }

        log.info("Updating user with ID: {}", findUser.getUserId());
        findUser.updateUser(user);
        return userGateway.createUser(findUser);
    }

    private boolean isChangingType(UserDomain newUser, UserDomain currentUser) {
        return !newUser.getUserType().equals(currentUser.getUserType());
    }

    private boolean isChangingToClient(UserDomain newUser) {
        return newUser.getUserType() == UserType.CLIENT;
    }

    private boolean isChangingDocumentNumber(UserDomain newUser, UserDomain currentUser) {
        return !newUser.getDocumentNumber().equals(currentUser.getDocumentNumber());
    }

    private boolean isChangingEmail(UserDomain newUser, UserDomain currentUser) {
        return !newUser.getEmail().equals(currentUser.getEmail());
    }
}
