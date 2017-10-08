/*
 * \file Stats.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant la collecte et l'affichage des resultats de simulation
 *
 */

public class Stats {


    public static void affichageResultats(int duree, double lambda, double mu, int iterator_client, int sans_attente, int avec_attente, double nombre_moyen_client, double temps_moyen_sejour, int no_echeancier) {

        double prob_sans_attente = (double) sans_attente / iterator_client;
        double prob_avec_attente = (double) avec_attente / iterator_client;


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
        System.out.println(" RESULTATS THEORIQUES");
        System.out.println("----------------------");

        System.out.println(" Nombre total de clients = " + iterator_client);
        System.out.println(" Proportion clients sans attente = " + prob_sans_attente);
        System.out.println(" Proportion clients avec attente = " + prob_avec_attente);
        System.out.println(" Debit = " + (double) iterator_client / duree);
        System.out.println(" Nb moyen de clients dans systeme = " + nombre_moyen_client);
        System.out.println(" Temps moyen de sejour = " + temps_moyen_sejour);

    }

}
