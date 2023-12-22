package classPackage;

import abstractClassPackage.Essence;
import myExceptionPackage.NameError;

public class Competition extends Essence {

    public Competition(String name) throws NameError {
        super((name.isEmpty()) ? "Лохотрон" : name);
        if(name.isEmpty()) {
            throw new NameError("Объект не может быть создан без имени");
        }
        System.out.println("Проходит соревнование: " + name + "!");
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
        return "Competition{}";
    }

}
