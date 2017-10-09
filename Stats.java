/*
 * \file Stats.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant la collecte et l'affichage des resultats de simulation
 *
 */

import java.io.FileWriter;
import java.io.IOException;

public class Stats {

    public int nombre_client_actuel;
    public int iterator_client; // numero client
    public int sans_attente; // nombre de client sans attente
    public int avec_attente; // nombre de client avec attente
    public double nombre_moyen_client;
    public double temps_moyen_sejour;
    public double debut;

    public Stats() {
        this.nombre_client_actuel = 0;
        this.iterator_client = 0;
        this.sans_attente = 0;
        this.avec_attente = 0;
        this.nombre_moyen_client = 0.0;
        this.temps_moyen_sejour = 0.0;
        this.debut = 0.0;
    };

    public void departClient(double fin) {
        this.nombre_moyen_client += this.nombre_client_actuel * (fin - this.debut);
        this.debut = fin;
        this.nombre_client_actuel--;
    }

    public void arriveeClient(double fin) {
        this.nombre_moyen_client += this.nombre_client_actuel * (fin - this.debut);
        this.debut = fin;
        this.nombre_client_actuel++;
    }


    public void affichageResultats(int duree, double lambda, double mu, Evt dernier_evenement, int debug) {

        this.nombre_moyen_client = (this.nombre_moyen_client / dernier_evenement.getDate());
        this.temps_moyen_sejour = (this.temps_moyen_sejour / (iterator_client - 1));

        double prob_sans_attente = (double) sans_attente / (iterator_client - 1);
        double prob_avec_attente = (double) avec_attente / (iterator_client - 1);

        System.out.println("----------------------");
        System.out.println(" RESULTATS THEORIQUES");
        System.out.println("----------------------");

        if (lambda < mu)
            System.out.println(" lambda<mu : file stable ");
        else
            System.out.println(" lambda>mu : file non stable ");
        System.out.println(" ro (lambda/mu) = " + lambda / mu);
        System.out.println(" nombre de clients attendus (lambda x duree) = " + lambda * duree);
        System.out.println(" Prob de service sans attente (1 - ro) = " + (1 - (lambda / mu)));
        System.out.println(" Prob file occupee (ro) = " + lambda / mu);
        System.out.println(" Debit (lambda) = " + lambda);
        System.out.println(" Esp nb clients (ro/1-ro) = " + (lambda / mu) / (1 - (lambda / mu)));
        System.out.println(" Temps moyen de sejour (1/mu(1-ro)) = " + 1 / (mu * (1 - (lambda / mu))));

        System.out.println("----------------------");
        System.out.println(" RESULTATS SIMULATION");
        System.out.println("----------------------");

        System.out.println(" Nombre total de clients = " + (this.iterator_client - 1));
        System.out.println(" Proportion clients sans attente = " + prob_sans_attente);
        System.out.println(" Proportion clients avec attente = " + prob_avec_attente);
        System.out.println(" Debit = " + (double)(this.iterator_client - 1) / duree);
        System.out.println(" Nb moyen de clients dans systeme = " + this.nombre_moyen_client);
        System.out.println(" Temps moyen de sejour = " + this.temps_moyen_sejour);


        if (debug == 2)
            resultatToJson(lambda, mu, duree, this.iterator_client - 1, prob_sans_attente, prob_avec_attente, (double)(iterator_client - 1) / duree, this.nombre_moyen_client, this.temps_moyen_sejour);

    }

    public static void resultatToJson(double lambda, double mu, int duree, int iterator_client, double prob_sans_attente, double prob_avec_attente, double debit, double nombre_moyen_client, double temps_moyen_sejour) {

        try {
            int[] r;
            FileWriter writer;
            writer = new FileWriter("Logs/log.js", true);
            writer.append("\n{ duree: '" + duree + "', lambda: '" + lambda + "', mu: '" + mu + "', nbclient: '" + iterator_client + "', sans_att: '" + prob_sans_attente + "', avec_att: '" + prob_avec_attente + "', debit: '" + debit + "', nombre_moyen_client: '" + nombre_moyen_client + "', temps_moyen_sejour: '" + temps_moyen_sejour + "'},");

            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
