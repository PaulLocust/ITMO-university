package moves.PhysicalMove;
import ru.ifmo.se.pokemon.*;

public class SmartStrike extends PhysicalMove {
    public SmartStrike() {
        super(Type.STEEL, 70, 1000);
    }

    @Override
    protected String describe() {
        return "использует Smart Strike, имея 100% точность!";
    }

}
