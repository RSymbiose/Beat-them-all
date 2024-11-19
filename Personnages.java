public abstract class Personnages {
    protected String nom;
    protected int pointsDeVie;
    protected int attaque;
    protected int defense;

    public Personnages(String nom, int pointsDeVie, int attaque, int defense) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.attaque = attaque;
        this.defense = defense;
    }

    public void attaquer(Personnages cible) {
        int degats = Math.max(0, this.attaque - cible.defense);
        System.out.println(this.nom + " attaque " + cible.nom + " et inflige " + degats + " points de dégâts.");
        cible.prendreDegats(degats);
    }

    public void prendreDegats(int degats) {
        this.pointsDeVie -= degats;
        System.out.println(this.nom + " perd " + degats + " points de vie. PV restants : " + this.pointsDeVie);
        if (!estVivant()) {
            System.out.println(this.nom + " est mort.");
        }
    }

    public boolean estVivant() {
        return this.pointsDeVie > 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
