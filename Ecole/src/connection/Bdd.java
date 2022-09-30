package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    public static Connection getConnection(String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:dbcours", user, password);
        return conect;
    }
}
