package moves.StatusMove;
import ru.ifmo.se.pokemon.*;

public class Smokescreen extends StatusMove {
    public Smokescreen() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.ACCURACY, -1);
    }

    @Override
    protected String describe() {
        return "использует Smokescreen!";
    }
}
