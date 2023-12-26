package pokemons;
import moves.PhysicalMove.SmartStrike;
import moves.SpecialMove.Blizzard;
import moves.StatusMove.Flatter;
import ru.ifmo.se.pokemon.*;

public class Nidorino extends Pokemon{
    public Nidorino(String name, int level) {
        super(name, level);
        setStats(61, 72, 57, 55, 55, 65);
        setType(Type.POISON);
        setMove(new SmartStrike(), new Blizzard(), new Flatter());
    }
}
