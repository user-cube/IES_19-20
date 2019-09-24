# IES Lab 01

Resolução do primeiro guião da UC Introdução à Engenharia de Software.

## O que é um maven goal?

Quando é criada uma build de um projeto maven, percorremos uma sequência de fases e são executados os goals associados às mesmas. Um goal não passa de uma tarefa específica que contruibui para a build do projeto. É de salientar que este pode estar assoaciado a uma ou mais fases.
É importante ainda referir que os Maven Plugins são também goals, contudo, este estão associados há mesma fase.

## Quais os principais "maven goals" e a resptiva sequência de invocação?
Os principais goals por sequência de invocação, são:

1. Na fase `compile` - `compiler:compile`
2. Na fase `test-compile` - `compiler:testCompile`
3. Na fase `test` - `surefire:test`
4. Na fase `install` - `install:install`
5. Na fase `package` - `jar:jar`


## Docker
### Setup
Como estou a utilizar windows decidi virtualizar uma imagem de Ubuntu 18.04.1 de modo a que seja mais simples trabalhar com o docker.
Deste modo é necessário proceder à ativação da virtualização quer na bios do pc. Caso o problema pressista é necessário correr:
```cmd
VBoxManage modifyvm YourVirtualBoxName --nested-hw-virt on
```
### Install Docker

Para instalar o docker criei um script:
```bash
sudo add-apt-repository multiverse && sudo apt-get update &&
sudo apt-get install virtualbox &&
base=https://github.com/docker/machine/releases/download/v0.16.0 &&
curl -L $base/docker-machine-$(uname -s)-$(uname -m) >/tmp/docker-machine &&
sudo install /tmp/docker-machine /usr/local/bin/docker-machine
```
### Install Postgres
Como estamos a usar docker não existe a necessidade de instalar o postgres, podemos apenas fazer pull do mesmo. Para isso fiz um script em que o `$1` é a tag (passada por argumento) que iremos usar. O `$2` representa a password do Postgres.
```bash
docker pull postgres &&
docker pull postgres:$1 &&
mkdir -p $HOME/docker/volumes/postgres &&
docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=$2 -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres
```

#### Postgres with Dockerfile

Podemos ainda instalar o Postgres com o Dockerfile.

```bash
docker build -t eg_postgresql .
docker run --rm -P --name pg_test eg_postgresql #Run in foreground
```
Container linking: 
```bash
docker run --rm -t -i --link pg_test:pg eg_postgresql bash
```


### Testar o Postgres
Para testar o Postgres podemos utilizar apenas uma simples versão cliente do Postgres, para a sua instalação utilizei um pequeno script que fiz:
```bash
sudo apt install postgresql-client-common &&
sudo apt install postgresql-client-10
```

Para correr podemos simplesmente inicializar o cliente da seguinte forma:
```shell
psql -h localhost -U postgres -d postgres
```
