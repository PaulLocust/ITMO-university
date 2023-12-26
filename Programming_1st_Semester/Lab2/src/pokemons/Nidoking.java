package pokemons;
import moves.PhysicalMove.SmartStrike;
import moves.SpecialMove.Blizzard;
import moves.SpecialMove.IceBeam;
import moves.StatusMove.Flatter;
import ru.ifmo.se.pokemon.*;

public class Nidoking extends Pokemon{
    public Nidoking(String name, int level) {
        super(name, level);
        setStats(81, 102, 77, 85, 75, 85);
        setType(Type.POISON, Type.GROUND);
        setMove(new SmartStrike(), new Blizzard(), new Flatter(), new IceBeam());
    }
}
