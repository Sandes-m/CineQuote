# CineQuote - Catálogo Cinematográfico

---

## 🎬 Visão Geral do Projeto / Project Overview

O CineQuote é uma aplicação Full-Stack interativa de citações cinematográficas. Desenvolvido em Java (Spring Boot) no Back-end, conta com a integração de IA Generativa (Google Gemini) e da OMDb API, orquestrando a persistência relacional através do PostgreSQL. Já o Front-end desenvolvido em JavaScript é responsável pelo consumo da API REST entregando uma interface responsiva e acessibilidade através da Web Speech API.

*EN: CineQuote is an interactive Full-Stack application for iconic cinema quotes. Built with Java (Spring Boot) on the Back-end, it integrates Generative AI (Google Gemini) and the OMDb API, orchestrating relational persistence through PostgreSQL. The Front-end, developed in JavaScript, consumes the REST API to deliver a responsive user interface and native accessibility features powered by the Web Speech API.*

**Objetivo (Objective):** Desenvolver uma aplicação Web Full-Stack completa e interativa, aplicando na prática conceitos arquiteturais e boas práticas da engenharia como **Prompt Engineering**, **Data Transfer Objects (DTOs)**, **JPQL**, **Domain-Driven Design (DDD)**, **Spring Profiles** e **UX/Acessibilidade**.

---

## 📂 Estrutura do Projeto / Project Structure

A aplicação segue o modelo arquitetural **Full-Stack** desacoplada. O **Back-end (servidor)** utiliza **Spring Boot (Java 21)** com **Maven**, 
organizado sob os princípios de **SRP** e **DDD**. Já o **Front-end (cliente)** é estruturado de forma leve em **HTML5**, **CSS3** e **JavaScript**.

````text
cinequote/
├── Back-end/
│   └── cinequote/
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/br/com/sandes/cinequote/
│       │   │   │   ├── config/
│       │   │   │   │   └── CORSConfig.java
│       │   │   │   │
│       │   │   │   ├── controller/
│       │   │   │   │   └── ObraController.java
│       │   │   │   │
│       │   │   │   ├── dto/
│       │   │   │   │   ├── FrasesDTO.java
│       │   │   │   │   ├── ObraDTO.java
│       │   │   │   │   └── OmdbDTO.java
│       │   │   │   │
│       │   │   │   ├── exception/
│       │   │   │   │   ├── AiRequestFailedException.java
│       │   │   │   │   └── EnvVarNotFoundException.java
│       │   │   │   │
│       │   │   │   ├── model/
│       │   │   │   │   ├── Frases.java
│       │   │   │   │   ├── Genero.java
│       │   │   │   │   └── Obra.java
│       │   │   │   │
│       │   │   │   ├── repository/
│       │   │   │   │   ├── FrasesRepository.java
│       │   │   │   │   └── ObraRepository.java
│       │   │   │   │
│       │   │   │   ├── service/
│       │   │   │   │   ├── AiClient.java
│       │   │   │   │   ├── ObraService.java
│       │   │   │   │   └── OmdbClient.java
│       │   │   │   │
│       │   │   │   ├── utils/
│       │   │   │   │   ├── DataMapper.java
│       │   │   │   │   ├── DataSeeder.java
│       │   │   │   │   └── IDataMapper.java
│       │   │   │   │
│       │   │   │   └── CinequoteApplication.java
│       │   │   │
│       │   │   └── resources/
│       │   │       └── application.properties
│       │   │
│       │   └── test/
│       │       └── java/br/com/sandes/cinequote/
│       │           └── CinequoteApplicationTests.java
│       │
│       └── pom.xml
│
├── Front-end/
│   ├── index.html
│   ├── style.css
│   └── script.js
│
└── README.md
````

---

## 💻 Tecnologias e Ferramentas / Tecnologies & Tools

| Linguagens de Programação |                                                      Framework                                                      | Gerenciador | Controle de Versão | Ferramentas de Apoio & APIs |
| :---: |:-------------------------------------------------------------------------------------------------------------------:| :---: | :---: | :---: |
| ![Java](https://img.shields.io/badge/Java%2021-ED8B00?style=flat-square&logo=openjdk&logoColor=white)&nbsp;![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black)<br/>![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white)&nbsp;![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white) | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white) | ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white) | ![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white) | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)&nbsp;![Google Gemini](https://img.shields.io/badge/Google%20Gemini-4285F4?style=flat-square&logo=googlegemini&logoColor=white)&nbsp;![OMDb API](https://img.shields.io/badge/OMDb%20API-FFB81C?style=flat-square&logo=imdb&logoColor=black)<br/>![Web Speech API](https://img.shields.io/badge/Web%20Speech%20API-5A67D8?style=flat-square&logo=w3c&logoColor=white)&nbsp;![Jackson JSON](https://img.shields.io/badge/Jackson%20JSON-000000?style=flat-square&logo=json&logoColor=white)&nbsp;![Antigravity (AI)](https://img.shields.io/badge/Antigravity%20(AI)-7F3FBF?style=flat-square&logo=googlegemini&logoColor=white) |

---

## 🧠 Lições de Arquitetura e Crescimento (What I learned)

Busquei consolidar aprendizados teóricos junto a pesquisas por melhorias e metodologias de desenvolvimento em conjunto com 
Inteligência Artificial.

### 1. Arquitetura de Transferência de Dados (DTOs)
Aprendi a confeccionar classes **DTO** utilizando **`Java Records`** para manipulação de dados tanto de entrada quanto de saída,
permitindo assim o mapeamento de dados vindos de APIs externas para os meus objetos Java **`(Obra e Frases)`** e a transformação
das minhas entidades num único objeto JSON final **`(ObraDTO)`** formatado e destinado para o consumo do Front-end.

### 2. Aplicando Domain-Driven Design e Aggregate Root (Raiz de Agregação & DDD)
Durante o desenvolvimento em conjunto com a Inteligência Artificial, me deparei com o conceito de **`Domain-Driven Design (DDD)`**
ao tomar a decisão arquitetural de que o projeto giraria em torno da entidade **`Obra`**. Ao me aprofundar no conceito,
percebi que tinha definido **`Obra`** como a **`Raiz de Agregação (Aggregate Root)`**, estabelecendo que ela controlaria 
as regras e o ciclo de vida das entidades associadas, como **`Frases`**. Essa descoberta me possibilitou centralizar a 
integridade dos dados, simplificou as responsabilidades dos **`Services`** e **`Repositories`**, além de me permitir definir
com clareza o contrato de comunicação entre o **`Back-end`** e o **`Front-end`**.

### 3. Evolução no uso de Prompt Engineering
Busquei aprimorar meu domínio de Prompt Engineering aplicando a metodologia de **`Pair programming`** com a Inteligência Artificial.
Em vez de comandos genéricos e sem contexto, passei a estruturar instruções claras com contexto técnico, exemplos e restrições
de design para guiar a IA no desenvolvimento de um **`Front-end`** sólido, responsivo e limpo, adotando boas práticas de manipulação do DOM,
tratamento de estados de carregamento e a integração de acessibilidade com a **`Web Speech API`**.

---

## 🚀 Teste o Projeto / Test the Project

### 1. Acesso Online (Sem Instalação)

A aplicação está totalmente disponível na nuvem para teste imediato:

* 🌐 **Aplicação Web (Front-end):** [https://cinequote-chi.vercel.app](https://cinequote-chi.vercel.app)
* ⚙️ **API REST (Back-end):** [https://cinequote-api-jxtg.onrender.com/obra](https://cinequote-api-jxtg.onrender.com/obra)

> **(AVISO)**  
> Como o Back-end e o banco estão hospedados no plano gratuito do Render, o servidor pode levar de 30 a 50 segundos para "acordar" 
> (*cold start*) na primeira requisição após um período de inatividade.

### 2. Execução Local (Desenvolvimento)

#### Pré-requisitos

* **Java 21** instalado
* **PostgreSQL** instalado e em execução
* Chave de API do **Google Gemini** ([Google AI Studio](https://aistudio.google.com/))
* Chave de API da **OMDb API** ([OMDb API Key](https://www.omdbapi.com/apikey.aspx))
* Extensão **Live Server** no VS Code (ou navegador moderno)

#### Passo a Passo

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/Sandes-m/CineQuote.git
   cd CineQuote
   ```

2. **Configure as Variáveis de Ambiente: Defina as variáveis no seu sistema ou no arquivo application.properties:**

* **`GEMINI_API_KEY:`** Sua chave do Google Gemini.
* **`OMDB_API_KEY:`** Sua chave da OMDb API.
* **`DB_URL, DB_HOST, CQ_DB:`** URL e nome da base no PostgreSQL.
* **`DB_USER e DB_PASSWORD:`** Credenciais do seu banco de dados.

3. **Inicie o Back-end (Spring Boot):**

   ```bash
   ./mvnw spring-boot:run # - No Windows (PowerShell) ou Linux/macOS
   ```
   ```bash
   mvnw.cmd spring-boot:run # - No Windows (CMD)
   ```
   O Back-end iniciará em http://localhost:8080 com o perfil dev ativo, permitindo a ingestão inicial via **`DataSeeder`**.


4. **Inicie o Front-end:**

* Abra a pasta **`Front-end/`** no VS Code.
* Clique com o botão direito no **`index.html`** e selecione "Open with Live Server" (geralmente em http://127.0.0.1:5500).
* O script detecta automaticamente o ambiente local e aponta as requisições para http://localhost:8080.

---

## 💬 Contatos / Contacts

| Network Profile | E-mail | Social Profile |
|------------------|------------------|------------------|
| [![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/paulo-m-sandes-51742422b) | [![Gmail](https://img.shields.io/badge/Gmail-EFEFEF?style=flat&logo=gmail&logoColor=555555)](mailto:paulohenriquesandes@gmail.com) | [![Instagram](https://img.shields.io/badge/Instagram-E1306C?style=flat&logo=instagram&logoColor=white)](https://www.instagram.com/sandes_xz) |