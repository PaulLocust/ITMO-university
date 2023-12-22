package classPackage;

import abstractClassPackage.Essence;
import interfacePackage.GiveSomething;
import interfacePackage.Seriousable;

public class Dodo extends Essence implements GiveSomething, Seriousable {
    public Dodo() {
        super("Дронт");
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
        return "Dodo{}";
    }
    @Override
    public void giveSomething(String givenItem, String person) {
        System.out.println(getName() + " протягивает " + givenItem + " " + person + ".");
    }
    @Override
    public void beSerious() {
        System.out.println("Лицо " + getName() + " очень серьёзно!");
    }

}

