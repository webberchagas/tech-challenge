package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.domain.model.UserDomain;

public interface UpdateUserCase {

    UserDomain run(String id, UserDomain user);
}
