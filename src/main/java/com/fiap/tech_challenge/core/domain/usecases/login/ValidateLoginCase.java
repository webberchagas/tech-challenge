package com.fiap.tech_challenge.core.domain.usecases.login;

import com.fiap.tech_challenge.core.domain.model.LoginDomain;

public interface ValidateLoginCase {

    Boolean run(LoginDomain loginDomain);
}
