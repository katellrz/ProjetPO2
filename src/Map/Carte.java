package Map;

import Librairies.FileExtraction;
import Map.Case.Casetype;

import static outils.Omnicient.SavetoOmniSpawn;

import java.util.ArrayList;
import java.util.List;
import outils.Omnicient;

public class Carte {

    private List<List<Case>> CarteJeu;
    private String nom;
    private int size;
    private List<Case> Chemin;


    
    public Carte(String nom){
        this.nom = nom;
        this.CarteJeu = ConvertiCase();
        Omnicient.SaveToOmni(CarteJeu);
        this.Chemin = ConstruitChemin();
        Omnicient.SavetoOmni(Chemin);
    }

    public Carte copier() {
        return new Carte(this.nom); // Retourne une nouvelle instance de Map
    }

    public String getNom(){
        return this.nom;
    }

    /**
     * Prend une carte dans un fichier du même nom que celle ci et convertit chacun des caratère de ce fichier en une liste de liste de case 
     * @return Case[][] un tableau 2D de char 
     */
    private List<List<Case>> ConvertiCase() {
        String filePath = "resources/maps/" + this.nom + ".mtp";
        List<String> tabStrings = FileExtraction.ExtraireFichier(filePath);
        if (tabStrings == null || tabStrings.isEmpty()) {
            System.out.println("Erreur: fichier de carte introuvable ou vide.");
            return null;
        }
        int rows = tabStrings.size();
        int cols = tabStrings.get(0).length();
        this.size = 700 / Math.max(rows, cols);
        Omnicient.SaveToOmni(size);
        int startX = (700 - cols * this.size) / 2;
        int startY = (700 - rows * this.size) / 2;
        List<List<Case>> Carte = new ArrayList<>();
        try {
            for (int i = 0; i < rows; i++) {
                String ligne = tabStrings.get(i);
                List<Case> lignCases = new ArrayList<>();
                for (int j = 0; j < cols; j++) {
                    int centerX = startX + j * size + size / 2;
                    int centerY = startY + i * size + size / 2;
                    Case c = new Case(ligne.charAt(j), i, j, centerX, centerY);
                    lignCases.add(c);
                }
                Carte.add(lignCases);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion des cases: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return Carte;
    }



    /**
     * Affiche la carte en appelant un fonction qui dessine chaque cas
     * 
     */
    public void afficheCarte(){ 
        for (List<Case> l : CarteJeu) {//pour chaque ligne 
            for(Case c : l){// pour chaque colone 
                c.afficheCase();
            }
        }       
    }

    @Override
    public String toString(){
        String retour = "";
        for (List<Case> l : CarteJeu) {
            for (Case c : l){
                retour+= c.getLettre();
            }
            retour+="\t";   
        }
        return retour; 
    }
    
    /**
     * Cherche la case suivate a partitr d'un tableaux de case cette direction est chercher sur les case autour de la case actuelle sans prendre en compte le diagonale 
     * @param current case actuelle dont on veux trouver la case suivante
     * @param chemin liste des case deja visité (qui corrspondent au case du chamin déja construit )
     * @return la case suivante a devoir être ajouté au chemin
     * Cette fonction a été soumise a l'IA pour la structure et l'utilisation du tableau de direction qui ne fonctionait pas en raison d'une mauvais initialisation et utilisation par la suite 
     *
     */
    private Case TrouveCaseSuivante(Case current, List<Case> chemin) {
        int row = current.rows; // Coordonnées actuelles
        int col = current.cols;
    
        // Directions possibles : Haut, Bas, Gauche, Droite
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };
    
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
    
            // Vérifie si la case voisine est valide (pas forcement nécéssaire car les case sont entoure er de decor)
            if (newRow >= 0 && newRow < CarteJeu.size() && newCol >= 0 && newCol < CarteJeu.get(0).size()) {
                Case nextCase = CarteJeu.get(newRow).get(newCol);
                if ((!chemin.contains(nextCase)) && nextCase.getType() == Casetype.ROUTE||nextCase.getType() == Casetype.BASE ) {
                    return nextCase; // Retourne la première case valide
                }
            }
        }
        return null; // Aucun voisin route ou base trouvé
    }


    public List<Case> ConstruitChemin(){
        List<Case> chemin = new ArrayList<>();

        Case start = Spawn();//Trouve la case depart
        Omnicient.SavetoOmniSpawn(start);

        if (start == null) {    
            System.out.println("Pas de case SPAW trouvée la carte n'est pas valide");
            return chemin;
        }

        chemin.add(start);
        return ConstruitCheminRecursive(start, chemin);
    }

    /**
     * Fonction recursive qui construit le chemin petit a petit, 
     * @return la liste chemin 
     */
    private List<Case> ConstruitCheminRecursive(Case current, List<Case> chemin) {
        if(current.getType()==Casetype.BASE){
            //System.out.println("Arrive la ");
            Omnicient.SavetoOmniBase(current);
            return chemin;
        }else{
            //System.out.println("Arrive la ");
            Case nextCase = TrouveCaseSuivante(current, chemin);
            if (nextCase != null) {
                //System.out.println("Arrive la vrl");
                chemin.add(nextCase);
                return ConstruitCheminRecursive(nextCase, chemin);
            }else{
                return null;
            }
        }
    }
    
    /**
     * Trouve le spawn de la map et le return 
     * Cette fonction e été soumise a chat GPT pour la solifier( géré les cas ou )
     * @return une case de type Spawn
     */
    public Case Spawn() {
        if (CarteJeu == null) {
            System.out.println("CarteJeu est null");
            return null;
        }
    
        for (int i = 0; i < CarteJeu.size(); i++) {
            if (CarteJeu.get(i)==null) continue; // Vérifiez si la ligne est null TODO 
    
            for (int j = 0; j < CarteJeu.get(0).size(); j++) {
                if (CarteJeu.get(i).get(j) != null && CarteJeu.get(i).get(j).getType() == Casetype.SPAWN) {
                    //System.out.println("trouvé");
                    SavetoOmniSpawn(CarteJeu.get(i).get(j));
                    return CarteJeu.get(i).get(j);
                }
            }
        }
        //System.out.println("null");
        return null;
    }

}