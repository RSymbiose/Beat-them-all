package Niveaux;

import java.util.ArrayList;

public class Jungle extends Carte{
    private ArrayList<Object> Map;

    public Jungle(){
        super();
        int longueur = 9;
        this.Map=new ArrayList<Object>(longueur);
    }

    public void Difficulte(int diff){
        super.setDifficulty(diff);
        ArrayList<Integer> spawnPlaces = new ArrayList<Integer>(diff);
        for(int i=0; i<diff;i++){
            int indice = (int)(Math.random()*diff+1);
            if (!spawnPlaces.contains(indice)){
                spawnPlaces.add(indice);}
        }
        // spawnability : determined by smthing
    }
}