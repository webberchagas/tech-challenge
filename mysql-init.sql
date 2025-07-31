CREATE TABLE users(
    user_id         CHAR(36) PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    document_number VARCHAR(11)  NOT NULL UNIQUE,
    phone           VARCHAR(20)  NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    user_type       VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP    NOT NULL,
    updated_at      TIMESTAMP
);

CREATE TABLE user_address(
    address_id   CHAR(36) PRIMARY KEY,
    street       VARCHAR(100) NOT NULL,
    complement   VARCHAR(50),
    number       VARCHAR(10)  NOT NULL,
    neighborhood VARCHAR(50)  NOT NULL,
    city         VARCHAR(50)  NOT NULL,
    state        VARCHAR(2)   NOT NULL,
    country      VARCHAR(50)  NOT NULL,
    postal_code  VARCHAR(20)  NOT NULL,
    user_id      CHAR(36)     NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE restaurant_address(
    address_id   CHAR(36) PRIMARY KEY,
    street       VARCHAR(100) NOT NULL,
    complement   VARCHAR(50),
    number       VARCHAR(10)  NOT NULL,
    neighborhood VARCHAR(50)  NOT NULL,
    city         VARCHAR(50)  NOT NULL,
    state        VARCHAR(2)   NOT NULL,
    country      VARCHAR(50)  NOT NULL,
    postal_code  VARCHAR(20)  NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP
);

CREATE TABLE restaurants(
    restaurant_id   CHAR(36)     NOT NULL PRIMARY KEY,
    restaurant_name VARCHAR(100) NOT NULL,
    cnpj            VARCHAR(14)  NOT NULL,
    cuisine_type    VARCHAR(100) NOT NULL,
    opening_hours   VARCHAR(150) NOT NULL,
    address_id      CHAR(36)     NOT NULL,
    user_id         CHAR(36)     NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    updated_at   TIMESTAMP,
    CONSTRAINT fk_restaurant_address FOREIGN KEY (address_id)
        REFERENCES restaurant_address (address_id),
    CONSTRAINT fk_restaurant_user FOREIGN KEY (user_id)
        REFERENCES users (user_id)
);



INSERT INTO users (user_id, name, document_number, phone, email, password, user_type, created_at, updated_at)
VALUES ('05e71b88-8d37-4e7f-a055-f3af8d249939', 'Cleber Silva', '12345678901', '11999999999', 'clebersilva@email.com',
        'A123456*', 'CLIENT', '2025-05-12 10:00', NULL),
       ('9116788f-cf9d-40e2-a06d-6f2a831ec362', 'Maria Silva', '98765432100', '11988888888', 'marias@email.com',
        'B123456*', 'RESTAURANT_OWNER', '2025-05-12 10:10', NULL),
       ('f4e32baf-7e6d-4a84-b2c0-91e1caae1234', 'Joana Pereira', '32165498700', '11977777777','joana.pereira@email.com',
        'C123456*', 'RESTAURANT_OWNER', '2025-05-12 10:20', NULL);


INSERT INTO user_address (address_id, street, complement, number, neighborhood, city, state, country, postal_code,
                          user_id, created_at, updated_at)
VALUES ('4a25c2fd-a05a-4b05-9e89-ea75a32adc29', 'Avenida Brasil', null, '1234', 'Jardins', 'Sorocaba', 'SP', 'Brasil',
        '01311000', '05e71b88-8d37-4e7f-a055-f3af8d249939', '2025-05-12 10:00', NULL),
       ('5bf579b0-f4ef-4c7c-bd63-97f5574896d6', 'Rua XV de Novembro', 'Sala 501', '5678', 'Centro', 'Santos', 'SP',
        'Brasil', '01015000', '05e71b88-8d37-4e7f-a055-f3af8d249939', '2025-05-12 10:00', NULL),
       ('820cc7f7-fee6-4fd5-93a5-20e4f1a271b9', 'Rua das Flores', 'Bloco A', '7890', 'Vila Nova', 'Guarulhos', 'SP',
        'Brasil', '02295000', '9116788f-cf9d-40e2-a06d-6f2a831ec362', '2025-05-12 10:10', NULL),
       ('44444444-aaaa-bbbb-cccc-444444444444','Rua São João','Apto 21','456','Centro','Santos','SP',
        'Brasil','11013-500','f4e32baf-7e6d-4a84-b2c0-91e1caae1234',NOW(),NULL);


INSERT INTO restaurant_address (address_id, street, complement, number, neighborhood, city, state, country, postal_code,
                                created_at, updated_at)
VALUES ('11111111-aaaa-bbbb-cccc-111111111111', 'Rua das Flores', 'Loja 1', '123', 'Centro', 'São Paulo', 'SP',
        'Brasil', '01000-000', NOW(), NULL),
       ('22222222-aaaa-bbbb-cccc-222222222222', 'Av. Paulista', NULL, '1578', 'Bela Vista', 'São Paulo', 'SP', 'Brasil',
        '01310-200', NOW(), NULL),
       ('33333333-aaaa-bbbb-cccc-333333333333', 'Rua do Comércio', 'Fundos', '45B', 'Jardins', 'Campinas', 'SP',
        'Brasil', '13010-110', NOW(), NULL);


INSERT INTO restaurants (restaurant_id, restaurant_name, cnpj, cuisine_type, opening_hours, address_id, user_id, created_at, updated_at)
VALUES ('aaaa1111-aaaa-bbbb-cccc-aaaa11111111', 'Bella Massa', '12345678000199', 'Italiana', 'Seg-Sáb: 11h às 22h',
        '11111111-aaaa-bbbb-cccc-111111111111', '9116788f-cf9d-40e2-a06d-6f2a831ec362', NOW(),NULL),
       ('aaaa2222-aaaa-bbbb-cccc-aaaa22222222', 'Sushi Zen', '98765432000188', 'Japonesa', 'Todos os dias: 12h às 23h',
        '22222222-aaaa-bbbb-cccc-222222222222', '9116788f-cf9d-40e2-a06d-6f2a831ec362', NOW(),NULL),
       ('aaaa3333-aaaa-bbbb-cccc-aaaa33333333', 'Brasa Burger', '19283746000155', 'Hamburgueria', 'Qua-Dom: 18h às 00h',
        '33333333-aaaa-bbbb-cccc-333333333333', 'f4e32baf-7e6d-4a84-b2c0-91e1caae1234', NOW(),NULL);