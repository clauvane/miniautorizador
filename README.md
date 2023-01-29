# Autorizador

## Overview

Este projeto é um mini autorizador.
Ele será capaz de criar novos cartões, consultar seu saldo e efetivar transações.
## Como rodar

### Passo 1
Execute no terminal
```
- cd docker
- docker-compose up -d
```

### Passo 2
Execute no terminal 
```
./gradlew clean build
```

### Passo 3
Execute no terminal 
```
./gradlew bootrun
```

### ENDPOINTS

#### Criar novo cartão
```shell
curl --location --request POST 'http://localhost:8080/cartoes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numeroCartao": "12345678901234567891",
    "senha": "1234567891"
}'
```

#### Consultar todos os cartões
```shell
curl --location --request GET 'http://localhost:8080/cartoes'
```

#### Consultar cartão pelo número
```shell
curl --location --request GET 'http://localhost:8080/cartoes/12345678901234567890'
```

#### Efetivar transação de débito
```shell
curl --location --request POST 'http://localhost:8080/transacoes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numeroCartao": "12345678901234567891",
    "senha": "1234567891",
    "saldo": 1
}'
```

#### Efetivar transação de crédito
```shell
curl --location --request POST 'http://localhost:8080/transacoes/recarga' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numeroCartao": "12345678901234567890",
    "senha": "1234567890",
    "saldo": 10
}'
```

## Documentação

* [Documentação Swagger](http://localhost:8080/swagger-ui/index.html)