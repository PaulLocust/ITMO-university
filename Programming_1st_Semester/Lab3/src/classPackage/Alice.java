package classPackage;

import abstractClassPackage.Essence;
import interfacePackage.Laughable;
import interfacePackage.InteractWithGuests;
import interfacePackage.Seriousable;

public class Alice extends Essence implements Laughable, Seriousable, InteractWithGuests {
    public Alice() {
        super("Алиса");
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Alice{}";
    }
    @Override
    public void interactWithGuests(String[] guests) {
        for (String guest : guests) {
            System.out.println(getName() + " поклонилась гостю " + guest);
        }
    }


    @Override
    public void startLaugh(boolean state) {
        if (state){
            System.out.println(getName() + " вот-вот засмеётся!");
        }else System.out.println(getName() + " сдержала смех.");
    }

    @Override
    public void beSerious() {
        System.out.println("Лицо " + getName() + " приняло серьёзный вид.");
    }
}

