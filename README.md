# VR Software - Sistema de Gestão de Clientes e Produtos

## Sobre o Projeto
Este é um sistema desenvolvido em Java utilizando Swing para a interface gráfica. Ele permite o gerenciamento de clientes e produtos.

## Tecnologias Utilizadas
- **Java 21**
- **Gradle 8.12.1**
- **PostgreSQL**
- **Swing (Javax.Swing)** para a interface gráfica

## Estrutura do Projeto
O projeto segue uma **arquitetura em camadas**, separando a lógica de negócio da interface gráfica e da comunicação com a API.

## Funcionalidades
- Listagem, cadastro de clientes, exclusão de clientes
- Listagem, cadastro de clientes, exclusão de Produtos

## Como Executar
1. Certifique-se de ter o **Java 21** instalado.
2. Instale o **Gradle 8.12.1+**.
3. Configure o **PostgreSQL** (arquivo VRDB.sql incluido no projeto) e ajuste as credenciais no projeto (application.properties).
4. Clone este repositório:
   ```sh
   git clone https://github.com/mullersilva/Software.git
   ```
5. Acesse a pasta do projeto:
   ```sh
   cd Software
   ```
6. Execute o projeto via Gradle:
   ```sh
   gradle bootRun
   ```

## Futuras Melhorias
- **Implementação no frontEnd para exclusão e edição de clientes e produtos (backEnd pronto)**
- **Implementação da tela de vendas**
- **Refatoração do layout do frontend**, incluindo:
  - Máscaras para campos (CPF, telefone, valores monetários, etc.)
  - Estilização de grids para melhor visualização dos dados
  - Melhor posicionamento e dimensionamento de botões
- **Refatoração para clean architecture**, visando maior manutenibilidade e legibilidade do código.

