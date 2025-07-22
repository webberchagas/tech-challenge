package com.fiap.tech_challenge.config;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.CreateAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.DeleteAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.ReadAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.UpdateAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.login.CreatePasswordCase;
import com.fiap.tech_challenge.core.domain.usecases.login.ValidateLoginCase;
import com.fiap.tech_challenge.core.domain.usecases.login.impl.CreatePasswordCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.login.impl.ValidateLoginCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.CreateUserCase;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.CreateUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.DeleteUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.ReadUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.UpdateUserCaseImpl;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.AddressMapper;
import com.fiap.tech_challenge.infrastructure.persistence.mapper.LoginMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Management API",
                version = "1.0.0",
                description = "API for managing users and user addresses",
                contact = @Contact(name = "Grupo 3 Tech Challenge", email = "may-aishiteru@hotmail.com"),
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Ambiente local")
        }
)
public class OpenApiConfig {

        @Bean
        public CreateUserCaseImpl createUserCase(UserGateway userGateway){
                return CreateUserCaseImpl.create(userGateway);
        }

        @Bean
        public DeleteUserCaseImpl deleteUserCase(UserGateway userGateway){
                return new DeleteUserCaseImpl(userGateway);
        }

        @Bean
        public UpdateUserCaseImpl updateUserCase(UserGateway userGateway){
                return new UpdateUserCaseImpl(userGateway);
        }

        @Bean
        public ReadUserCaseImpl readUserCase(UserGateway userGateway){
                return new ReadUserCaseImpl(userGateway);
        }

        @Bean
        public ValidateLoginCaseImpl validateLoginCase(LoginGateway loginGateway, LoginMapper loginMapper){
                return new ValidateLoginCaseImpl(loginGateway,loginMapper);
        }

        @Bean
        public CreatePasswordCaseImpl createPasswordCase(LoginGateway loginGateway, LoginMapper loginMapper){
                return new CreatePasswordCaseImpl(loginGateway,loginMapper);
        }

        @Bean
        public CreateAddressCaseImpl createAddressCase(AddressGateway addressGateway, UserGateway userGateway){
                return new CreateAddressCaseImpl(addressGateway,userGateway);
        }

        @Bean
        public DeleteAddressCaseImpl deleteAddressCase(AddressGateway addressGateway){
                return new DeleteAddressCaseImpl(addressGateway);
        }

        @Bean
        public UpdateAddressCaseImpl updateAddressCase(AddressGateway addressGateway){
                return new UpdateAddressCaseImpl(addressGateway);
        }

//        @Bean
//        public ReadAddressCaseImpl readAddressCase(AddressGateway addressGateway, AddressMapper addressMapper){
//                return new ReadAddressCaseImpl(addressGateway, addressMapper);
//        }
}
