package moves.StatusMove;
import ru.ifmo.se.pokemon.*;

public class Flatter extends StatusMove {
    public Flatter() {
        super(Type.DARK, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_ATTACK, 1);
        Effect.confuse(p);
    }

    @Override
    protected String describe() {
        return "использует Flatter!";
    }
}
