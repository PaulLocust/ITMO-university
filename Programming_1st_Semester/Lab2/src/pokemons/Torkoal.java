package pokemons;
import moves.PhysicalMove.BodySlam;
import moves.StatusMove.Confide;
import moves.StatusMove.Smokescreen;
import moves.StatusMove.Swagger;
import ru.ifmo.se.pokemon.*;

public class Torkoal extends Pokemon{
    public Torkoal(String name, int level) {
        super(name, level);
        setStats(70, 85, 140, 85, 70, 20);
        setType(Type.FIRE);
        setMove(new Swagger(), new BodySlam(), new Smokescreen(), new Confide());
    }
}
