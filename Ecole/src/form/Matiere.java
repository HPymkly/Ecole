package form;

public class Matiere {
    double id;
    String nom;

    

    public Matiere(double id, String nom) {
        this.id = id;
        this.nom = nom;
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

}
