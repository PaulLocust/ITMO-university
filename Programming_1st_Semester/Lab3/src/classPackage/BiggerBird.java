package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.Complainable;
import interfacePackage.Eat;
import interfacePackage.Seriousable;

public class BiggerBird extends Essence implements Eat, Seriousable, Complainable {
    public BiggerBird(String name) {
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
        return "BiggerBirds{} " + getName();
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
    public void complain() {
        System.out.println(getName() + " начал жаловаться, что не успел даже толком распробовать.");
    }

    @Override
    public void beSerious() {
        System.out.println("Выражение лица у " + getName() + " очень серьёзно.");
    }

}
