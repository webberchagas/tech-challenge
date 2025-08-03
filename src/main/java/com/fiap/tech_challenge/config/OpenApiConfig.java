package com.fiap.tech_challenge.config;

import com.fiap.tech_challenge.core.adapters.AddressGateway;
import com.fiap.tech_challenge.core.adapters.LoginGateway;
import com.fiap.tech_challenge.core.adapters.MenuItemGateway;
import com.fiap.tech_challenge.core.adapters.RestaurantGateway;
import com.fiap.tech_challenge.core.adapters.UserGateway;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.CreateAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.DeleteAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.ReadAddressByIdCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.ReadAddressByUserIdCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.address.impl.UpdateAddressCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.login.impl.CreatePasswordCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.login.impl.ValidateLoginCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.menu.impl.CreateMenuItemCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.menu.impl.DeleteMenuItemCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.menu.impl.ReadAllMenuItemCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.menu.impl.ReadMenuItemByIdCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.ReadRestaurantByIdCase;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.impl.CreateRestaurantCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.impl.DeleteRestaurantCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.impl.ReadAllRestaurantCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.impl.ReadRestaurantByIdCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.restaurant.impl.UpdateRestaurantCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.CreateUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.DeleteUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.ReadAllUserCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.ReadUserByIdCaseImpl;
import com.fiap.tech_challenge.core.domain.usecases.user.impl.UpdateUserCaseImpl;
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
    public CreateUserCaseImpl createUserCase(UserGateway userGateway) {
        return CreateUserCaseImpl.create(userGateway);
    }

    @Bean
    public DeleteUserCaseImpl deleteUserCase(UserGateway userGateway, RestaurantGateway restaurantGateway) {
        return new DeleteUserCaseImpl(userGateway, restaurantGateway);
    }

    @Bean
    public UpdateUserCaseImpl updateUserCase(UserGateway userGateway, RestaurantGateway restaurantGateway) {
        return new UpdateUserCaseImpl(userGateway, restaurantGateway);
    }

    @Bean
    public ReadAllUserCaseImpl readAllUserCase(UserGateway userGateway) {
        return new ReadAllUserCaseImpl(userGateway);
    }

    @Bean
    public ReadUserByIdCaseImpl readUserByIdCase(UserGateway userGateway) {
        return new ReadUserByIdCaseImpl(userGateway);
    }

    @Bean
    public ValidateLoginCaseImpl validateLoginCase(LoginGateway loginGateway) {
        return new ValidateLoginCaseImpl(loginGateway);
    }

    @Bean
    public CreatePasswordCaseImpl createPasswordCase(LoginGateway loginGateway) {
        return new CreatePasswordCaseImpl(loginGateway);
    }

    @Bean
    public CreateAddressCaseImpl createAddressCase(AddressGateway addressGateway, UserGateway userGateway) {
        return new CreateAddressCaseImpl(addressGateway, userGateway);
    }

    @Bean
    public DeleteAddressCaseImpl deleteAddressCase(AddressGateway addressGateway) {
        return new DeleteAddressCaseImpl(addressGateway);
    }

    @Bean
    public UpdateAddressCaseImpl updateAddressCase(AddressGateway addressGateway) {
        return new UpdateAddressCaseImpl(addressGateway);
    }

    @Bean
    public ReadAddressByIdCaseImpl readAddressByUserIdCase(AddressGateway addressGateway) {
        return new ReadAddressByIdCaseImpl(addressGateway);
    }

    @Bean
    public ReadAddressByUserIdCaseImpl readAddressByIdCase(UserGateway userGateway) {
        return new ReadAddressByUserIdCaseImpl(userGateway);
    }

    @Bean
    public CreateRestaurantCaseImpl createRestaurantCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        return CreateRestaurantCaseImpl.create(restaurantGateway, userGateway);
    }

    @Bean
    public ReadRestaurantByIdCase readRestaurantByIdCase(RestaurantGateway restaurantGateway) {
        return new ReadRestaurantByIdCaseImpl(restaurantGateway);
    }

    @Bean
    public UpdateRestaurantCaseImpl updateRestaurantCase(RestaurantGateway restaurantGateway, UserGateway userGateway) {
        return new UpdateRestaurantCaseImpl(restaurantGateway, userGateway);
    }

    @Bean
    public DeleteRestaurantCaseImpl deleteRestaurantCase(RestaurantGateway restaurantGateway) {
        return new DeleteRestaurantCaseImpl(restaurantGateway);
    }

    @Bean
    public ReadAllRestaurantCaseImpl readAllRestaurantCase(RestaurantGateway restaurantGateway) {
        return new ReadAllRestaurantCaseImpl(restaurantGateway);
    }

    @Bean
    public CreateMenuItemCaseImpl createMenuItemCase(MenuItemGateway menuItemGateway, RestaurantGateway restaurantGateway) {
        return CreateMenuItemCaseImpl.create(menuItemGateway, restaurantGateway);
    }

    @Bean
    public ReadMenuItemByIdCaseImpl readMenuItemByIdCase(MenuItemGateway menuItemGateway) {
        return new ReadMenuItemByIdCaseImpl(menuItemGateway);
    }

    @Bean
    public DeleteMenuItemCaseImpl deleteMenuItemCase(MenuItemGateway menuItemGateway) {
        return new DeleteMenuItemCaseImpl(menuItemGateway);
    }

    @Bean
    public ReadAllMenuItemCaseImpl readAllMenuItemCase(MenuItemGateway menuItemGateway) {
        return new ReadAllMenuItemCaseImpl(menuItemGateway);
    }
}
