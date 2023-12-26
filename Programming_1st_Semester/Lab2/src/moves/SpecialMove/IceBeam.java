package moves.SpecialMove;
import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove {
    public IceBeam() {
        super(Type.ICE, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.1) {
            p.addEffect(new Effect().condition(Status.FREEZE).chance(0.1).turns(1).attack(0.0));

        }
    }

    @Override
    protected String describe() {
        return "использует Blizzard!";
    }
}
