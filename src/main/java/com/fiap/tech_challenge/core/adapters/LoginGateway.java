package com.fiap.tech_challenge.core.adapters;

import com.fiap.tech_challenge.core.domain.model.UserDomain;

public interface LoginGateway {

    UserDomain getUserByEmail(String email);
    void updatedPassword(UserDomain userEntity);
}
