package com.IES.lab02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="IESServ", urlPatterns = {"/users"})
public class MainServ extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        
        PrintWriter print = response.getWriter();
        print.println("<h3>My First Servlet</h3>");

        if (request.getParameterValues("name") != null) {
            print.println("<p>Nome:" + request.getParameter("name") + "</p>");
        }else {
            print.println("<p>Esta servlet usa o método get para interpertar os comandos.</p>" +
                    "<br>" +
                    "<p>Esta página recebe como query o nome do utilizador, para isso:</p>" +
                    "<br>"+
                    "<p>URL: http://localhost:8080/users?name={NAME}</p>");
        }

    }
}