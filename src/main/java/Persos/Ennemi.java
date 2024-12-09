package Persos;

public abstract class Ennemi extends Personnages {
    protected boolean agressif;

    public Ennemi(String nom, int pointsDeVie, int attaque, int defense) {
        super(nom, pointsDeVie, attaque, defense);
        this.agressif = true;
    }

    // Getters
    public boolean isAgressif() {
        return agressif;
    }

    // Setters
    public void setAgressif(boolean agressif) {
        this.agressif = agressif;
    }

    // Méthodes spécifiques aux ennemis
    @Override
    public void prendreDegats(int degats) {
        super.prendreDegats(degats);
        // L'ennemi devient plus agressif quand il est blessé
        if (this.pointsDeVie < this.pointsDeVie / 2) {
            this.agressif = true;
            System.out.println(this.nom + " devient plus agressif !");
        }
    }

    // Méthode pour fuir le combat
    public void fuir() {
        if (this.pointsDeVie < this.pointsDeVie / 4) {  // Fuit si moins de 25% PV
            System.out.println(this.nom + " tente de fuir le combat !");
            double chanceDeFuite = 0.4;  // 40% de chance de fuir
            if (Math.random() < chanceDeFuite) {
                System.out.println(this.nom + " a réussi à s'enfuir !");
            } else {
                System.out.println(this.nom + " n'a pas réussi à s'enfuir !");
            }
        }
    }

    // Méthode pour intimider l'adversaire
    public void intimider(Personnages cible) {
        if (this.agressif) {
            System.out.println(this.nom + " tente d'intimider " + cible.getNom() + " !");
        }
    }

    @Override
    public void attaquer(Personnages cible) {
        // Vérifier si le joueur est charmé par Succube avant l'attaque
        if (cible instanceof Succube && ((Succube) cible).estCharme(this)) {
            System.out.println(this.nom + " ne peut pas attaquer " + cible.getNom() + " car il est charmé.");
        } else {
            // Si le joueur n'est pas charmé, attaquer comme d'habitude
            // Si l'ennemi est agressif, il a une chance de faire un coup critique
            if (this.agressif && Math.random() < 0.2) {  // 20% de chance de coup critique
                int attaqueInitiale = this.attaque;
                System.out.println(this.nom + " prépare une attaque critique !");
                this.attaque *= 1.5;  // Augmente les dégâts de 50%
                super.attaquer(cible);
                this.attaque = attaqueInitiale;  // Restaure l'attaque normale
            } else {
                super.attaquer(cible);
            }

            // Possibilité de fuir après l'attaque
            this.fuir();
        }
    }
}
