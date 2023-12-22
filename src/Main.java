import classPackage.*;
import enumPackage.Dishes;
import interfacePackage.TellStory;
import myExceptionPackage.OverloadedTableException;


public class Main {
    public static void main(String[] args){
        Competition competitionBox = new Competition("Боксёрский бой");
        Competition competitionDarts = new Competition("Дартс");
        Competition competitionEat = new Competition("Поедание блинов");


        LittleGuest dwarf = new LittleGuest("Дворф");
        LittleGuest fairy = new LittleGuest("Фэйри");
        LittleGuest bunny = new LittleGuest("Кролик");
        BiggerBird ostrich = new BiggerBird("Страус");
        BiggerBird eagle = new BiggerBird("Орёл");



        dwarf.compete(competitionBox.getName());
        fairy.compete(competitionDarts.getName());
        bunny.compete(competitionEat.getName());
        ostrich.compete(competitionEat.getName());
        eagle.compete(competitionBox.getName());
        eagle.compete(competitionBox.getName());

        Alice alice = new Alice();
        alice.confuse();
        alice.checkPockets();

        String candiedFruits = "Цукаты";
        alice.giveSomething( candiedFruits, dwarf.getName());
        alice.giveSomething( candiedFruits, fairy.getName());
        alice.giveSomething( candiedFruits, bunny.getName());
        alice.giveSomething( candiedFruits, ostrich.getName());
        alice.giveSomething( candiedFruits, eagle.getName());


        new Ceremony.Menu().showMenu();
        Ceremony ceremony = new Ceremony("Вручение напёрстка");

        Dodo dodo = new Dodo();
        Mouse mouse = new Mouse();


        try {
            ceremony.setTheTable(Dishes.BISCUITS);
            ceremony.setTheTable(Dishes.CAKES);
            ceremony.setTheTable(Dishes.CANDIES);
            ceremony.setTheTable(Dishes.CHOCOLATE);
            ceremony.setTheTable(Dishes.ECLAIRS);

        } catch (OverloadedTableException e) {
            System.out.println(e.getMessage());
        }


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

        dodo.giveSomething("Напёрсток", "Алиса");

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

        bunny.stopEating();
        dwarf.stopEating();
        eagle.stopEating();
        ostrich.stopEating();
        fairy.stopEating();


        dwarf.listenStory(mouse.getName());
        fairy.listenStory(mouse.getName());
        bunny.listenStory(mouse.getName());
        eagle.listenStory(mouse.getName());
        ostrich.listenStory(mouse.getName());

        // Anonymous class for story
        TellStory story = new TellStory() {
            @Override
            public void tellStory(){
                if (Math.random()*100 > 50) {
                    System.out.println(mouse.getName() + " рассказывает интересную историю!");
                }
                else System.out.println("К сожалению, " + mouse.getName() + " не в духе и будет жаловаться на жизнь");
            }
        };

        // Покажет, что будет рассказывать мышь
        story.tellStory();
    }

}