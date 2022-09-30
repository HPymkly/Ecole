package form;

import java.util.Date;

public class Etudiant {
    double id;
    String nom;
    String prenom;
    Date naissance;
    Genre genre;
    Ecole ecole;
    Note[] note;

    public Note[] getNote() {
        return note;
    }

    public void setNote(Note[] note) {
        this.note = note;
    }

    public Etudiant(double id, String nom, String prenom, Date naissance, Genre genre, Ecole ecole) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
        this.genre = genre;
        this.ecole = ecole;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

}
