package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.SetTable;

public class Ceremony extends Essence implements SetTable {
    public Ceremony(String name) {
        super(name);
        System.out.println("Началась церемония: " + name + "!");
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
        return "Ceremony{}";
    }
    @Override
    public void setTheTable(Dishes dishes) {
        System.out.print("На стол были поданы ");
        switch (dishes) {
            case CANDIES -> System.out.println("Конфеты!");
            case CAKES -> System.out.println("Пирожные!");
            case BISCUITS -> System.out.println("Бисквиты!");
            case CHOCOLATE -> System.out.println("Шоколадные фонтаны!");
            case ECLAIRS -> System.out.println("Эклеры!");
            case OTHER -> System.out.println("остальные блюда!");
        }
    }

}
