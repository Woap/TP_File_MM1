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
    protected double date_debug; // Cas debug = 1

    public Evt(int type, double date, int no_client, double date_arrive)
		{
        this.type = type;
        this.date = date;
        this.no_client = no_client;
        if (type == 0)
            this.date_debug = date_arrive;
    };

    public Evt(int type, double date, int no_client)
		{
        this.type = type;
        this.date = date;
        this.no_client = no_client;
    };

    public void affichage() {
            if (type == 0)
                System.out.println("Date=" + date + "\t Depart  client #" + no_client + "\t arrive a t=" + date_debug);
            else
                System.out.println("Date=" + date + "\t Arrivee client #" + no_client);
    }

		public int compareTo(Evt e) {
        if (this.date < e.date)
            return -1;
        else
            return 1;
    }

    protected double get_date() {
        return date;
    }

    protected int get_type() {
        return type;
    }



}
