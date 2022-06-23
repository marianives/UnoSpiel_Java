package uno.kartenspiel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author M-N
 */
public class Spiel {

    private final List<Spieler> spieler = new ArrayList();
    private final List<Karte> karten = new ArrayList<>();
    private int anzSpieler;
    private boolean gameOver = false;
    private int karteZumLegen;
    private int farbeWaehlen;
    private String neueFarbe;
    private boolean karteGelegt = false;
    private int anzKartenZumNehmen = 1;
    private String gewinner;
    private boolean zahlEingegeben;
    private boolean farbeEingegeben;
    private boolean spielerEingegeben;

    public void start() {
        Tisch tisch = new Tisch();
        System.out.println("*UNO* Kartenspiel by M-N");
        System.out.println("Bitte gib die Anzahl spieler ein:");
        Scanner sc = new Scanner(System.in);

        do {
            spielerEingegeben = true;
            try {

                anzSpieler = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Gib eine richtige Zahl ein, bitte.");
                sc.nextLine();
                spielerEingegeben = false;
            }
        } while (spielerEingegeben == false);

        for (int i = 0; i < anzSpieler; i++) {
            System.out.println("Bitte gib den Namen von Spieler " + (i + 1) + " ein.");

            String name = sc.next();
            spieler.add(new Spieler(name));
        }

        createCards();
        kartenVerteilen();

        tisch.layStartCard(karten.get(0));
        karten.remove(0);
        do {
            for (Spieler spielKartenLegen : spieler) {
                System.out.println();
                System.out.print("Karte auf dem Tisch: ");
                System.out.println(tisch.getKarteAufTisch());
                System.out.println();

                String name = spielKartenLegen.getName();
                System.out.println("Spieler " + name + " ist dran.");
                System.out.println();
                System.out.println("Hier deine Karten: ");
                System.out.println(spielKartenLegen.showMyCards());

                System.out.println("Welche Karte möchtest du legen?");
                karteGelegt = false;
                System.out.println();
                System.out.println();

                do {
                    zahlEingegeben = true;
                    try {

                        karteZumLegen = sc.nextInt();
                        System.out.println("Diese Karte möchtest du legen: " + spielKartenLegen.getCard(karteZumLegen));
                    } catch (Exception e) {
                        System.out.println("Gib eine gültige zahl ein.");
                        sc.nextLine();
                        zahlEingegeben = false;
                    }
                } while (zahlEingegeben == false);

                //Falls eine +4 oder +2 Karte auf dem Tisch ist, bekommt der Spieler 2 o.4 Karten
                if (tisch.getKarteAufTisch().getZeichen().equals("+2") || tisch.getKarteAufTisch().getZeichen().equals("+4")) {
                    System.out.println("Du musst " + tisch.getKarteAufTisch().getZeichen() + " Karten nehmen!");
                    if (tisch.getKarteAufTisch().getZeichen().equals("+2")) {
                        spielKartenLegen.giveCard(karten.get(0));
                        System.out.println("Dir wurde eine neue Karte gegeben: " + karten.get(0));
                        karten.remove(0);
                        spielKartenLegen.giveCard(karten.get(0));
                        System.out.println("Dir wurde eine neue Karte gegeben: " + karten.get(0));
                        karten.remove(0);
                    } else {
                        for (int i = 0; i < 4; i++) {
                            spielKartenLegen.giveCard(karten.get(0));
                            System.out.println("Dir wurde eine neue Karte gegeben: " + karten.get(0));
                            karten.remove(0);
                        }
                    }
                }

                //Falls die Karte auf dem Tisch die gleiche Farbe oder Zeichen haben wird die Karte gelegt. Bei Schwarzen karten kann man immer legen.
                if (spielKartenLegen.getCard(karteZumLegen).getFarbe().equals(tisch.getKarteAufTisch().getFarbe()) || spielKartenLegen.getCard(karteZumLegen).getZeichen().equals(tisch.getKarteAufTisch().getZeichen()) || tisch.getKarteAufTisch().getFarbe().equals("Schwarz") || spielKartenLegen.getCard(karteZumLegen).getFarbe().equals("Schwarz")) {                    tisch.layCard(spielKartenLegen.getCard(karteZumLegen));
                    spielKartenLegen.dropCard(spielKartenLegen.getCard(karteZumLegen));
                    karteGelegt = true;

                }

                //Falls der Spieler eine +2 o. +4 Karte legen moechte
                if (spielKartenLegen.getCard(karteZumLegen).getZeichen().equals("+2") || spielKartenLegen.getCard(karteZumLegen).getZeichen().equals("+4")) {
                    tisch.layCard(spielKartenLegen.getCard(karteZumLegen));
                    spielKartenLegen.dropCard(spielKartenLegen.getCard(karteZumLegen));
                    karteGelegt = true;
                }
                if (karteGelegt == false) {
                    spielKartenLegen.giveCard(karten.get(0));
                    System.out.println("Du kannst diese Karte nicht legen");
                    System.out.println("Dir wurde eine neue Karte gegeben: " + karten.get(0));
                    karten.remove(0);
                }
                for (Spieler spieler : spieler) {
                    if (spieler.zaehleMeineKarten() == 0) {
                        gewinner = spieler.getName();
                        gameOver = true;
                    }
                }
            }
        } while (gameOver == false);

        System.out.println("Das Spiel ist beendet!!");
        System.out.println();
        System.out.println("Der Spieler " + gewinner + " hat das Spiel gewonnen!");
        System.out.println();
        System.out.println("Gratuliere!!");
    }

    private Karte getKarte(int kartenNr) {
        return karten.get(kartenNr);
    }

    public void kartenVerteilen() {
        for (Spieler i : spieler) {
            for (int j = 0; j < 7; j++) {
                i.giveCard(karten.get(j));
                karten.remove(j);
            }
        }
    }

    private void createCards() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                karten.add(new Karte(Integer.toString(j), "Blau"));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                karten.add(new Karte(Integer.toString(j), "Grün"));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                karten.add(new Karte(Integer.toString(j), "Rot"));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                karten.add(new Karte(Integer.toString(j), "Gelb"));
            }
        }
        for (int r = 0; r < 4; r++) {
            karten.add(new Karte("+4", "Schwarz"));
        }
        for (int r = 0; r < 4; r++) {
            karten.add(new Karte("+2", "Schwarz"));
        }
        for (int r = 0; r < 4; r++) {
            karten.add(new Karte("(@)", "Schwarz"));
        }
        Collections.shuffle(karten);
    }

    public void löscheAlleKarten() {
        karten.clear();
        for (Spieler kartenLöscher : spieler) {
            kartenLöscher.löscheMeineKarten();
        }
    }

    public boolean hatEsNochKarten() {
        boolean hatNochKarten = true;
        if (karten.isEmpty()) {
            hatNochKarten = false;
        }
        return hatNochKarten;
    }
}
