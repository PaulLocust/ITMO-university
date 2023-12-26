package pokemons;
import moves.PhysicalMove.SmartStrike;
import moves.SpecialMove.Blizzard;
import ru.ifmo.se.pokemon.*;

public class Nidoran_M extends Pokemon{
    public Nidoran_M(String name, int level) {
        super(name, level);
        setStats(46, 57, 40, 40, 40, 50);
        setType(Type.POISON);
        setMove(new SmartStrike(), new Blizzard());
    }
}
