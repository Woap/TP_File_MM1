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

import java.io.FileWriter;
import java.io.IOException;


public class Utile {

    public static double loiExponentielle(double date, double param) {
        double random = randomDouble();
        return (date + (-Math.log(1 - random) / param));
    }

    public static double randomDouble() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    public static LinkedList < Evt > ajoutTrie(Evt e, LinkedList < Evt > l) {
        l.add(e);
        Collections.sort(l, new Comparator < Evt > () {
            @Override
            public int compare(Evt e1, Evt e2) {
                return e1.compareTo(e2);
            }
        });
        return l;
    }

		public static void affichageResultats(int duree,double lambda,double mu,int iterator_client,int sans_attente,int avec_attente,double nombre_moyen_client,double temps_moyen_sejour,int no_echeancier)
		{

      double prob_sans_attente = (double)sans_attente/iterator_client;
      double prob_avec_attente = (double)avec_attente/iterator_client;


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
      System.out.println(" Nb moyen de clients dans systeme = " + nombre_moyen_client);
      System.out.println(" Temps moyen de sejour = " + temps_moyen_sejour);
      resultatToJson(iterator_client,prob_sans_attente,prob_avec_attente,(double)iterator_client/duree,nombre_moyen_client,temps_moyen_sejour,no_echeancier);
		}

    public static void resultatToJson(int iterator_client,double prob_sans_attente,double prob_avec_attente,double debit,double nombre_moyen_client,double temps_moyen_sejour,int no_echeancier)
    {


      try {
          int[] r;
          FileWriter writer;
          writer = new FileWriter("log.js", true);
          writer.append("\n{ t: '" + no_echeancier + "', nbclient: '" + iterator_client + "', sans_att: '" + prob_sans_attente + "', avec_att: '" + prob_avec_attente + "', debit: '" + debit + "', nb_client: '" + nombre_moyen_client + "', tmps: '" + temps_moyen_sejour + "'},");

          writer.close();

      } catch (IOException e) {
          System.out.println(e);
      }

		}


}
