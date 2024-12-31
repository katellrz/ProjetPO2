package Map;

import Librairies.StdDraw;
import java.util.List;
import static outils.Omnicient.getCarte;
import static outils.Omnicient.getSize;

/**
 * La classe {@code DetectionSouris} fournit des méthodes pour détecter les actions de la souris sur la carte du jeu.
 * Elle permet de savoir si la souris est sur une case particulière, si elle est dans une zone spécifique de l'interface, 
 * ou si elle a cliqué sur une case.
 */
public class DetectionSouris {


  /**
     * Détecte la case sur laquelle la souris est positionnée et si elle a été cliquée.
     * Si une case est sélectionnée, elle devient active et est renvoyée.
     *
     * @param mouseX la position horizontale de la souris (coordonnée X).
     * @param mouseY la position verticale de la souris (coordonnée Y).
     * @return la  Case sur laquelle la souris a cliqué, ou null si aucune case n'a été détectée.
     */
    public static Case DetectionSourisCase(double mouseX, double mouseY) {
        //System.out.println("Détection de la souris aux coordonnées : (" + mouseX + ", " + mouseY + ")");
        for (List<Case> ligne : getCarte()) {
            for (Case c : ligne) {
                if (c.contains(mouseX, mouseY, getSize())&&StdDraw.isMousePressed()) {
                    System.out.println("Click sur la case : " + c.toString()); 
                    while ((StdDraw.isMousePressed())) {
                        c.setSel(true);
                        //Interface.AfficheDynamique("10-10");
                        
                    }
                    c.setSel(false);
                    return c;
                }
            }
        }
        //System.out.println("Aucune case détectée sous la souris.");
        return null;
    }

      /**
     * Détecte si la souris est positionnée sur la case donnée et si elle a été cliquée.
     *
     * @param c la case à vérifier.
     * @return  true si la souris est sur la case et qu'elle a été cliquée, sinon  false.
     */

    public static boolean DetectionSourisCaseBool(Case c) {
        if(c.contains(StdDraw.mouseX(), StdDraw.mouseY(), getSize())&&StdDraw.isMousePressed()) {
            System.out.println("Souris sur la case : " + c.toString());
            return true;
        }else{
            return false;
        }
    }


    /**
     * Détecte dans quelle zone de l'interface la souris est positionnée, en fonction de ses coordonnées.
     *
     * @param mouseX la position horizontale de la souris (coordonnée X).
     * @param mouseY la position verticale de la souris (coordonnée Y).
     * @return un  String représentant la zone de l'interface sur laquelle la souris est positionnée. 
     *         Les zones possibles sont "Zone Map", "Zone Level", "Zone Player", "Zone Store" et "Zone Autre".
     */
    public static String DetectionZone(double mouseX, double mouseY) {
        if (mouseX > 0 && mouseX < 700 && mouseY > 0 && mouseY < 700) {
            return "Zone Map";
        } else if (mouseX > 721 && mouseX < 1009 && mouseY > 676 && mouseY < 700) {
            return "Zone Level";
        } else if (mouseX > 721 && mouseX < 1009 && mouseY > 616 && mouseY < 666) {
            return "Zone Player";
        } else if (mouseX > 721 && mouseX < 1009 && mouseY > 0 && mouseY < 606) {
            return "Zone Store";
        } else {
            return "Zone Autre";
        }
    }
    /**
     * Méthode vide pour la détection de la souris sur une tour
     * Actuellement, cette méthode ne fait rien.
     */

    public static void DetectionSourisTour() {
        
    }


}