package uno.kartenspiel;

/**
 *
 * @author M-N
 */
public class Tisch {

    Karte karteAufTisch;

    public Karte getKarteAufTisch() {
        return karteAufTisch;
    }

    public void layCard(Karte karte) {

        karteAufTisch = karte;

    }

    public void layStartCard(Karte karte) {
        karteAufTisch = karte;
    }

}
