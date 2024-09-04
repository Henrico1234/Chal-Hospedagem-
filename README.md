# Projeto de Gerenciamento de Hospedagem

Este projeto é uma aplicação Java para gerenciamento de hospedagens em chalés, permitindo que os usuários façam reservas e gerenciem dados relacionados aos chalés e clientes.

## Funcionalidades

- **Cadastro de Chalés:** Adiciona novos chalés ao sistema, incluindo detalhes como localização, capacidade, valor para alta e baixa estação.
- **Cadastro de Clientes:** Adiciona novos clientes ao sistema, incluindo detalhes como RG, CEP, ESTADO(sigla), entre outros.
- **Cadastro de Hospedagens:** Permite aos clientes fazerem reservas em chalés disponíveis, verificando previamente a disponibilidade e a capacidade do chalé.
- **Cálculo de Valores:** Calcula automaticamente o valor da hospedagem com base no período da reserva e em um possível desconto fornecido pelo usuário.

## Configuração do Ambiente

### Requisitos

- **Java Development Kit (JDK)**: Certifique-se de ter o JDK instalado.
- **IDE (Eclipse, IntelliJ, etc.)**: Recomenda-se o uso de uma IDE para facilitar o desenvolvimento.
- **PostgreSQL**: Banco de dados utilizado para armazenar as informações.

### Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL para o projeto.
2. Abra uma query e crie as tabelas anexadas no arquivo Banco de Dados
3. Modifique as configurações de conexão com o banco de dados na classe `FabricaDeConexao` para se adequar ao seu ambiente. Substitua os valores de URL, usuário e senha para refletirem as credenciais do seu banco de dados.
das
```java
// Exemplo de configuração na classe FabricaDeConexao
public class FabricaDeConexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/seuBancoDeDados";
    private static final String USER = "seuUsuario";
    private static final String PASSWORD = "suaSenha";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```
# Aplicação de Gerenciamento de Chalés

## Executando a Aplicação

1. **Importe o projeto na sua IDE.**
   - Abra sua IDE preferida e importe o projeto. Certifique-se de que todas as dependências foram corretamente resolvidas.

2. **Compile e execute a aplicação.**
   - Compile o projeto para verificar se há erros. Em seguida, execute a aplicação para iniciar a interface gráfica.

3. **Utilize a interface gráfica para:**
   - **Cadastrar Chalés:** Insira os dados do chalé, incluindo localização, capacidade, e valores para alta e baixa estação.
   - **Cadastrar Clientes:** Insira os dados do cliente, como nome, CEP e RG.
   - **Realizar uma Hospedagem:** Selecione o chalé e o cliente, informe as datas de início e fim, e verifique a disponibilidade e capacidade do chalé. O sistema calculará o valor final com base nas datas e aplicará o desconto, se houver.

## Passos para Execução

### 1. Cadastrar um Chalé
   - Insira os dados do chalé, incluindo:
     - Localização
     - Capacidade
     - Valores para alta e baixa estação

### 2. Cadastrar um Cliente
   - Insira os dados do cliente, como:
     - Nome
     - CEP
     - RG

### 3. Realizar uma Hospedagem
   - Selecione o chalé e o cliente.
   - Informe as datas de início e fim.
   - Verifique a disponibilidade e a capacidade do chalé.
   - O sistema calculará o valor final com base nas datas e aplicará o desconto, se houver.

## Observações

- **DATAS:** Todas as datas são no formato ANO/MÊS/DIA.
- **Validação de Datas e Capacidade:** Certifique-se de que as datas de hospedagem e a capacidade do chalé sejam validadas corretamente antes de confirmar uma reserva.
- **Cálculo de Valor:** O código está preparado para calcular o valor da hospedagem considerando as estações do ano e aplicar um desconto, caso fornecido.
- **RG do Cliente:** certifique-se de que o rg que vc está tentando inserir é unico, so assim será possivel cadastrar o cliente.
- **Estado do Cliente:** Sempre será a sigla do estado, por exemplo: Distrito Federal = DF.
  
