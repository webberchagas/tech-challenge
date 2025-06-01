#   ms-user-management-api

## 📚 Sobre o Projeto
API REST para gerenciamento de usuários, desenvolvida em Java com Spring Boot. Este microsserviço permite criar, consultar, atualizar e validar usuários, além de gerenciar seus endereços.

## 🚀 Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok
- Swagger/OpenAPI
- Docker
- MySQL
- Maven

## 📁 Estrutura de pastas
```
src/main/java/com/fiap/tech_challenge

├── config                 # Configurações gerais (Swagger)
├── controller             # Camada de controllers REST
│   ├── dto                # Objetos de entrada e saída
│   ├── impl               # Implementações dos controllers
│   └── type               # Enums
├── domain                # Regras de negócio e validações)
├── entity                # Entidades JPA
├── exception             # Classes de exceção e handler global
│   ├── factory           # Fábricas de exceções
├── mapper                # Conversores (MapStruct)
├── repository            # Interfaces JPA
├── service               # Lógica de negócio
│   ├── user              # Lógica relacionada a usuários
│   ├── login             # Login e autenticação
│   └── address           # Operações com endereços
└── TechChallengeApplication.java
```
## 🛠️ Como Configurar
### Pré-requisitos
- Java 21 ou superior
- Maven
- MySQL

### Clone o repositório:
```bash
   git clone https://github.com/MatheusBragaRibeiroFiap/tech-challenge.git
```

### Instale as dependências:
```bash
 ./mvnw clean install
```

## 🧩 Variáveis de ambiente
Parametrize o arquivo .env.example da seguinte forma:
```
DB_USER= usuario_do_banco
DB_PASSWORD= senha_do_banco
```

## 💻 Como rodar o projeto
### Executar com Docker Compose
Navegue até o diretório do projeto
```bash
  cd tech-challenge
```
Execute o docker-compose
```bash
  docker-compose up --build
```
### Aplicação estará disponível em:

📍 http://localhost:8080

ou

Utilize a interface interativa disponibilizada pelo Swagger

📍 http://localhost:8080/swagger-ui.html

## 📄 Documentação da API
Após rodar o projeto, acesse a documentação Swagger:

👉 http://localhost:8080/swagger-ui.html

ou

👉 http://localhost:8080/v3/api-docs

## 🧩 Exemplo de payload - Criação de usuário
``` json
{
  "name": "John Doe",
  "documentNumber": "12345678909",
  "email": "johndoe@email.com",
  "phone": "11999999999",
  "password": "A123456*",
  "userType": "RESTAURANT_OWNER",
  "address": [
    {
      "street": "Rua das Flores",
      "number": "123",
      "complement": "Apto 1",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP",
      "country": "Brasil",
      "postalCode": "01001000"
    }
  ]
}
```

## 🧑‍💻 Autoria
- Desenvolvido por Mayara Bomfim, Weber Chagas, Matheus Braga e Antonio C. Theodoro Jr
- Projeto acadêmico da pós-graduação em Java pela FIAP.

