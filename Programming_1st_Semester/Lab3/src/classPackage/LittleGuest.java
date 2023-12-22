package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.Squeakable;
import interfacePackage.Eat;
import interfacePackage.Chokeable;
import interfacePackage.Seriousable;

public class LittleGuest extends Essence implements Eat, Chokeable, Seriousable, Squeakable {

    public LittleGuest(String name) {
        super(name);
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
        return "LittleGuest{} " + getName();
    }

    @Override
    public void beSerious() {
        System.out.println(getName() + " выглядел очень серьёзно!");
    }

    @Override
    public void eat(Dishes dishes) {
        System.out.print(getName() + " начал есть ");
        switch (dishes) {
            case CANDIES -> System.out.println("Конфеты!");
            case CAKES -> System.out.println("Пирожные!");
            case BISCUITS -> System.out.println("Бисквиты!");
            case CHOCOLATE -> System.out.println("Шоколадные фонтаны!");
            case ECLAIRS -> System.out.println("Эклеры!");
            case OTHER -> System.out.println("оставшиеся блюда!");
        }
    }

    @Override
    public void squeak() {
        System.out.println(getName() + " стал пищать");
    }
    @Override
    public void choke() {
        System.out.println(getName() + " второпях поперхнулся, и его пришлось похлопать по спинке.");

    }
}

