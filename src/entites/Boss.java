package entites;

import java.util.List;

import outils.Omnicient;

public class Boss extends Enemi {
    
    public Boss() {
        this.PV = 150;
        this.ATK = 100;
        this.ATKSpeed = 10.;
        this.Range = 2;
        this.element = Element.FIRE;
        this.Speed = 0.5;
        this.Reward = 100;
        this.PVmax = PV;
    }

    @Override
    public int getMaxPV() {
        return PVmax;
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

