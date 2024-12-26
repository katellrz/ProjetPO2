package Map;

import static outils.Omnicient.getCarte;
import static outils.Omnicient.getSize;

import java.awt.Color;
import java.util.List;

import Gestion.Interface;
import Librairies.StdDraw;
import entites.Tour;

public class DetectionSouris {


    public static Case DetectionSourisCase(double mouseX, double mouseY) {
        //System.out.println("Détection de la souris aux coordonnées : (" + mouseX + ", " + mouseY + ")");
        for (List<Case> ligne : getCarte()) {
            for (Case c : ligne) {
                if (c.contains(mouseX, mouseY, getSize())&&StdDraw.isMousePressed()) {
                    System.out.println("Click sur la case : " + c.toString()); 
                    while ((StdDraw.isMousePressed())) {
                        c.setSel(true);
                        Interface.AfficheDynamique("10-10");
                        StdDraw.pause(10);
                    }
                    c.setSel(false);
                    return c;
                }
            }
        }
        //System.out.println("Aucune case détectée sous la souris.");
        return null;
    }

    public static boolean DetectionSourisCaseBool(Case c) {
        if(c.contains(getSize(), getSize(), getSize())&&StdDraw.isMousePressed()) {
            return true;
        }else{
            return false;
        }
    }


}