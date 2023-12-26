package pokemons;
import moves.PhysicalMove.QuickAttack;
import moves.SpecialMove.ShadowBall;
import moves.StatusMove.DoubleTeam;
import moves.StatusMove.Screech;
import ru.ifmo.se.pokemon.*;

public class Umbreon extends Pokemon{
    public Umbreon(String name, int level) {
        super(name, level);
        setStats(95, 65, 110, 60, 130, 65);
        setType(Type.DARK);
        setMove(new ShadowBall(), new DoubleTeam(), new QuickAttack(), new Screech());
    }
}
