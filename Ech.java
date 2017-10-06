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
    protected LinkedList < Evt > Liste_echeancier;
    protected int duree;
    protected double lambda;
    protected double mu;
    protected int debug;

    protected int iterator_client;
    protected Utile Utile;

		protected int sans_attente;
		protected int avec_attente;


    public Ech(int duree, int lambda, int mu, int debug) {
        this.duree = duree;
        this.lambda = lambda;
        this.mu = mu;
        this.debug = debug;

				this.sans_attente = 0;
				this.avec_attente = 0;

				Evt Init = new Evt(1, 0.0, 0);
        this.Liste_echeancier = new LinkedList < Evt > ();
        Liste_echeancier.add(Init);
        this.iterator_client = 0; // Nombre de client

        lancement();

    };

    public void lancement() {

				Evt Evenement_actuel = Liste_echeancier.getFirst();
        double temps_arrivee_n = Evenement_actuel.get_date();
        double temps_arrivee_nplusun = Utile.loi_exponentielle(temps_arrivee_n, lambda);
        double temps_depart_n = Utile.loi_exponentielle(temps_arrivee_n, mu);

        Evt Depart_actuel = new Evt(0, temps_depart_n, iterator_client, temps_arrivee_n);
        this.Liste_echeancier = Utile.ajout_trie(Depart_actuel, Liste_echeancier);

        this.iterator_client++;
        Evt Arrivee_prochain = new Evt(1, temps_arrivee_nplusun, iterator_client);
        this.Liste_echeancier = Utile.ajout_trie(Arrivee_prochain, Liste_echeancier);

        if ( debug == 1)
          Evenement_actuel.affichage();
        this.sans_attente++;
        Liste_echeancier.removeFirst();

        while (Liste_echeancier.size() != 0) {

            Evenement_actuel = Liste_echeancier.getFirst();
            if (Evenement_actuel.get_type() == 0) // DEPART
            {
                if ( debug == 1)
                  Evenement_actuel.affichage();
                Liste_echeancier.removeFirst();

            }
						else // ARRIVEE
            {
                temps_arrivee_n = temps_arrivee_nplusun;
                temps_arrivee_nplusun = Utile.loi_exponentielle(temps_arrivee_n, lambda);
                if (temps_depart_n > temps_arrivee_n)
								{
										this.avec_attente++;
                    temps_depart_n = Utile.loi_exponentielle(temps_depart_n, mu);
								}
                else
								{
										this.sans_attente++;
                    temps_depart_n = Utile.loi_exponentielle(temps_arrivee_n, mu);
								}

                Depart_actuel = new Evt(0, temps_depart_n, this.iterator_client, temps_arrivee_n);
                this.Liste_echeancier = Utile.ajout_trie(Depart_actuel, Liste_echeancier);

                this.iterator_client++;
                if (temps_arrivee_nplusun < duree)
								{
                    Arrivee_prochain = new Evt(1, temps_arrivee_nplusun, this.iterator_client);
                    this.Liste_echeancier = Utile.ajout_trie(Arrivee_prochain, Liste_echeancier);
                }

                if ( debug == 1)
                  Evenement_actuel.affichage();
                Liste_echeancier.removeFirst();

            }
        }
        if ( debug == 1)
				    Utile.affichageResultats(duree,lambda,mu,iterator_client-1,sans_attente-1,avec_attente);
    }

}
