package classPackage;

import abstractClassPackage.Essence;
import interfacePackage.TellStory;

public class Mouse extends Essence implements TellStory {
    public Mouse() {
        super("Мышь");
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
        return "Mouse{}";
    }

    @Override
    public void tellStory(){
        System.out.println(getName() + " начала рассказывать историю");
    }
}
