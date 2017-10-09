/*
 * \file Evt.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant la gestion des evenements
 *
 */

public class Evt {
    protected int type; // 1 arrivee et 0 depart
    protected int no_client;
    protected double date;
    protected double date_debug; // Cas type depart

    public Evt(int type, double date, int no_client) {
        this.type = type;
        this.date = date;
        this.no_client = no_client;
    };

    public Evt(int type, double date, int no_client, double date_arrive) {
        this.type = type;
        this.date = date;
        this.no_client = no_client;
        if (type == 0)
            this.date_debug = date_arrive;
    };

    public void affichage() {
        if (type == 0)
            System.out.println("Date=" + date + "\t Depart  client #" + no_client + "\t arrive a t=" + date_debug);
        else
            System.out.println("Date=" + date + "\t Arrivee client #" + no_client);

    }

    public double getDate() {
        return date;
    }

    public double getDuree() {
        return (date - date_debug);
    }

    public int getType() {
        return type;
    }

}
