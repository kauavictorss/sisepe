# SISEPE - Sistema de Seções Eleitorais de Pernambuco

**Backend**

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![OpenCSV](https://img.shields.io/badge/OpenCSV-5.12.0-0A7EA4?style=for-the-badge)

**Interface Web**

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Templates-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3.5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)

**Infraestrutura e Ferramentas**

![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Wrapper-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Project](https://img.shields.io/badge/Version-2.0.2-2F80ED?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-111111?style=for-the-badge)

O **SISEPE** é uma aplicação web para consulta, importação e organização de dados eleitorais do estado de Pernambuco. O sistema centraliza informações sobre **zonas eleitorais**, **municípios**, **polos** e **seções de votação**, oferecendo uma API REST em Spring Boot e telas web com Thymeleaf e Bootstrap.

Desenvolvido durante estágio no **Tribunal Regional Eleitoral de Pernambuco (TRE-PE)**, o projeto aplica conceitos de backend Java, persistência relacional, migrações versionadas e consumo de dados a partir de arquivos CSV.

---

## Sumário

- [Visão Geral](#visão-geral)
- [Tecnologias](#tecnologias)
- [Funcionalidades](#funcionalidades)
- [Endpoints da API](#endpoints-da-api)
- [Modelo de Dados](#modelo-de-dados)
- [Arquitetura do Projeto](#arquitetura-do-projeto)
- [Como Executar](#como-executar)
- [Interface Web](#interface-web)
- [Autor](#autor)
- [Licença](#licença)

---

## Visão Geral

O projeto foi construído para transformar arquivos CSV eleitorais em uma base consultável por API e por páginas web. A aplicação permite:

- importar dados oficiais de seções, zonas, polos e municípios;
- consultar relações entre zonas eleitorais, municípios, polos e seções;
- cadastrar, listar, atualizar e desativar usuários;
- acessar telas web para consultas operacionais;
- manter a evolução do banco de dados com Flyway.

---

## Tecnologias

### Backend

- **Java 21:** linguagem principal da aplicação.
- **Spring Boot 3.5.14:** base do backend e configuração da aplicação.
- **Spring Web:** exposição da API REST.
- **Spring Data JPA / Hibernate:** persistência e mapeamento objeto-relacional.
- **Jakarta Bean Validation:** validação dos dados recebidos pela API.
- **Lombok:** redução de código repetitivo nas classes Java.
- **OpenCSV 5.12.0:** leitura e processamento dos arquivos CSV eleitorais.

### Interface Web

- **Thymeleaf:** renderização server-side das páginas da aplicação.
- **HTML:** estrutura das telas de navegação e consulta.
- **Bootstrap 5.3.5:** estilização e componentes visuais.
- **JavaScript:** scripts estáticos em `src/main/resources/static/js`.
- **Assets estáticos:** imagens, favicon e arquivos Bootstrap em `src/main/resources/static`.

### Infraestrutura e Ferramentas

- **PostgreSQL:** banco de dados relacional usado pela aplicação.
- **Flyway:** versionamento e execução das migrações do banco.
- **Maven Wrapper:** build e execução sem depender de uma instalação global do Maven.
- **Git:** versionamento do código-fonte.

---

## Funcionalidades

### Implementado

- **Importação de dados eleitorais:** carga de arquivos CSV para popular municípios, polos, zonas e seções.
- **Consultas de zonas:** busca por número, município sede, municípios vinculados e seções.
- **Consultas de municípios:** busca por código TSE, zonas, polos relacionados e seções.
- **Consultas de polos:** busca por número, municípios participantes e zonas relacionadas.
- **Consultas de seções:** listagem geral e filtro por zona eleitoral.
- **Gestão de usuários:** cadastro, listagem de ativos, detalhamento, atualização e exclusão lógica.
- **Interface web:** páginas HTML renderizadas pelo Spring MVC para navegação e consultas.

### Roadmap

- [ ] Adicionar autenticação e autorização com Spring Security.
- [ ] Criar documentação interativa com OpenAPI/Swagger.
- [ ] Ampliar cobertura de testes unitários e de integração.
- [ ] Adicionar paginação e filtros avançados nas consultas.
- [ ] Melhorar tratamento global de erros com respostas padronizadas.

---

## Endpoints da API

> Base local: `http://localhost:8080`

### Importação

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `POST` | `/importar` | Importa os CSVs disponíveis em `src/main/resources/db/migration/csv`. |

### Zonas

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/zonas/numero?numZona={id}` | Busca uma zona eleitoral pelo número. |
| `GET` | `/zonas/municipio-sede?numZona={id}` | Retorna o município sede de uma zona. |
| `GET` | `/zonas/municipios?numZona={id}` | Lista os municípios vinculados a uma zona. |
| `GET` | `/zonas/secoes?numZona={id}` | Lista as seções de uma zona. |

### Municípios

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/municipios/codTse?codTse={id}` | Busca um município pelo código TSE. |
| `GET` | `/municipios/zonas?nomeMunicipio={nome}` | Lista as zonas de um município. |
| `GET` | `/municipios/polos?nomeMunicipio={nome}` | Lista municípios que pertencem ao mesmo polo. |
| `GET` | `/municipios/secoes?nomeMunicipio={nome}` | Lista as seções de um município. |

> As consultas com `nomeMunicipio` esperam o nome em letras maiúsculas.

### Polos

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/polos/numero?numPolo={id}` | Busca um polo pelo número. |
| `GET` | `/polos/municipios?numPolo={id}` | Lista os municípios de um polo. |
| `GET` | `/polos/zonas?numPolo={id}` | Lista as zonas relacionadas a um polo. |

### Seções

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `GET` | `/secoes` | Lista todas as seções cadastradas. |
| `GET` | `/secoes/zona/{numZona}` | Lista as seções de uma zona específica. |

### Usuários

| Método | Endpoint | Descrição |
| --- | --- | --- |
| `POST` | `/usuarios/cad` | Cadastra um novo usuário. |
| `GET` | `/usuarios/listar/todos/ativos` | Lista usuários ativos. |
| `GET` | `/usuarios/detalhes/{cpf}` | Detalha um usuário por CPF. |
| `PUT` | `/usuarios/atualizar` | Atualiza os dados de um usuário. |
| `DELETE` | `/usuarios/excluir/{cpf}` | Realiza exclusão lógica de um usuário. |

<details>
  <summary>Exemplo de JSON para cadastro de usuário</summary>

```json
{
  "cpf": "00000000000",
  "nome": "Nome do Usuário",
  "email": "usuario@email.com"
}
```

</details>

---

## Modelo de Dados

| Entidade | Responsabilidade | Chave principal |
| --- | --- | --- |
| `Zona` | Representa uma zona eleitoral e seus municípios relacionados. | `numero` |
| `Municipio` | Representa um município identificado pelo código TSE. | `codTse` |
| `Polo` | Agrupa municípios e possui um município sede. | `numero` |
| `Secao` | Representa uma seção de votação vinculada a zona, município e polo. | `id` |
| `Usuario` | Representa usuários cadastrados no sistema. | `cpf` |

### Relacionamentos

| Relação | Cardinalidade | Descrição |
| --- | --- | --- |
| `Polo` -> `Municipio` | Um para muitos | Um polo pode agrupar vários municípios. |
| `Municipio` -> `Zona` | Muitos para muitos | Um município pode pertencer a várias zonas, e uma zona pode abranger vários municípios. |
| `Secao` -> `Zona` | Muitos para um | Uma seção pertence a uma única zona. |
| `Secao` -> `Municipio` | Muitos para um | Uma seção pertence a um único município. |
| `Secao` -> `Polo` | Muitos para um | Uma seção pertence a um único polo. |

---

## Arquitetura do Projeto

O SISEPE utiliza uma organização por domínio funcional. Cada pacote concentra entidade, DTO, repositório e controller do recurso correspondente, mantendo o código próximo do contexto de negócio.

```text
sisepe/
├── src/main/java/sisepe/api/
│   ├── controllers/       # Controllers de navegação das páginas web
│   ├── imports/           # Serviço e endpoint de importação dos CSVs
│   ├── municipio/         # Domínio, DTO, repositório e REST de municípios
│   ├── polo/              # Domínio, DTO, repositório e REST de polos
│   ├── secao/             # Domínio, DTO, repositório e REST de seções
│   ├── usuario/           # Domínio, DTOs, repositório e REST de usuários
│   ├── zona/              # Domínio, DTO, repositório e REST de zonas
│   └── SisepeApplication.java
│
├── src/main/resources/
│   ├── db/migration/      # Scripts Flyway e arquivos CSV
│   ├── static/            # Assets estáticos: imagens, JS e Bootstrap
│   ├── templates/         # Telas Thymeleaf
│   └── application.yml    # Configurações da aplicação
│
└── src/test/java/         # Testes automatizados
```

### Fluxo de Importação

1. O endpoint `POST /importar` aciona o serviço de importação.
2. O `ImportService` lê os arquivos CSV empacotados em `resources`.
3. Os registros são convertidos para entidades JPA.
4. Os relacionamentos entre polos, municípios, zonas e seções são montados.
5. Os dados são persistidos no PostgreSQL.

---

## Como Executar

### Pré-requisitos

- JDK 21+
- PostgreSQL
- Git
- Maven instalado ou Maven Wrapper do projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/kauavictorss/sisepe.git
cd sisepe
```

### 2. Configurar o banco de dados

Crie um banco PostgreSQL chamado `sisepe`.

```sql
CREATE DATABASE sisepe;
```

Configure a senha do banco por variável de ambiente:

```bash
# Linux/macOS
export SENHA_DB=sua_senha

# Windows PowerShell
$env:SENHA_DB="sua_senha"
```

Se necessário, ajuste usuário, senha ou URL em `src/main/resources/application.yml`.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/sisepe
    username: postgres
    password: ${SENHA_DB}
```

### 3. Executar a aplicação

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows PowerShell
.\mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

### 4. Importar os dados eleitorais

Após iniciar o servidor, execute a importação:

```bash
curl -X POST http://localhost:8080/importar
```

Resposta esperada:

```text
Importação concluída com sucesso!
```

---

## Interface Web

| Página | URL |
| --- | --- |
| Início | `http://localhost:8080/` |
| Login | `http://localhost:8080/login` |
| Menu | `http://localhost:8080/menu` |
| Usuários | `http://localhost:8080/pages/usuarios` |
| Zonas | `http://localhost:8080/pages/zonas` |
| Municípios | `http://localhost:8080/pages/municipios` |
| Polos | `http://localhost:8080/pages/polos` |
| Seções | `http://localhost:8080/pages/secoes` |

---

## Autor

<div align="center">
  <img src="https://github.com/kauavictorss.png" width="140" alt="Foto de perfil de Kauã Victor" />
  <h3>Kauã Victor Silva dos Santos</h3>

  <a href="https://github.com/kauavictorss">
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />
  </a>
  <a href="https://www.linkedin.com/in/kaua-victor-santos/">
    <img src="https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />
  </a>
</div>

---

## Licença

Este projeto está licenciado sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
