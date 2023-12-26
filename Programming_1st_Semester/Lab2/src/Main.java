import pokemons.*;
import ru.ifmo.se.pokemon.Battle;
public class Main {
    public static void main(String[] args) {
        Battle game = new Battle();

        Eevee eevee = new Eevee("BorisBritva", 1);
        Nidoking nidoking = new Nidoking("B.B.King", 1);
        Nidoran_M nidoran_m = new Nidoran_M("Maksim", 1);
        Nidorino nidorino = new Nidorino("Chipolino", 1);
        Torkoal torkoal = new Torkoal("Kamenshik", 1);
        Umbreon umbreon = new Umbreon("TutHamon", 1);

        // Команда 1
        game.addAlly(eevee);
        game.addAlly(nidoking);
        game.addAlly(nidoran_m);

        // Команда 2
        game.addFoe(nidorino);
        game.addFoe(torkoal);
        game.addFoe(umbreon);

        game.go();
    }

}