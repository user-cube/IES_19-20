# Multi-layer web applications with Spring Boot
Learning outcomes
* Start a web application with Spring Boot and Spring Initializr, combining the appropriate 
* starter” dependencies.
* Create and persist entities into a relational database using Spring Data. 
* Expose a RESTful API, using Spring Annotations.

## Architectural Diagram
!["Diagram"](Diagram.png)

## Test API

* GET Method
<br>
!["GET"](get.png)
<br>
<br>
* POST Method
<br>
!["POST"](post.png)
<br>
<br>
* PUT Method
<br>
!["PUT"](put.png)

## What happens to your data when the application is stopped and restarted? How could you change  that behavior?
A API retorna todos os valores de origem.
A solução para resolver este problema seria separar a base de dados da API.

## Why is that the Employee entity does not have getters and setters defined? (tip: Lombok)

A entidade `Emplyoee` tem uma anotação `@Data`, esta anotação faz com que o `Lombok` gere os getters e setters de forma automática não sendo necessário escrever os mesmos.

## Explain the annotations @Table, @Colum, @Id found in the Employee entity
De acordo com o site https://codesjava.com/ :
The Column annotation is used to specify the mapped column for a persistent property or field. If no Column annotation is specified, the default value will be applied.

The Table annotation is used to specify the primary table for the annotated entity. Additional tables may be specified using SecondaryTable or SecondaryTables annotation.

The @Id annotation is used to specify the primary key of an entity. The field or property to which the Id annotation is applied should be one of the following types:
1. Any Java primitive type
2. Any primitive wrapper type.
3. String
4. java.util.Date
5. java.sql.Date
6. java.math.BigDecimal
7. java.math.BigInteger
It is contained in the javax.persistence package. The mapped column for the primary key of the entity is assumed to be the primary key of the primary table. If no Column annotation is specified, the primary key column name is assumed to be the name of the primary key property or field.

## Explain the use of the annotation @AutoWire
Segundo o Tutorials Point:
The @Autowired annotation provides more fine-grained control over where and how autowiring should be accomplished. The @Autowired annotation can be used to autowire bean on the setter method just like @Required annotation, constructor, a property or methods with arbitrary names and/or multiple arguments.