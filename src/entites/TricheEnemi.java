package entites;

public class TricheEnemi extends Enemi {
    
    public TricheEnemi() {
        super(10, 3, 0, 0, Element.NONE, 1, 1);
    }

    
    @Override
    public void attaquer() {
        // Les Minions ne peuvent pas attaquer
    }
    
}
