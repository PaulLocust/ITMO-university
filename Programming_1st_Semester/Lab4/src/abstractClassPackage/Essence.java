package abstractClassPackage;
import java.util.Objects;

public abstract class Essence {
    private final String name;
    public Essence(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Essence essence = (Essence) o;
        return Objects.equals(name, essence.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Essence{" +
                "name='" + name + '\'' +
                '}';
    }

}


