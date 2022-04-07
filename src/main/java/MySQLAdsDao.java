import com.mysql.cj.jdbc.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;

//Create a class named MySQLAdsDao that implements the Ads interface
public class MySQLAdsDao implements Ads {
    //have a private instance property named connection of type Connection that is initialized in the constructor

    private Connection connection ;

//Define your constructor so that it accepts an instance of your Config class so that it can obtain the database credentials.

    //When you create an instance of MySQLAdsDao, create an instance of your Config class to pass to the MySQLAdsDao constructor
    public MySQLAdsDao(Config config) throws SQLException {
        DriverManager.registerDriver(new Driver());
        this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );
    }

    //Implement the methods in the Ads interface
    @Override
    //retrieve ads from the database
    public List<Ad> all() {
        List<Ad> adList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");
            while (rs.next()) {
                Ad newAd = new Ad(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getString("title"),
                        rs.getString("description")
                );
                adList.add(newAd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adList;
    }

    @Override
    //insert new ads into the database
    public Long insert(Ad ad) {
        try {
            Statement stmt = connection.createStatement();
            String query = String.format("INSERT INTO ads (user_id,title,description) VALUES (%d,'%s','%s')", ad.getUserId(), ad.getTitle(), ad.getDescription());
//            stmt.executeUpdate("INSERT INTO ads (user_id, title,description) VALUES ('ad.getUserId(), ad.getTitle(), ad.getDescription()')", Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate(query, stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
