INSERT INTO users (user_id, name, document_number, phone, email, password, user_type,created_at, updated_at)
VALUES ('05e71b88-8d37-4e7f-a055-f3af8d249939', 'Cleber Silva', '12345678901', '11999999999', 'clebersilva@email.com','A123456*', 'CLIENT','2025-05-12 10:00', NULL),
       ('9116788f-cf9d-40e2-a06d-6f2a831ec362', 'Maria Silva', '98765432100', '11988888888', 'marias@email.com','B123456*', 'RESTAURANT_OWNER','2025-05-12 10:10', NULL);


INSERT INTO user_address (address_id, street, complement, number, neighborhood, city, state, country, postal_code, user_id, created_at, updated_at)
VALUES
    ('4a25c2fd-a05a-4b05-9e89-ea75a32adc29', 'Avenida Brasil', null, '1234', 'Jardins', 'Sorocaba', 'SP', 'Brasil', '01311000', '05e71b88-8d37-4e7f-a055-f3af8d249939', '2025-05-12 10:00', NULL),
    ('5bf579b0-f4ef-4c7c-bd63-97f5574896d6', 'Rua XV de Novembro', 'Sala 501', '5678', 'Centro', 'Santos', 'SP', 'Brasil', '01015000', '05e71b88-8d37-4e7f-a055-f3af8d249939', '2025-05-12 10:00', NULL),
    ('820cc7f7-fee6-4fd5-93a5-20e4f1a271b9', 'Rua das Flores', 'Bloco A', '7890', 'Vila Nova', 'Guarulhos', 'SP', 'Brasil', '02295000', '9116788f-cf9d-40e2-a06d-6f2a831ec362', '2025-05-12 10:10', NULL);
