# Finsch
Repositório destinado a avaliação prática

🏗️ Decisões Arquiteturais

   - Spring Boot 2.7.18: Framework principal que simplifica a configuração e execução da aplicação. Como a proposta foi apresentada com Java 11, esta é a última versão do Spring disponível.

   - Arquitetura em camadas (MVC): Separação clara entre controller, service e repository.

   - Persistência com Spring Data JPA: Abstração de acesso a dados com uso de entidades e repositórios.

   - Banco em memória (H2): Utilizado para facilitar testes e desenvolvimento local.

   - Validação com Bean Validation: Garantia de integridade dos dados via anotações.

   - Documentação com Springdoc OpenAPI: Interface interativa via Swagger UI.

   - Lombok: Redução de boilerplate com geração automática de getters, setters e construtores.

⚙️ Configuração e Execução

✅ Pré-requisitos

    Java 11 instalado

    Maven 3.6+ instalado

🚀 Subida do Projeto

    Clonar o repositório

    git clone https://github.com/cavalc00/finsch.git
    cd finsch

    Compilar e rodar

    mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.

🔍 Acesso ao Console H2

    URL: http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:mem:fintrackdb

    Usuário: sa

    Senha: (em branco)

📚 Documentação da API

Acesse http://localhost:8080/swagger-ui.html para visualizar e testar os endpoints da API.