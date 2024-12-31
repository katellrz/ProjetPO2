package entites;

import java.util.List;

import outils.Omnicient;

public class EarthBrute extends Enemi {
    
    public EarthBrute() {
        super(30, 5, 1, 5, Element.EARTH, 1, 3);
    }

    @Override
    public void attaquer() {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = PlusProche(cibles);
                if (cible != null) {
                    cible.setPV(cible.getPV() - this.ATK);
                    afficheattaque(cible);
                }
            }
        }
    }
}