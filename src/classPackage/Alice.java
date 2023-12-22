package classPackage;

import abstractClassPackage.Essence;
import interfacePackage.*;

public class Alice extends Essence implements Laughable, Seriousable, InteractWithGuests, Findable, Confusionable, GiveSomething {
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

    @Override
    public void confuse() {
        System.out.println(getName() + " не знала, что делать и сильно растерялась.");
    }

    public interface CheckPockets{
        public void checkPockets();
    }

    // Вложенный локальный класс
    @Override
    public void checkPockets() {
        class Box {
            final String snackName = "Цукаты";
            public String getSnack() {
                return snackName;
            }
        }
        Box box = new Box();
        System.out.println(getName() + " cунула руку в кармашек и нашла там коробочку со сладостями. Это " + box.getSnack() + "!");
    }

    @Override
    public void giveSomething(String object, String person) {
        System.out.println(getName() + " раздаёт " + object + " " + person);
    }

}

