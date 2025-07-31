package com.fiap.tech_challenge.core.domain.usecases.user;

import com.fiap.tech_challenge.core.domain.model.PageResultDomain;
import com.fiap.tech_challenge.core.domain.model.UserDomain;

public interface ReadAllUserCase {

    PageResultDomain<UserDomain> run(Integer page, Integer size, String sort);
}
