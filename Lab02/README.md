# Apache Tomcat

Quando usamos anotações nas servlets podemos ignorar a modificação do web.xml
```xml
<?xml version="1.0"?>
<web-app     xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
            http://xmlns.jcp.org/xml/ns/javaee/web-app_3_0.xsd"
            version="3.0">
             
    <welcome-file-list>
        <welcome-file>/MyFirstServlet</welcome-file>
    </welcome-file-list>
     
    <servlet>
        <servlet-name>MyFirstServlet</servlet-name>
        <servlet-class>com.howtodoinjava.servlets.MyFirstServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyFirstServlet</servlet-name>
        <url-pattern>/MyFirstServlet</url-pattern>
    </servlet-mapping>
     
</web-app>
```
