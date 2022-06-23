package uno.kartenspiel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author M-N
 */
public class Spieler {

    private String name;
    private final List<Karte> meineKarten = new ArrayList<>();

    public void giveCard(Karte karte) {
        meineKarten.add(karte);
    }

    public void dropCard(Karte karte) {
        meineKarten.remove(karte);
    }

    public Karte getCard(int kartenzahl) {
        return meineKarten.get(kartenzahl - 1);

    }

    public Spieler(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean uno() {
        boolean uno = false;
        if (meineKarten.size() == 1) {
            uno = true;
        }
        return uno;
    }

    public boolean gameWin() {
        boolean gameGewonnen = false;
        if (meineKarten.isEmpty()) {
            gameGewonnen = true;
        }
        return gameGewonnen;

    }

    public String showMyCards() {
        String stringMeineKarten = "";
        int i = 1;
        for (Karte zeigeMeineKarten : meineKarten) {

            stringMeineKarten += i + " -> " + zeigeMeineKarten.toString() + "\n";
            i = i + 1;
        }
        return stringMeineKarten;
    }

    public void löscheMeineKarten() {
        meineKarten.clear();
    }

    public int zaehleMeineKarten() {
        return meineKarten.size();
    }

}
