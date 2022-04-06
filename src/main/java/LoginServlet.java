import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
            response.sendRedirect("/profile");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isAdmin = username.equals("admin") && password.equals("password");

        if (isAdmin) {
            request.getSession().setAttribute("user", username.substring(0,1).toUpperCase(Locale.ROOT)+username.substring(1));
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
