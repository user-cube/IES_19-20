package EmbeddedJetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmbeddedJetty {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8680);

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(MainPage.class, "/");
        servletHandler.addServletWithMapping(UsersHandler.class, "/users");

        server.start();
        server.join();

    }

    public static class UsersHandler extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setContentType("text/html");

            PrintWriter print = null;
            print = response.getWriter();

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

    public static class MainPage extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response){
            response.setContentType("text/html");

            PrintWriter print = null;
            try {
                print = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            print.println("<h3>Servlet Handler</h3>");
            print.println("<a href='http://localhost:8680/users'>Users</a>");

        }
    }
}
