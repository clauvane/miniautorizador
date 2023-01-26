# Autorizador

## Overview

Este projeto é um mini autorizador.
Sempre que uma transação via cartão tiver que ser efetivada devemos verificar se ela está autorizada.

## How to run

### Step 1
Execute in terminal
```
docker-compose up -d
```

### Step 2
Execute in terminal 
```
./gradlew clean build
```

### Step 3
Execute in terminal 
```
./gradlew bootrun
```

## How to use

- Criar um usuário
- Criar um cartão

### ENDPOINTS FOR USE

```shell
curl -X GET \
http://localhost:8080/challenge/user/227bcd3d-4969-49e0-b494-cc9e39e4cc8f \
-H 'cache-control: no-cache' \
-H 'postman-token: 6111c4cb-d213-fcb4-9f89-43296d876ae9' \
```

```shell
curl -X GET \
'http://localhost:8080/challenge/user?page=0&size=10&sort=name' \
-H 'cache-control: no-cache' \
-H 'postman-token: b6f7afe5-d243-ad74-3f04-1ba57eb87e02'
```

```shell
curl -X POST \
http://localhost:8080/challenge/user \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: a33f576a-0b6a-238e-00aa-d3e5cf8e316d' \
-d '{

"name": "Clauvane Nascimento 7",
"nickName": "Clauvane",
"email": "clauvane1708@gmail.com",
"phone": "85989004944",
"birthdate": "1990-08-17"

}'
```

```shell
curl -X PUT \
http://localhost:8080/challenge/user/52a8d6fe-5ef8-4459-a8e7-6eb04830d4d6 \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: dd1a6868-8d7f-1d9f-8b9e-9090976550da' \
-d '{
"name": "Clauvane Nascimento 6",
"nickName": "Clauvane",
"email": "clauvane1708@gmail.com",
"phone": "85989004944",
"birthdate": "1990-08-17"

}'
```

```shell
curl -X DELETE \
http://localhost:8080/challenge/user/52a8d6fe-5ef8-4459-a8e7-6eb04830d4d6 \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: b6eece55-2073-c0c3-097d-c835534de205'
```

```shell
curl -X GET \
http://localhost:8080/challenge/user/227bcd3d-4969-49e0-b494-cc9e39e4cc8f/teams \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: a270f313-4605-7297-a87d-0c26311b9468'
```

```shell
curl -X POST \
http://localhost:8080/challenge/user/227bcd3d-4969-49e0-b494-cc9e39e4cc8f/teams \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 93524e47-8e90-33b1-0214-c3878b240f39' \
-d '["df9e58ab-10b4-47aa-a3c3-023b13a65e17"]'
```

## ENDPOINTS FOR TEAMS

```shell
curl -X GET \
http://localhost:8080/challenge/teams/df9e58ab-10b4-47aa-a3c3-023b13a65e17 \
-H 'cache-control: no-cache' \
-H 'postman-token: f0a80481-6e0b-5c6f-3a7f-36230b756b2a'
```

```shell
curl -X GET \
'http://localhost:8080/challenge/teams?page=0&size=10&sort=name' \
-H 'cache-control: no-cache' \
-H 'postman-token: d6ec7c7d-c8ef-6816-f95c-7c1a5744a774'
```

```shell
curl -X POST \
http://localhost:8080/challenge/teams \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: ff5cc5eb-7874-6a89-1d83-3cea35425fc8' \
-d '{

"name": "Squad 3",
"nickName": "The Spartans"

}'
```

```shell
curl -X PUT \
http://localhost:8080/challenge/teams/094d5531-f618-4187-905f-484c7866f6dd \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: ec2c8ab4-33a1-1de8-a893-767ce20ccac4' \
-d '{

"name": "Squad 3",
"nickName": "The Spartans",
"teamLeaderId": "de6f5f66-0c1a-455a-a7ce-d994a6a68615"

}'
```

```shell
curl -X DELETE \
http://localhost:8080/challenge/teams/1621a9be-112d-4da4-9d69-35941c8d4f4e \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: 3c6d184c-24a1-ea55-3026-56320180e244'
```

```shell
curl -X GET \
http://localhost:8080/challenge/teams/094d5531-f618-4187-905f-484c7866f6dd/users \
-H 'cache-control: no-cache' \
-H 'postman-token: 80e6546e-f82d-bc7e-d6e4-b0b7a14d972c'
```

```shell
curl -X POST \
http://localhost:8080/challenge/teams/094d5531-f618-4187-905f-484c7866f6dd/users \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-H 'postman-token: e1540828-99a7-d450-e0ea-1e9af23d0c8c' \
-d '["97af964f-0bd7-42f8-94d5-cbdb76ca9544"]'
```
