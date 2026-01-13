# Sistema de Gerenciamento de Máquinas (SGM)

O Sistema de Gerenciamento de Máquinas (SGM) é uma API REST desenvolvida com Java e Spring Boot, voltada para o setor industrial, com o objetivo de controlar máquinas que utilizam horímetro (como empilhadeiras, transpaleteiras, entre outras) e registrar seu histórico de manutenções.

## 🏁 Objetivo

- Centralizar o cadastro de máquinas
- Controlar o status das máquinas (ativa, inativa, em manutenção)
- Registrar e consultar o histórico de manutenções
- Garantir integridade das informações de horímetro

## ⚓ Arquitetura
O projeto segue uma arquitetura em camadas, com separação clara de responsabilidades:

```
sgm
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.rokaly.sgm
│   │   │       ├── controller  # Exposição dos endpoints REST
│   │   │       ├── service     # Orquestração e regras de negócio
│   │   │       ├── repository  # Persistência de dados
│   │   │       ├── model       # Entidades de domínio e regras de negócio
│   │   │       ├── dto         # Transporte de dados entre camadas
│   │   │       ├── security    # Autenticação e autorização
│   │   │       ├── exception   # Tratamento global de erros
│   │   │       ├── springdoc   # Documentação da API
│   │   │       └── utils       # Utilitários
│   │   │           └── enums
│   │   │
│   │   └── resources
│   │
│   └── test
│       └── java
│           └── com.rokaly.sgm
│
├── pom.xml                      # Configuração do Maven
└── README.md
```

Para as regras de negócio utilizei o conceito de enriquecimento de entidades do DDD, mantendo as regras de negócio que interagem com uma entidade na mesma entidade.

## 💻 Tecnologias Utilizadas
### Backend
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (Auth0)
- Spring Validation
- Swagger / OpenAPI
- FlyWay
### Banco de Dados
- PostgreSQL

## 🔐 Segurança
A API utiliza autenticação stateless com JWT:

- Login com geração de token
- Proteção das rotas
- Controle de acesso por perfil (ADMIN / USER)

## 📗 Documentação da API
A documentação dos endpoints está disponível via Swagger:

```/swagger-ui.html```

## 🚧 Status do Projeto

✔️ Backend com Java/Spring Boot

⏳ Frontend em Angular
