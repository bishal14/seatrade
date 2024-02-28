import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainClass {
    public static void main(String [] args){


        String url = "jdbc:mysql://localhost:3306/seatrade";
        String user = "root";
        String password = "admin";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1");

            if (resultSet.next()) {
                System.out.println("MySQL connection is successful.");
            } else {
                System.out.println("MySQL connection failed.");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
