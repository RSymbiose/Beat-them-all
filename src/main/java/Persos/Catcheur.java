package Persos;

public class Catcheur extends Ennemi {
    //possède une prise spéciale et fait plus de dégâts quand il a moins de 50% hp
    private String priseSignature;  // La prise de catch signature
    private boolean enColere;  // État de rage du catcheur

    public Catcheur(String nom) {
        // Valeurs par défaut pour un catcheur
        super(nom, 100, 15, 0);
        this.priseSignature = "Suplex suuuuuuu";
        this.enColere = false;
    }

    public Catcheur(String nom, int pointsDeVie, int attaque, int defense, String priseSignature) {
        super(nom, pointsDeVie, attaque, defense);
        this.priseSignature = priseSignature;
        this.enColere = false;
    }

    public String getPriseSignature() {
        return priseSignature;
    }

    public void setPriseSignature(String priseSignature) {
        this.priseSignature = priseSignature;
    }

    public boolean isEnColere() {
        return enColere;
    }

    public void setEnColere(boolean enColere) {
        this.enColere = enColere;
    }

    @Override
    public void prendreDegats(int degats) {
        super.prendreDegats(degats);
        // Le catcheur devient enragé quand il perd trop de vie
        if (this.getPointsDeVie() < 50 && !enColere && this.estVivant()) {
            enColere = true;
            this.setAttaque(this.getAttaque() + 5);
            System.out.println(this.getNom() + " devient enragé ! Son attaque augmente !");
        }
    }

    @Override
    public void attaquer(Personnages cible) {
        if (Math.random() < 0.3) {  // 30% de chance d'utiliser la prise signature
            System.out.println(this.getNom() + " utilise sa prise signature : " + priseSignature + " !");
            int attaqueBonus = 8;
            this.setAttaque(this.getAttaque() + attaqueBonus);
            super.attaquer(cible);
            this.setAttaque(this.getAttaque() - attaqueBonus);
        } else {
            super.attaquer(cible);
        }
    }

    public String toString() {
        return "Catcheur{" +"nom: "+this.nom+", PV: "+this.pointsDeVie+", attaque: "+this.attaque+", defense: "+this.defense + "}";
    }
}