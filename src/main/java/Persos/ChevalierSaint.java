package Persos;

public class ChevalierSaint extends Hero {
    // Peut purifier un ennemi, celui-ci inflige 50% de dégâts en moins
    private Personnages personnagePurifie;
    private boolean purificationActive;

    public ChevalierSaint(String nom) {
        super(nom, 200, 30, 10);
        this.purificationActive = false;
        this.personnagePurifie = null;
    }

    @Override
    public void utiliserCapaciteSpeciale(Personnages cible) {
        if (!isCapaciteSpecialeUtilisee()) {
            this.personnagePurifie = cible;
            this.purificationActive = true;
            setCapaciteSpecialeUtilisee(true);
            System.out.println(getNom() + " purifie " + cible.getNom() + " ! Ses dégâts sont réduits de moitié !");

            // Réduit l'attaque de la cible de moitié
            if (cible instanceof Ennemi) {
                Ennemi ennemi = (Ennemi) cible;
                int nouvelleAttaque = ennemi.getAttaque() / 2;
                ennemi.setAttaque(nouvelleAttaque);
            } else {
                System.out.println("Capacité spéciale ne peut être utilisée que sur un ennemi !");
            }
        } else {
            System.out.println("La capacité spéciale a déjà été utilisée !");
        }
    }

    public boolean estPurifie(Personnages personnage) {
        return purificationActive && personnagePurifie == personnage;
    }

    public void finPurification() {
        if (personnagePurifie != null && purificationActive) {
            // Restaure l'attaque originale
            if (personnagePurifie instanceof Ennemi) {
                Ennemi ennemi = (Ennemi) personnagePurifie;
                ennemi.setAttaque(ennemi.getAttaque() * 2);
            }
            this.purificationActive = false;
            this.personnagePurifie = null;
        }
    }
}
