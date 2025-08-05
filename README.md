#   ms-user-management-api

## 📚 Sobre o Projeto
Esta API RESTful foi desenvolvida para facilitar o gerenciamento de usuários e seus endereços, restaurantes e seus respectivos cardápios. A aplicação permite criar, consultar, atualizar e remover registros de forma simples, segura e escalável aplicando as melhores praticas do Clean Architecture com os princípios do SOLID.

🚀 Funcionalidades
- Usuários
    - ✅ Cadastro de novos usuários
    - 🔍 Consulta de usuários por ID ou listagem geral 
    - ✏️ Atualização de informações do usuário
    - ❌ Remoção de usuários
 - Endereço
    - ✅ Cadastro de novos endereços
    - 🔍 Consulta de endereços por ID do endereço
    - 🔍 listagem de todos os endereços de um usuário
    - ✏️ Atualização das informações do endereço
    - ❌ Remoção de endereços
- Restaurantes 
  - ✅ Registro de restaurantes 
  - 🔍 Consulta de restaurantes por ID ou listagem dos restaurantes
  - ✏️ Atualização de dados do restaurante 
  - ❌ Remoção de restaurantes
- Cardápios 
  - ✅ Adição de itens ao cardápio de um restaurante 
  - 🔍 Consulta de um item por ID ou listagem de todos os itens de um restaurante 
  - ✏️ Edição de itens do cardápio 
  - ❌ Exclusão de itens do cardápio
- Login
  - 🔍 Validação de acesso
  - ✏️ Atualização de senha
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
- Jacoco
## 📁 Estrutura de pastas
```
src/main/java/com/fiap/tech_challenge

├── config                     # Configurações gerais (Swagger) e Beans de instanciação dos UseCase
├── core                       # Camada responsavel por encapsular camadas internas
│   ├── adapters               # Interfaces gateway
│   ├── application            # Controller Rest que utiliza os UseCase
│   ├── domain                 # Camada que encapsula Entity e UseCase
│   │   ├── model              # Entidades de dominio e validações
│   │   └── usecases           # Regra de negocio 
│   │       ├── address        # Operações com endereços
│   │       ├── login          # Login e autenticação
│   │       ├── menu           # Operações com os itens dos cardapios
│   │       ├── restaurant     # Operações com os restaurantes
│   │       └── user           # Lógica relacionada a usuários 
│   ├── dto                    # Objetos de entrada e saída
│   └── exceptiontype          # Classes de exceção e handler global
├── infrastructure             # Camada que encapsula camadas externas  
│   └── persistence            # Camada para permanência de dados       
│       ├── entity             # Entidades JPA    
│       ├── gateway            # Implementação dos gateways com o framework
│       ├── mapper             # Conversores (MapStruct)
│       └── repository         # Interfaces JPA
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
Renomei o arquivo “.env.example” para ".env" e preencha-o da seguinte maneira:
```
DB_URI=jdbc:mysql://mysql-container:3306/db-usermanagement?useSSL=false&serverTimezone=UTC&allowPublicK
eyRetrieval=true
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

## ✅ Teste
Para cobertura de testes foi utilizado a ferramenta [Jacoco](https://www.eclemma.org/jacoco/) atingindo o percentual total de 82%.
Para rodar os testes unitários, na raiz do projeto, execute o seguinte comando:

> mvn clean verify

O relatório de cobertura pode ser encontrado dentro da pasta `./target`. Para acessar o relatório web acesse:

> taget/site/jacoco/index.html

![image](https://github.com/MatheusBragaRibeiroFiap/tech-challenge/tree/v1.0.0/src/main/resources/assets/cobertura-testes.png)


## 🧑‍💻 Autoria
- Desenvolvido por Mayara Bomfim, Webber Chagas, Matheus Braga e Antonio C. Theodoro Jr
- Projeto acadêmico da pós-graduação em Java pela FIAP.
