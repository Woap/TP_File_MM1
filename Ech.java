/*
 * \file Ech.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant le gestion de l'echeancier
 *
 */

import java.util.LinkedList;

public class Ech {
    protected LinkedList < Evt > liste_echeancier;
    protected int duree;
    protected double lambda;
    protected double mu;
    protected int debug;

    protected Stats stats;

    public Ech(int duree, int lambda, int mu, int debug) {
        this.duree = duree;
        this.lambda = lambda;
        this.mu = mu;
        this.debug = debug;

        // On initialise l'echeancier avec le premier evenement
        Evt Init = new Evt(1, 0.0, 0);
        this.liste_echeancier = new LinkedList < Evt > ();
        liste_echeancier.add(Init);

        this.stats = new Stats();

        stats.nombre_client_actuel++;
        lancement();
    };

    public void lancement() {

        Evt evenement_actuel = liste_echeancier.getFirst(); // evenemnt arrivee du premier client
        stats.debut = evenement_actuel.getDate();
        double temps_arrivee_n = evenement_actuel.getDate();
        double temps_arrivee_nplusun = Utile.loiExponentielle(temps_arrivee_n, lambda);
        double temps_depart_n = Utile.loiExponentielle(temps_arrivee_n, mu);

        Evt depart_actuel = new Evt(0, temps_depart_n, stats.iterator_client, temps_arrivee_n); // evenement depart du premier client
        this.liste_echeancier = Utile.ajoutTrie(depart_actuel, liste_echeancier);

        stats.iterator_client++;
        Evt arrivee_prochain = new Evt(1, temps_arrivee_nplusun, stats.iterator_client); // evenement arrivee du prochaine client
        this.liste_echeancier = Utile.ajoutTrie(arrivee_prochain, liste_echeancier);


        if (debug == 1)
            evenement_actuel.affichage();

        stats.sans_attente++;
        stats.temps_moyen_sejour += temps_depart_n - temps_arrivee_n;

        liste_echeancier.removeFirst();

        // Boucle principale
        while (liste_echeancier.size() != 0) {

            evenement_actuel = liste_echeancier.getFirst(); // evenement en tete de liste ( client n )

            if (evenement_actuel.getType() == 0) // cas evenement de type DEPART
            {
                if (debug == 1)
                    evenement_actuel.affichage();

                stats.departClient(evenement_actuel.getDate());
                liste_echeancier.removeFirst();
            } else // ARRIVEE
            {
                temps_arrivee_n = temps_arrivee_nplusun;
                temps_arrivee_nplusun = Utile.loiExponentielle(temps_arrivee_n, lambda);
                if (temps_depart_n > temps_arrivee_n) {
                    stats.avec_attente++;
                    temps_depart_n = Utile.loiExponentielle(temps_depart_n, mu);
                } else {
                    stats.sans_attente++;
                    temps_depart_n = Utile.loiExponentielle(temps_arrivee_n, mu);
                }


                depart_actuel = new Evt(0, temps_depart_n, stats.iterator_client, temps_arrivee_n); // evenement depart ( client n )
                this.liste_echeancier = Utile.ajoutTrie(depart_actuel, liste_echeancier);

                stats.iterator_client++;
                if (temps_arrivee_nplusun < duree) {
                    arrivee_prochain = new Evt(1, temps_arrivee_nplusun, stats.iterator_client); // evenement arrivee du prochain client ( client n+1 )
                    this.liste_echeancier = Utile.ajoutTrie(arrivee_prochain, liste_echeancier);

                    stats.arriveeClient(evenement_actuel.getDate());
                }

                if (debug == 1)
                    evenement_actuel.affichage();

                stats.temps_moyen_sejour += temps_depart_n - temps_arrivee_n;
                liste_echeancier.removeFirst();
            }
        }


        stats.affichageResultats(duree, lambda, mu, evenement_actuel, debug);
    }
}
