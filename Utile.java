/*
 * \file Utile.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe regroupant toutes les methodes statiques utiles
 *
 */


import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Utile {

    public static double loi_exponentielle(double date, double param) {
        double random = random_double();
        return (date + (-Math.log(1 - random) / param));
    }

    public static double random_double() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    public static LinkedList < Evt > ajout_trie(Evt e, LinkedList < Evt > l) {
        l.add(e);
        Collections.sort(l, new Comparator < Evt > () {
            @Override
            public int compare(Evt e1, Evt e2) {
                return e1.compareTo(e2);
            }
        });
        return l;
    }

		public static void affichageResultats(int duree,double lambda,double mu,int iterator_client,int sans_attente,int avec_attente)
		{

      double prob_sans_attente = (double)sans_attente/iterator_client;
      double prob_avec_attente = (double)avec_attente/iterator_client;
      double nombre_moyen_client;
      double temps_moyen_sejour;

      System.out.println("----------------------");
      System.out.println(" RESULTATS THEORIQUES");
      System.out.println("----------------------");

      if ( lambda < mu )
        System.out.println(" lambda<mu : file stable ");
      else
        System.out.println(" lambda>mu : file non stable ");
      System.out.println(" ro (lambda/mu) = " + lambda/mu);
      System.out.println(" nombre de clients attendus (lambda x duree) = " + lambda * duree);
      System.out.println(" Prob de service sans attente (1 - ro) = " + (1 - (lambda/mu)));
      System.out.println(" Prob file occupee (ro) = " + lambda/mu);
      System.out.println(" Debit (lambda) = " + lambda);
      System.out.println(" Esp nb clients (ro/1-ro) = " + (lambda/mu)/(1-(lambda/mu)) );
      System.out.println(" Temps moyen de sejour (1/mu(1-ro)) = " + 1/(mu*(1-(lambda/mu))));



      System.out.println("----------------------");
      System.out.println(" RESULTATS THEORIQUES");
      System.out.println("----------------------");

      System.out.println(" Nombre total de clients = " + iterator_client);
      System.out.println(" Proportion clients sans attente = " + prob_sans_attente);
      System.out.println(" Proportion clients avec attente = " + prob_avec_attente);
      System.out.println(" Debit = " + (double)iterator_client/duree);
      System.out.println(" Nb moyen de clients dans systeme = " + prob_avec_attente/(1-prob_avec_attente));
      System.out.println(" Temps moyen de sejour = " + 1/(mu*(1-prob_avec_attente)));


		}

}
