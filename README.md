# 🛒 Order Processing System

Este repositório contém um **mono repo** com três microserviços desenvolvidos em **Spring Boot** que trabalham juntos para processar pedidos de forma assíncrona utilizando **RabbitMQ**.

## Visão Geral

O sistema é composto pelos seguintes microserviços:

1. **Order API**: Recebe pedidos e publica mensagens na fila do **RabbitMQ**.
2. **Notification Service**: Consome a fila e envia um e-mail de confirmação ao cliente.
3. **Processor**: Consome a fila de pedidos, salva os dados no banco e notifica sobre o processamento.

## Arquitetura

```plaintext
[Cliente] ---> [Order API] ---> [RabbitMQ] ---> [Notification Service] ---> [E-mail: Pedido Criado]
                                      |
                                      v
                               [Processor] ---> [Banco de Dados]
                                      |
                                      v
                       [RabbitMQ] ---> [Notification Service] ---> [E-mail: Pedido Processado]

````
### Fluxo de Processamento:

1. O **Order API** recebe um pedido via **REST API** e o publica em uma fila do **RabbitMQ**.
2. O **Notification Service** consome essa mensagem e envia um e-mail de pedido criado ao cliente.
3. O **Processor** consome a fila, processa o pedido e o salva no banco de dados.
4. O **Processor** também publica uma nova mensagem para notificar que o pedido foi processado.
5. O **Notification Service** consome essa mensagem e envia um e-mail de pedido processado ao cliente.


## Tecnologias Utilizadas

- **Spring Boot** (para desenvolvimento dos microserviços)
- **RabbitMQ** (para comunicação assíncrona)
- **Spring Doc** (para documentação da API)
- **Banco de Dados** (PostgreSQL)
- **Docker** (para ambiente de desenvolvimento)

## Como Rodar o Projeto

### 1️⃣ Pré-requisitos:

- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- RabbitMQ rodando localmente (`docker-compose` recomendado)

### 2️⃣ Clonar o repositório:

```sh
git clone https://github.com/thallyta-castro-cv/spring-rabbitmq-order-ops.git
cd spring-rabbitmq-order-ops
````

### 3️⃣ Subir o ambiente com Docker:


````
docker-compose up -d
````

### 4️⃣ Rodar cada serviço separadamente:
````
cd order-api
mvn spring-boot:run

cd ../notification-service
mvn spring-boot:run

cd ../processor
mvn spring-boot:run
````

### Documentação da API
A Order API está documentada utilizando SpringDoc OpenAPI e pode ser acessada no seguinte endpoint:

````
http://localhost:8081/swagger-ui/index.html
````

Caso esteja rodando em outra porta, ajuste a URL de acordo com a configuração da sua aplicação.

Para acessar a especificação OpenAPI (Swagger JSON/YAML):
````
http://localhost:8080/v3/api-docs
http://localhost:8080/v3/api-docs.yaml
````

Essa documentação permite visualizar e testar os endpoints diretamente pelo navegador.


