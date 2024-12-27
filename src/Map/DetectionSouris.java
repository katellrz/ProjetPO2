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
        if(c.contains(StdDraw.mouseX(), StdDraw.mouseY(), getSize())&&StdDraw.isMousePressed()) {
            return true;
        }else{
            return false;
        }
    }

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

    public static void DetectionSourisTour() {
        
    }


}