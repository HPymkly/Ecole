package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import form.Matiere;

public class MatiereDao {
    private static Matiere[] toMatiere(ArrayList<String[]> liste) {
        Matiere[] ans = new Matiere[liste.size()];
        String[] row = null;
        for (int i = 0; i < ans.length; i++) {
            row = liste.get(i);
            ans[i] = new Matiere(Double.parseDouble(row[0]), row[1]);
        }
        return ans;
    }

    public static Matiere[] fetchAll() throws ClassNotFoundException, SQLException {
        Connection connection = GlobalDao.getConnection();
        Matiere[] matiere = MatiereDao.fetchAll(connection);
        connection.close();
        return matiere;
    }

    public static Matiere[] fetchAll(Connection connection) throws ClassNotFoundException, SQLException {
        String sql = "select id,nom from matiere";
        ArrayList<String[]> liste = GlobalDao.fetch(sql, 2);
        return MatiereDao.toMatiere(liste);
    }
}
