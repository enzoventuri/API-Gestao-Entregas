# API de Gestão de Entregas e Motoristas

API REST desenvolvida em **Spring Boot** para gestão de motoristas e entregas em uma operação logística, com autenticação e autorização baseadas em **JWT** e **Spring Security**.

## 📋 Contexto

O sistema conecta operações de pátio, frotas e motoristas às plataformas digitais da empresa, resolvendo:

- Ausência de mapeamento relacional entre motoristas e entregas.
- Endpoints expostos sem controle de acesso.
- Falta de padronização nas respostas do serviço web.

## 🚀 Tecnologias

- **Java 21**
- **Spring Boot**
    - Spring Web
    - Spring Data JPA
    - Spring Security
- **JWT** (JSON Web Token)
- **H2 Database** (em memória, para testes)
- **Lombok**
- **Maven**
- **Swagger / OpenAPI** (springdoc-openapi-starter-webmvc-ui)

## 🗂️ Arquitetura

O projeto segue arquitetura em camadas:

```
src/main/java/br/com/ctw/gestaoentrega/
├── controller/     # Endpoints REST
├── service/        # Regras de negócio
├── repository/     # Interfaces Spring Data JPA
├── entity/         # Entidades JPA
├── dto/            # Objetos de transferência de dados
├── filter/         # Filtro JWT (OncePerRequestFilter)
└── config/         # Configuração de segurança (SecurityFilterChain)
```

## 🗄️ Modelo de Dados

```sql
-- Tabela de Usuários para Autenticação Security/JWT
CREATE TABLE tb_usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- Tabela de Motoristas
CREATE TABLE tb_motorista (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnh VARCHAR(20) NOT NULL UNIQUE
);

-- Tabela de Entregas (relacionamento com Motorista)
CREATE TABLE tb_entrega (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status VARCHAR(30) NOT NULL,
    motorista_id BIGINT,
    FOREIGN KEY (motorista_id) REFERENCES tb_motorista(id)
);
```

**Relacionamentos:**
- `Entrega` → `@ManyToOne` → `Motorista`
- `Motorista` → `@OneToMany` → `Entrega` (lista de entregas)

## 🔐 Autenticação e Segurança

A API utiliza autenticação **stateless** via JWT:

1. O usuário faz login em `POST /api/auth/login` enviando `username` e `password`.
2. As credenciais são validadas via `AuthenticationManager` e `UsuarioDetailsService`, com senhas armazenadas em **BCrypt**.
3. Um token JWT é gerado contendo as *claims* do usuário (username, role, data de emissão e expiração).
4. O cliente deve enviar o token no cabeçalho `Authorization: Bearer <token>` em todas as requisições às rotas protegidas.
5. Um filtro customizado (`JwtAuthenticationFilter`, estendendo `OncePerRequestFilter`) intercepta cada requisição, extrai e valida o token, e popula o `SecurityContextHolder` quando o token é válido.

### Tabela de Rotas e Permissões

| Recurso / Endpoint      | Método | Status de Sucesso | Permissão (Role)        | Exige Token JWT? |
|--------------------------|--------|--------------------|---------------------------|-------------------|
| `/api/auth/login`        | POST   | 200 OK             | Pública                   | Não               |
| `/api/motoristas`        | POST   | 201 Created        | ROLE_ADMIN                 | Sim               |
| `/api/motoristas`        | GET    | 200 OK             | ROLE_USER, ROLE_ADMIN     | Sim               |
| `/api/entregas/{id}`     | GET    | 200 OK / 404       | ROLE_USER, ROLE_ADMIN     | Sim               |
| `/api/entregas/{id}`     | DELETE | 204 No Content     | ROLE_ADMIN                  | Sim               |

> Requisições sem token retornam **401 Unauthorized**. Requisições autenticadas mas sem a role exigida retornam **403 Forbidden**.

## ⚙️ Configuração e Execução

### Pré-requisitos
- Java 21
- Maven

### Passos

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd gestao-entrega
   ```

2. Configure o `application.properties` (ou `.yml`), incluindo:
   ```properties
   # Banco de dados H2
   spring.datasource.url=jdbc:h2:mem:gestaoentrega
   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console

   # JWT
   jwt.secret=<sua-chave-secreta>
   jwt.expiration=3600000
   ```

3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`.

5. O schema e a massa de dados iniciais são carregados a partir de `schema.sql`/`data.sql`, incluindo usuários de teste com senhas criptografadas em BCrypt.

## 📖 Documentação da API (Swagger)

Com a aplicação em execução, acesse:

```
http://localhost:8080/swagger-ui.html
```

O Swagger UI permite testar as rotas protegidas diretamente, através da configuração de autenticação **Bearer Token**.

## 🧪 Testando com Postman/Insomnia

Uma coleção de requisições está disponível na raiz do projeto, contendo:

1. **Login** — `POST /api/auth/login` para obter o token JWT.
2. **Cadastro de motorista** — `POST /api/motoristas` (requer `ROLE_ADMIN`).
3. **Listagem de motoristas** — `GET /api/motoristas` (requer `ROLE_USER` ou `ROLE_ADMIN`).
4. **Busca de entrega** — `GET /api/entregas/{id}`.
5. **Remoção de entrega** — `DELETE /api/entregas/{id}` (requer `ROLE_ADMIN`).

Para testar rotas protegidas, adicione o token retornado no login na aba **Authorization → Bearer Token** de cada requisição.

## ✅ Checklist de Validação

- [x] Login retorna token JWT válido (estrutura `header.payload.assinatura`).
- [x] Requisições sem `Authorization: Bearer <token>` retornam `401 Unauthorized`.
- [x] Requisições autenticadas sem a role exigida retornam `403 Forbidden`.
- [x] Rotas `ROLE_ADMIN` bloqueiam usuários `ROLE_USER`.
- [x] CRUD de motoristas e entregas funcionando conforme a tabela de rotas.

## 📄 Licença

Projeto acadêmico/interno — sem licença pública definida.
