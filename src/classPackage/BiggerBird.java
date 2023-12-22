package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.*;
import myExceptionPackage.NameError;

public class BiggerBird extends Essence implements Eat, Seriousable, Complainable, Compete, ListenStory {
    public BiggerBird(String name) throws NameError {
        super((name.isEmpty()) ? "Маленький гость" : name);
        if(!name.isEmpty()) {
            System.out.println("На церемонию кто-то пришёл");
        }
        else {
            throw new NameError("Объект не может быть создан без имени");
        }
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

    @Override
    public void compete(String name){
        System.out.println(getName() + " участвует в соревновании под названием: " + name);
    }

    @Override
    public void listenStory(String byWho) {
        System.out.println(getName() + " подошёл к " + byWho + " чтобы послушать историю");
    }

    @Override
    public void stopEating() {
        System.out.println(getName() + " вдоволь наелся угощений");
    }
}
