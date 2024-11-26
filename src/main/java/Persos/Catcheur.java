package Persos;

public class Catcheur extends Ennemi {
    private String priseSignature;  // La prise de catch signature
    private boolean enColere;  // État de rage du catcheur

    public Catcheur(String nom) {
        // Valeurs par défaut pour un catcheur
        super(nom, 100, 15, 8);
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
        if (this.getPointsDeVie() < 50 && !enColere) {
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
}