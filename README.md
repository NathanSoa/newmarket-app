# newmarket-app

![Badge em Desenvolvimento](https://img.shields.io/badge/STATUS-EM%20DESENVOLVIMENTO-brightgreen)

## Descrição

Este projeto é uma versão de um outro projeto meu, neste novo projeto, utilizarei o Spring Boot 3 com Java 17.
O intuito é aprender e descobrir as tecnologias mais atualizadas da nova versão do Spring, assim como features mais recentes do Java.

Versão do projeto com Java 8 e Spring Boot 2 <br/>
[<img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/>](https://github.com/NathanSoa/supermarket-app)

## Requisitos
### Funcionais

- RF01 - O sistema deve permitir o cadastro de produtos de supermercado,
com foto, nome e descrição.
- RF02 - O sistema deve permitir a desativação de produtos de
supermercado - não é a exclusão, apenas desativação.
- RF03 - O sistema deve permitir o alteração de produtos de
supermercado, com foto, nome e descrição.
- RF04 - O sistema deve permitir o consulta de produtos de supermercado
por nome e descrição.
- RF05 - O sistema deve permitir o cadastro de listas de compra de
mercado, adicionando produtos previamente cadastrados e suas
quantidades.
- RF06 - O sistema deve permitir a atualização de listas de compra de
mercado, removendo produtos previamente adicionados ou alterando
suas quantidades.
- RF07 - O sistema deve permitir excluir listas de mercado já cadastradas.

### Não Funcionais

- RNF01 - O sistema deve ter dois perfis: Administrator e Usuário. Os
administradores podem gerir produtos (RF01 ... 04). Os usuários podem
gerir listas (RF05 ... 07).
- RNF02 - Deve ser possível acessar estas funções por meio de interface ou
API. No caso de API, deve ser utilizado um JSON web token. No caso de
interface, deve ser feita autenticação por usuário e senha.


## Continuação do Projeto Supermercado (Categorias de Produtos e Orçamento)

Continuação da aplicação Spring Boot, com Spring Web, Spring Security,
Data JPA e Thymeleaf que atende aos seguintes novos requisitos:

- RF01 - O sistema deve permitir o cadastro de categoria de produtos.
- RF02 - O sistema deve permitir vincular categorias de produtos aos produtos cadastrados.
- RF03 - O sistema deve implementar tratamentos de paginação dinâmica na consulta de  produtos, permitindo informar qual a página e a quantidade de registros por página.
- RF04 - O sistema deve implementar ordem na paginação dinâmica na consulta de produtos.
- RF05 - Deverá ser adicionado a informação de preço nos produtos.
- RF06 - Deverá ser aprimorado o filtro de produtos permitindo busca por nome, descrição e categorias simultaneamente.
- RF07 - Deverá ser aprimorado o filtro de produtos permitindo busca por faixa de preço.
- RF08 - O sistema deve permitir que todos os filtros sejam aplicados ao mesmo tempo.
- RF09 - O sistema deve ter um endpoint para retornar o valor total de uma lista de compras.

