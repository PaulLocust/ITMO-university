package pokemons;
import moves.PhysicalMove.QuickAttack;
import moves.SpecialMove.ShadowBall;
import moves.StatusMove.DoubleTeam;
import ru.ifmo.se.pokemon.*;

public class Eevee extends Pokemon{
    public Eevee(String name, int level) {
        super(name, level);
        setStats(55, 55, 50, 45, 65, 55);
        setType(Type.NORMAL);
        setMove(new ShadowBall(), new DoubleTeam(), new QuickAttack());
    }
}
