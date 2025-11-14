# Projeto SISEPE - Sistema de Seções Eleitorais de Pernambuco

Este projeto é uma aplicação web desenvolvida para consultar informações sobre Zonas Eleitorais, Municípios, Polos e Seções de votação do estado de Pernambuco. A aplicação permite a importação de dados a partir de arquivos CSV e a realização de diversas consultas através de uma API REST.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

*   **Backend:** Java 21 e Spring Boot 3.5.7
*   **Acesso a Dados:** Spring Data JPA
*   **API:** Spring Web (REST)
*   **Migração de Banco:** Flyway
*   **Banco de Dados:** PostgreSQL
*   **Frontend:** HTML + Bootstrap (consumindo a API)
*   **Build:** Maven

## 🗃️ Estrutura de Dados

A base de dados foi modelada para armazenar as informações eleitorais, que são importadas de arquivos CSV.

### Entidades JPA

*   **`Zona`**: Representa uma zona eleitoral.
    *   `numero` (Chave Primária)
    *   `municipioSede` (Relacionamento com `Municipio`)
*   **`Municipio`**: Representa um município.
    *   `codTse` (Chave Primária)
    *   `nome`
    *   Relacionamento com `Polo` e `Zona`.
*   **`Polo`**: Representa um polo que agrupa municípios.
    *   `numero` (Chave Primária)
    *   `municipioSede` (Relacionamento com `Municipio`)
*   **`Secao`**: Representa uma seção de votação.
    *   `id` (Chave Primária, autoincremento)
    *   `numero`
    *   Relacionamentos com `Zona`, `Municipio` e `Polo`.
*   **`Usuario`**: Representa um usuário do sistema.
    *   `cpf` (Chave Primária)
    *   `nome`
    *   `email`

### Relacionamentos

*   **`Polo` e `Municipio`**: Um-para-Muitos (`1-para-n`). Um polo possui vários municípios.
*   **`Municipio` e `Zona`**: Muitos-para-Muitos (`n-para-n`). Um município pode pertencer a várias zonas e uma zona pode abranger vários municípios.
*   **`Secao` e `Zona`/`Municipio`/`Polo`**: Muitos-para-Um. Uma seção pertence a uma única zona, um único município e um único polo.

## ✨ Funcionalidades

A API REST oferece as seguintes funcionalidades:

### 1. Importação de Dados
*   `POST /importar`: Inicia a importação dos dados a partir de arquivos CSV para popular o banco de dados.

### 2. Consultas de Zonas
*   `GET /zonas/numero?numZona={id}`: Busca uma zona pelo número.
*   `GET /zonas/municipio-sede?numZona={id}`: Lista o município sede de uma zona.
*   `GET /zonas/municipios?numZona={id}`: Lista todos os municípios que compõem uma zona.
*   `GET /zonas/secoes?numZona={id}`: Lista todas as seções de uma zona.

### 3. Consultas de Municípios
*   `GET /municipios/codTse?codTse={id}`: Busca um município pelo código do TSE.
*   `GET /municipios/zonas?nomeMunicipio={nome}`: Lista as zonas de um município.
*   `GET /municipios/secoes?nomeMunicipio={nome}`: Lista as seções de um município.

### 4. Consultas de Polos
*   `GET /polos/numero?numPolo={id}`: Busca um polo pelo número.
*   `GET /polos/municipios?numPolo={id}`: Lista os municípios de um polo.
*   `GET /polos/zonas?numPolo={id}`: Lista as zonas de um polo.

### 5. Consultas de Seções
*   `GET /secoes`: Lista todas as seções cadastradas.
*   `GET /secoes/zona/{numZona}`: Lista as seções de uma zona específica.

### 6. Cadastro de Usuários (CRUD)
*   `POST /usuarios/cad`: Cadastra um novo usuário.
*   `GET /usuarios/listar/todos/ativos`: Lista todos os usuários ativos.
*   `GET /usuarios/detalhes/{cpf}`: Detalha um usuário específico.
*   `PUT /usuarios/atualizar`: Atualiza os dados de um usuário.
*   `DELETE /usuarios/excluir/{cpf}`: Realiza a exclusão lógica de um usuário.

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente local.

### Pré-requisitos
*   Java JDK 21+
*   Apache Maven
*   PostgreSQL
*   Git

### Passos

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/kauavictorss/sisepe
    cd sisepe
    ```

2.  **Configure o Banco de Dados:**
    *   Crie um novo banco de dados no PostgreSQL (usando o pgAdmin 4, por exemplo) e nomeie-o como `sisepe`.
    *   No seu sistema operacional, crie uma **variável de ambiente** chamada `SENHA_DB` e defina seu valor para a senha do seu banco de dados. O Spring Boot a utilizará automaticamente.
    *   Se necessário, ajuste o nome de usuário em `src/main/resources/application.yml`.

3.  **Execute a Aplicação:**
    *   Utilize o Maven para iniciar o servidor Spring Boot. O Flyway executará as migrações do banco de dados automaticamente na primeira inicialização.
    ```bash
    # Usando o Maven Wrapper
    ./mvnw spring-boot:run
    ```

4.  **Importe os Dados (Opcional):**
    *   Após a aplicação iniciar, você pode popular o banco de dados executando uma requisição `POST` para o endpoint de importação.
    *   Você pode usar ferramentas como **Postman** ou **Insomnia** para fazer a requisição:
        *   **URL:** `http://localhost:8080/importar`
        *   **Método:** `POST`
    *   Ou usando o curl na linha de comando:
    ```bash
    # Exemplo usando curl
    curl http://localhost:8080/importar
    ```

5.  **Acesse a Aplicação:**
    *   A API estará disponível em `http://localhost:8080`.
    *   O frontend pode ser acessado abrindo os arquivos da pasta `src/main/resources/static` em seu navegador.