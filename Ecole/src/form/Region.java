package form;

public class Region {
    double id;
    String nom;

    public Region(double id, String nom) {
        this.setId(id);
        this.setNom(nom);
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
