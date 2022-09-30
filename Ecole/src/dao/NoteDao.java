package dao;

import java.sql.Connection;

import form.Etudiant;
import form.Note;

public class NoteDao {
    public void insert(Note note, Etudiant etudiant, Connection connection) {
        String sql = "insert into note(idetudiant,idMatiere,val) values(%s,%s,%s)";
        Object[] args = {

        };
    }
}
