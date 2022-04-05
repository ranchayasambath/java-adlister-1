import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewProfileServlet", urlPatterns = "/profile")
public class ViewProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("user") == null){
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
    }
}
