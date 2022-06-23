package uno.kartenspiel;

/**
 * Kanti M-N
 */
public class Karte {

    private final String zeichen;
    private String farbe;

    public Karte(String zeichen, String farbe) {
        this.zeichen = zeichen;
        this.farbe = farbe;
    }

    public void setFarbe(String farb) {
        this.farbe = farb;

    }

    @Override
    public String toString() {
        return "[" + this.zeichen + " " + this.farbe + "]";
    }

    public String getZeichen() {
        return this.zeichen;
    }

    public String getFarbe() {
        return this.farbe;
    }

}
