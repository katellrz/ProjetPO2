package entites;

public class TricheEnemi extends Enemi {
    
    public TricheEnemi() {
        this.PV = 100;
        this.ATK = 100;
        this.ATKSpeed = 100;
        this.Range = 100;
        this.element = Element.NONE;
        this.Speed = 100;
        this.Reward = 100;
    }

    
    @Override
    public void attaquer() {
        // Les Minions ne peuvent pas attaquer
    }
    
}
