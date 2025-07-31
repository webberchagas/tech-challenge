package com.fiap.tech_challenge.core.domain.usecases.login;

import com.fiap.tech_challenge.core.domain.model.LoginDomain;

public interface CreatePasswordCase {

    void run(LoginDomain loginDomain);
}
