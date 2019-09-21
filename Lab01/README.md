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
5- Na fase `package` - `jar:jar`
