/*
 * \file Stats.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant la collecte et l'affichage des resultats de simulation
 *
 */

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


    public void affichageResultats(int duree, double lambda, double mu, Evt dernier_evenement) {

        this.nombre_moyen_client = (this.nombre_moyen_client / dernier_evenement.getDate());
        this.temps_moyen_sejour = (this.temps_moyen_sejour / (iterator_client - 1));

        double prob_sans_attente = (double) sans_attente / (iterator_client - 1);
        double prob_avec_attente = (double) avec_attente / (iterator_client - 1);

        System.out.println("ITERATOR " + this.sans_attente);

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

    }

}
