package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.*;
import myExceptionPackage.NameError;

public class LittleGuest extends Essence implements Eat, Chokeable, Seriousable, Squeakable, Compete, ListenStory {

    public LittleGuest(String name) throws NameError {
        super((name.isEmpty()) ? "Маленький гость" : name);
        if(!name.isEmpty()) {
            System.out.println("Приходят новые гости!");
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
        System.out.println(getName() + " покончил с угощениями");
    }
}

