package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.Bdd;
import tool.MyTool;

public class GlobalDao {

    public static double generateID(String id) throws ClassNotFoundException, SQLException {
        Connection connection = GlobalDao.getConnection();
        double ans = GlobalDao.generateID(id, connection);
        connection.close();
        return ans;
    }

    public static double generateID(String id, Connection connection) throws ClassNotFoundException, SQLException {
        String sql = "select nextval('" + id + "')";
        ArrayList<String[]> res = GlobalDao.fetch(sql, 1);
        return Double.parseDouble(res.get(0)[0]);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return Bdd.getConnection(MyTool.user, MyTool.password);
    }

    public static ArrayList<String[]> fetch(String sql, int len) throws SQLException, ClassNotFoundException {
        Connection connection = GlobalDao.getConnection();
        ArrayList<String[]> ans = GlobalDao.fetch(sql, len, connection);
        connection.close();
        return ans;
    }

    public static ArrayList<String[]> fetch(String sql, int len, Connection connection) throws SQLException {
        ArrayList<String[]> answer = new ArrayList<>();
        Statement stat = connection.createStatement();
        ResultSet res = stat.executeQuery(sql);
        String[] liste = null;
        int i = 0;
        while (res.next()) {
            liste = new String[len];
            for (i = 0; i < len; i++) {
                liste[i] = res.getString(i + 1);
            }
            answer.add(liste);
        }
        res.close();
        stat.close();
        connection.close();
        return answer;
    }

    public static void execute(String sql) throws ClassNotFoundException, SQLException {
        Connection connection = Bdd.getConnection(MyTool.user, MyTool.password);
        GlobalDao.execute(sql, connection);
        connection.close();
    }

    public static void execute(String sql, Connection connection) throws SQLException {
        PreparedStatement pre = connection.prepareStatement(sql);
        pre.executeUpdate();
        pre.close();
    }

}
