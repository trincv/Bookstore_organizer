# Sistema de Gerenciamento de Biblioteca

Sobre o Projeto

Este é um Sistema de Gerenciamento de Biblioteca desenvolvido em Java, projetado para ser modular, escalável e de fácil manutenção. O sistema utiliza uma arquitetura de plugins que permite estender a funcionalidade de forma dinâmica, adicionando novos módulos sem a necessidade de alterar o código principal.

O objetivo do projeto é gerenciar os processos essenciais de uma biblioteca, incluindo o cadastro de usuários e livros, o controle de empréstimos e a geração de relatórios.

# Tecnologias Utilizadas

JavaFX: Para a construção da interface gráfica do usuário (UI).

Maven: Como ferramenta de automação de build e gerenciamento de dependências do projeto.

Jakarta Validation (Bean Validation): Para validação de dados em nível de aplicação (ex: formato de e-mail, restrições de tamanho de campo).

JDBC: Para a camada de acesso a dados (DAO), que se comunica com o banco de dados relacional.

JUnit 5 e Mockito: Para testes unitários da lógica de negócio, garantindo a qualidade e confiabilidade do código.

Banco de Dados Relacional: (Ex: MySQL) para persistência dos dados.

# Arquitetura do Projeto

A arquitetura do sistema segue um design modular e em camadas, focado na separação de responsabilidades.

Camada de UI (Views): Responsável pela interface do usuário. As views são criadas por cada plugin e se integram ao menu principal da aplicação.

Camada de Serviço (Services): Contém a lógica de negócio da aplicação. As classes de serviço orquestram as operações, realizam validações complexas (como verificar a disponibilidade de cópias de um livro) e coordenam o trabalho de um ou mais DAOs.

Camada de Acesso a Dados (DAOs): Realiza a comunicação direta com o banco de dados. Cada DAO é responsável pelas operações CRUD (Create, Read, Update, Delete) de uma entidade específica.

Sistema de Plugins: O núcleo da aplicação carrega plugins dinamicamente. Cada plugin é uma entidade autônoma que implementa a interface IPlugin, fornecendo seu próprio botão de navegação e sua própria interface de conteúdo.

# Funcionalidades Principais

O sistema oferece as seguintes funcionalidades através de seus plugins:

1. Gerenciamento de Usuários

    CRUD completo: Cadastro, consulta, edição e exclusão de usuários.

    Validação de Dados: Garantia de unicidade de e-mail e validação de formato e tamanho dos campos.

2. Gerenciamento de Livros

    CRUD completo: Cadastro, consulta, edição e exclusão de livros.

    Controle de Cópias: O sistema rastreia o número de cópias disponíveis de cada livro.

3. Gerenciamento de Empréstimos

    Registro de Empréstimos: Associa um usuário a um livro, com a devida verificação de disponibilidade de cópias.

    Registro de Devoluções: Atualiza o status de um empréstimo, registrando a data de devolução e incrementando o número de cópias disponíveis do livro.

4. Relatórios

    Relatório de Livros Emprestados: Exibe uma lista de todos os livros que estão atualmente emprestados, com informações sobre o título, autor, usuário e data do empréstimo.

# Como Executar o Projeto

Pré-requisitos:

Java Development Kit (JDK) 11 ou superior.

Maven 3.6.0 ou superior.

Um servidor de banco de dados (ex: MySQL) configurado e em execução.

# Configuração do Banco de Dados:

        Crie um banco de dados vazio.

        Configure as credenciais de acesso no arquivo de conexão (DBConnection.java).

        Execute os scripts SQL para criar as tabelas users, books e loans.

# Build e Execução:

Abra o terminal na pasta raiz do projeto.

Execute o comando Maven para compilar e empacotar o projeto:

    mvn clean install
    
Execute o comando Maven para executar o projeto:
