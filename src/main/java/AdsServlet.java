import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="AdsServlet", urlPatterns = "/ads")
public class AdsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Ads ads = DaoFactory.getAdsDao();
        List<Ad> adsListing = ads.all();
        request.setAttribute("adsListing", adsListing);
        request.getRequestDispatcher("ads/index.jsp").forward(request, response);
    }
}
