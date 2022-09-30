package form;

public class Ecole {
    double id;
    String nom;
    Region region;

    public Ecole(double id, String nom, Region region) {
        this.id = id;
        this.nom = nom;
        this.region = region;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
