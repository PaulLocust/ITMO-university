package classPackage;

import abstractClassPackage.Essence;
import enumPackage.Dishes;
import interfacePackage.SetTable;
import myExceptionPackage.NameError;
import myExceptionPackage.OverloadedTableException;

import java.util.Arrays;

public class Ceremony extends Essence implements SetTable {


    private static int tableCapacity = 5; // Сколько блюд поместится на стол
    public Ceremony(String name) throws NameError{
        super((name.isEmpty()) ? "Неизвестная церемония" : name);
        if(name.isEmpty()) {
            throw new NameError("Объект не может быть создан без имени");
        }

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
    public void setTheTable(Dishes dishes) throws OverloadedTableException {
        System.out.print("На стол были поданы ");
        tableCapacity = tableCapacity - 1;
        switch (dishes) {
            case CANDIES -> System.out.println("Конфеты!");
            case CAKES -> System.out.println("Пирожные!");
            case BISCUITS -> System.out.println("Бисквиты!");
            case CHOCOLATE -> System.out.println("Шоколадные фонтаны!");
            case ECLAIRS -> System.out.println("Эклеры!");
        }
        if(tableCapacity < 0) {
            throw new OverloadedTableException("Стол перегружен! Нужно убрать одно блюдо!");
        }
    }

    public static class Menu extends Essence {
        public Menu() {
            super("Меню");
        }
        public void showMenu(){
            System.out.println(getName() + " на сегодня, повара - англичане, без паники: " + Arrays.toString(Dishes.values()));
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
            return "Table{}";
        }

    }

}
