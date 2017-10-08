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

    protected int iterator_client; // numero client
    protected int sans_attente; // nombre de client sansattente
    protected int avec_attente; // nombre de client avec attente
    protected double nombre_moyen_client;
    protected double temps_moyen_sejour;

    protected int no_echeancier;

    public Ech(int duree, int lambda, int mu, int debug) {
        this.duree = duree;
        this.lambda = lambda;
        this.mu = mu;
        this.debug = debug;

        // On initialise l'echeancier avec le premier evenement
        Evt Init = new Evt(1, 0.0, 0);
        this.liste_echeancier = new LinkedList < Evt > ();
        liste_echeancier.add(Init);

        this.iterator_client = 0;
        this.sans_attente = 0;
        this.avec_attente = 0;
        this.nombre_moyen_client = 0.0;
        this.temps_moyen_sejour = 0.0;

        lancement();
    };

    public void lancement() {

        Evt evenement_actuel = liste_echeancier.getFirst(); // evenemnt arrivee du premier client
        double temps_arrivee_n = evenement_actuel.get_date();
        double temps_arrivee_nplusun = Utile.loiExponentielle(temps_arrivee_n, lambda);
        double temps_depart_n = Utile.loiExponentielle(temps_arrivee_n, mu);

        Evt depart_actuel = new Evt(0, temps_depart_n, iterator_client, temps_arrivee_n); // evenement depart du premier client
        this.liste_echeancier = Utile.ajoutTrie(depart_actuel, liste_echeancier);

        this.iterator_client++;
        Evt arrivee_prochain = new Evt(1, temps_arrivee_nplusun, iterator_client); // evenement arrivee du prochaine client
        this.liste_echeancier = Utile.ajoutTrie(arrivee_prochain, liste_echeancier);

        if (debug == 1)
            evenement_actuel.affichage();


        this.sans_attente++;
        this.nombre_moyen_client += liste_echeancier.size();
        this.temps_moyen_sejour += temps_depart_n - temps_arrivee_n;

        liste_echeancier.removeFirst();

        // Boucle principale
        while (liste_echeancier.size() != 0) {

            evenement_actuel = liste_echeancier.getFirst(); // evenement en tete de liste ( client n )

            if (evenement_actuel.get_type() == 0) // cas evenement de type DEPART
            {
                if (debug == 1)
                    evenement_actuel.affichage();

                this.nombre_moyen_client += liste_echeancier.size();
                liste_echeancier.removeFirst();
            } else // ARRIVEE
            {
                temps_arrivee_n = temps_arrivee_nplusun;
                temps_arrivee_nplusun = Utile.loiExponentielle(temps_arrivee_n, lambda);
                if (temps_depart_n > temps_arrivee_n) {
                    this.avec_attente++;
                    temps_depart_n = Utile.loiExponentielle(temps_depart_n, mu);
                } else {
                    this.sans_attente++;
                    temps_depart_n = Utile.loiExponentielle(temps_arrivee_n, mu);
                }


                depart_actuel = new Evt(0, temps_depart_n, this.iterator_client, temps_arrivee_n); // evenement depart ( client n )
                this.liste_echeancier = Utile.ajoutTrie(depart_actuel, liste_echeancier);

                this.iterator_client++;
                if (temps_arrivee_nplusun < duree) {
                    arrivee_prochain = new Evt(1, temps_arrivee_nplusun, this.iterator_client); // evenement arrivee du prochain client ( client n+1 )
                    this.liste_echeancier = Utile.ajoutTrie(arrivee_prochain, liste_echeancier);
                }

                if (debug == 1)
                    evenement_actuel.affichage();


                this.nombre_moyen_client += liste_echeancier.size();
                this.temps_moyen_sejour += temps_depart_n - temps_arrivee_n;
                liste_echeancier.removeFirst();
            }
        }

        nombre_moyen_client = (nombre_moyen_client / ((iterator_client - 1) * 4));
        temps_moyen_sejour = (temps_moyen_sejour / (iterator_client - 1));
        Stats.affichageResultats(duree, lambda, mu, iterator_client - 1, sans_attente - 1, avec_attente, nombre_moyen_client, temps_moyen_sejour, no_echeancier);
    }
}
