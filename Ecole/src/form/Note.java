package form;

public class Note {
    Matiere matiere;
    double value;

    public Note(Matiere matiere, double value) {
        this.matiere = matiere;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
