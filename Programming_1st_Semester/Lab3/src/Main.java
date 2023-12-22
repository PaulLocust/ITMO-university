import classPackage.*;
import enumPackage.Dishes;

public class Main {
    public static void main(String[] args) {

        Ceremony ceremony = new Ceremony("Вручение напёрстка");
        Alice alice = new Alice();
        Dodo dodo = new Dodo();

        LittleGuest dwarf = new LittleGuest("Дворф");
        LittleGuest fairy = new LittleGuest("Фэйри");
        LittleGuest bunny = new LittleGuest("Кролик");

        BiggerBird ostrich = new BiggerBird("Страус");
        BiggerBird eagle = new BiggerBird("Орёл");

        ceremony.setTheTable(Dishes.BISCUITS);
        ceremony.setTheTable(Dishes.CAKES);

        dodo.beSerious();
        dwarf.beSerious();
        fairy.beSerious();
        bunny.beSerious();
        ostrich.beSerious();
        eagle.beSerious();

        alice.startLaugh(true);
        alice.startLaugh(false);
        alice.beSerious();
        alice.interactWithGuests(new String[]{dwarf.getName(), fairy.getName(), bunny.getName(), eagle.getName(), ostrich.getName()});

        dodo.giveSomething("Напёрсток", alice);

        dwarf.eat(Dishes.BISCUITS);
        fairy.eat(Dishes.BISCUITS);
        bunny.eat(Dishes.BISCUITS);
        ostrich.eat(Dishes.CAKES);
        eagle.eat(Dishes.CAKES);

        dwarf.squeak();
        fairy.squeak();
        bunny.squeak();

        ostrich.complain();
        eagle.complain();

        bunny.choke();
        fairy.choke();
    }

}

