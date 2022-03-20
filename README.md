# Teste para Desenvolvedor

[![e-Precise Logo](https://www.e-precise.com.br/assets/images/logo_com_sombra.png)](https://www.e-precise.com.br/)

### Requisitos

- Quarkus (https://quarkus.io/)
- Linguagem: Java ou Kotlin (à critério do desenvolvedor)
- Estilo Arquitetural: REST
- Inserção de registros em banco de dados SQL ou NoSQL (à critério do desenvolvedor)

### Diferencial

- Testes unitários
- Testes de integração
- Criação container com a API desenvolvida

## Detalhes do teste

### Critérios de avaliação

- Arquitetura do projeto (DDD ou MVC)
- Aplicação de orientação a objetos
- Funcionalidades e funcionamento

### Funcionalidades

A API deve conter as seguintes funcionalidades:

1. Estados: GET (pesquisa com paginação)/ POST (inserir) / PUT (atualizar) / DELETE (remover)
2. Estados: GET número de registros
3. Cidades: GET (pesquisa com paginação)/ POST (inserir) / PUT (atualizar) / DELETE (remover)
4. Cidades: GET por estado (pesquisa com paginação)
5. Cidades: GET por nome (pesquisa com paginação, contendo o nome a partir de 3 caracteres)
6. Cidades: GET número de registros

### Especificações técnicas

- Criar um fork do projeto
- Descrever suas facilidades e dificuldades encontradas no processo de desenvolvimento.

## Pré-configurações (informativo)

### Rodando a aplicação em modo dev

Você pode rodar sua aplicação em modo desenvolvimento (que permite live coding), usando:

```
./mvnw quarkus:dev
```

### Empacotando e rodando a aplicação

A aplicação pode ser empacotada usando `./mvnw package`.
O artefato resultante do empacotamento é o arquivo `teste-dev-1.0.0-SNAPSHOT-runner.jar` no diretório `/target`.
Esteja ciente que não é gerado um _über-jar_, as dependências são copiadas no diretório `target/lib`.

A aplicação pode ser executada usando `java -jar target/teste-dev-1.0.0-SNAPSHOT-runner.jar`.

## Contato e Informações

### Dúvidas?
`contato@eprecise.com.br` com título `Teste para Desenvolvedor - Dúvida`

### Envio da prova?
`contato@eprecise.com.br` com título `Teste para Desenvolvedor - Finalização`. 
Incluir link com fork do repositório. 

### Prazo?
1 semana após o recebimento do teste.
