# 📊 Finance API

API de controle financeiro pessoal com autenticação JWT, utilizando Java 17, Spring Boot, banco de dados H2 e Swagger para documentação.

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- Banco de Dados H2 (memória)
- Swagger/OpenAPI
- Maven
- GitHub

---

## 🧠 Funcionalidades

- Registro e login de usuários
- Geração de token JWT no login
- Proteção de endpoints com autenticação
- Regras baseadas em roles (`USER`, `ADMIN`)
- CRUD de receitas, despesas e investimentos
- Dashboard com resumo financeiro
- Endpoints exclusivos para administradores
- Documentação interativa com Swagger

---

## 📁 Endpoints

### 🛡️ Autenticação
| Método | Rota           | Descrição                |
|--------|----------------|--------------------------|
| POST   | `/auth/register` | Registrar novo usuário   |
| POST   | `/auth/login`    | Login e gerar token JWT  |

### 💰 Receitas
| Método | Rota             | Descrição            |
|--------|------------------|----------------------|
| POST   | `/receitas`      | Criar receita        |
| GET    | `/receitas`      | Listar receitas      |
| GET    | `/receitas/{id}` | Ver detalhes         |
| PUT    | `/receitas/{id}` | Atualizar receita    |
| DELETE | `/receitas/{id}` | Remover receita      |

### 🧾 Despesas
| Método | Rota              | Descrição            |
|--------|-------------------|----------------------|
| POST   | `/despesas`       | Criar despesa        |
| GET    | `/despesas`       | Listar despesas      |
| GET    | `/despesas/{id}`  | Ver detalhes         |
| PUT    | `/despesas/{id}`  | Atualizar despesa    |
| DELETE | `/despesas/{id}`  | Remover despesa      |

### 📈 Investimentos
Cada investimento possui: nome, tipo (ex: ações, renda fixa), valor aplicado, data de aplicação, percentual estimado de retorno, e campo opcional de resgate.

| Método | Rota                  | Descrição                   |
|--------|-----------------------|-----------------------------|
| POST   | `/investimentos`      | Criar investimento          |
| GET    | `/investimentos`      | Listar investimentos        |
| GET    | `/investimentos/{id}` | Ver detalhes                |
| PUT    | `/investimentos/{id}` | Atualizar investimento      |
| DELETE | `/investimentos/{id}` | Remover investimento        |

### 📊 Dashboard
| Método | Rota                      | Descrição                                |
|--------|---------------------------|------------------------------------------|
| GET    | `/dashboard/saldo`        | Saldo atual (receitas - despesas)        |
| GET    | `/dashboard/resumo`       | Totais de receitas, despesas, investimentos |
| GET    | `/dashboard/grafico`      | (Opcional) Dados agrupados por mês       |

### 🛠️ Administração (somente `ADMIN`)
| Método | Rota                          | Descrição                                        |
|--------|-------------------------------|--------------------------------------------------|
| GET    | `/admin/usuarios`            | Lista todos os usuários                         |
| GET    | `/admin/estatisticas`        | Totais globais de receitas, despesas, investimentos |
| GET    | `/admin/estatisticas/mensais`| Totais mensais agregados de todos os usuários   |

---

## 💻 Como Executar o Projeto Localmente

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/finance-api.git
cd finance-api
```

2. **Execute com Maven**
```bash
./mvnw spring-boot:run
```

> ✅ O projeto usa banco de dados H2 em memória, **sem necessidade de configuração prévia**.

---

## 📚 Acessar a Documentação (Swagger)

Acesse no navegador:

🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Através da interface Swagger, você pode:

- Explorar endpoints disponíveis
- Ver exemplos de payloads
- Testar autenticação com JWT

---

## 📦 Exemplos de Payloads

### 📌 Registro

```http
POST /auth/register
```

```json
{
  "login": "usuario@email.com",
  "password": "senha123",
  "role": "USER"
}
```

### 🔐 Login

```http
POST /auth/login
```

```json
{
  "login": "usuario@email.com",
  "password": "senha123"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3..."
}
```

---

## 🔐 Como Autenticar com JWT

Após o login, copie o token retornado e envie nas requisições protegidas via cabeçalho:

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

### Exemplo com `curl`

```bash
curl -H "Authorization: Bearer eyJhbGciOi..." http://localhost:8080/receitas
```

---

## 🔒 Segurança e Controle de Acesso

- ✅ Somente usuários autenticados acessam os endpoints protegidos
- 🔐 Proteção por roles:
  - `USER`: Acesso restrito aos seus próprios dados
  - `ADMIN`: Acesso total e administrativo
- 🔁 Todas as rotas protegidas por filtros
- 🚫 Rotas inexistentes autenticadas respondem com 403 (por segurança)

---

## 🧑‍💻 Autor

Desenvolvido por **Vitor de Freitas Coimbra**

---
