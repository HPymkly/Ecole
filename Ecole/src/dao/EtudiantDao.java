package dao;

import java.sql.Connection;
import java.sql.SQLException;
import form.Etudiant;

public class EtudiantDao {

    public static void insert(Etudiant etudiant) throws ClassNotFoundException, SQLException {
        Connection connection = GlobalDao.getConnection();
        EtudiantDao.insert(etudiant, connection);
        connection.close();
    }

    public static void insert(Etudiant etudiant, Connection connection) throws SQLException, ClassNotFoundException {
        double id = GlobalDao.generateID("etudiant_id_seq");
        String sql = "insert into Etudiant(id,nom,prenom,idGenre,idEcole,datenaissance) values(%s,'%s','%s',%s,'%s')";
        Object[] args = {
                (int) id,
                etudiant.getNom(),
                etudiant.getPrenom(),
                etudiant.getGenre().getId(),
                etudiant.getEcole().getId(),
                etudiant.getNaissance()
        };
        sql = String.format(sql, args);
        GlobalDao.execute(sql, connection);
    }
}
