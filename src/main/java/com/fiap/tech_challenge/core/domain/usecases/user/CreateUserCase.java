package com.fiap.tech_challenge.core.domain.usecases.user;


import com.fiap.tech_challenge.core.domain.model.UserDomain;

public interface CreateUserCase {

    UserDomain run(UserDomain newUser);
}
