# SISBY

Sistema de biblioteca desenvolvido em Java para execução no terminal. Os dados são mantidos em memória durante a execução.

## Funcionalidades

- Cadastro, listagem, busca e remoção de livros.
- Cadastro, listagem e busca de usuários.
- Registro e listagem de empréstimos.
- Devolução de livros.
- Validação dos dados informados.

## Requisitos

- JDK 17
- Maven 3.8 ou superior

Confirme que os dois comandos utilizam o ambiente correto:

```bash
java -version
mvn -version
```

## Executar

Na raiz do projeto:

```bash
mvn clean compile exec:java
```

Também é possível gerar e executar o JAR:

```bash
mvn clean package
java -jar target/sisby-1.0-SNAPSHOT.jar
```

## Testes

```bash
mvn test
```

O GitHub Actions executa automaticamente a compilação e os testes em pushes e pull requests.

## Estrutura

```text
src/
├── main/java/com/example/sisby/  # aplicação e regras de negócio
└── test/java/com/example/sisby/  # testes automatizados
```

## Limitações atuais

- Os dados não são persistidos após o encerramento.
- Não há autenticação ou diferenciação de perfis.
- Não há controle de prazo ou atraso dos empréstimos.
