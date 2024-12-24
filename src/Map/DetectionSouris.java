package Map;

import static outils.Omnicient.getCarte;
import static outils.Omnicient.getSize;

import java.awt.Color;
import java.util.List;

import Librairies.StdDraw;

public class DetectionSouris {

    private static Case Caseprecedente;


    /* public static Case DetectionSourisCase(double mouseX, double mouseY) {
        for (List<Case> ligne : getCarte()) {
            for (Case c : ligne) {
                if (c.contains(mouseX, mouseY, getSize())) {
                    if (Caseprecedente != null) {
                        Caseprecedente.setSel(false);
                    }
                }

                c.setSel(true);
                Caseprecedente = c;
                return c;
            }  
        }
        return null; 
    }*/

    public static Case DetectionSourisCase(double mouseX, double mouseY) {
        System.out.println("Détection de la souris aux coordonnées : (" + mouseX + ", " + mouseY + ")");
        for (List<Case> ligne : getCarte()) {
            for (Case c : ligne) {
                if (c.contains(mouseX, mouseY, getSize())) {
                    System.out.println("La souris est sur la case : " + c.toString());
                    return c;
                }
            }
        }
        System.out.println("Aucune case détectée sous la souris.");
        return null;
    }

}


