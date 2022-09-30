package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import form.Genre;

public class GenreDao {
    private static Genre[] toGenre(ArrayList<String[]> liste) {
        Genre[] ans = new Genre[liste.size()];
        String[] row = null;
        for (int i = 0; i < ans.length; i++) {
            row = liste.get(i);
            ans[i] = new Genre(Double.parseDouble(row[0]), row[1]);
        }
        return ans;
    }

    public static Genre[] fetchAll() throws ClassNotFoundException, SQLException {
        Connection connection = GlobalDao.getConnection();
        Genre[] matiere = GenreDao.fetchAll(connection);
        connection.close();
        return matiere;
    }

    public static Genre[] fetchAll(Connection connection) throws ClassNotFoundException, SQLException {
        String sql = "select id,nom from matiere";
        ArrayList<String[]> liste = GlobalDao.fetch(sql, 2);
        return GenreDao.toGenre(liste);
    }
}
