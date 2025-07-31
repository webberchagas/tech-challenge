package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.domain.model.UserDomain;

public interface ReadUserByIdCase {

    UserDomain run(String id);
}
