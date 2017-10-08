/*
 * \file MM1.java
 * \author IBIS Ibrahim
 * \date 29 septembre 2017
 *
 * Classe permettant le lancement de la simulation
 *
 */


import java.io.FileWriter;
import java.io.IOException;

public class MM1 {
    protected int lambda;
    protected int mu;
    protected int duree;
    protected int debug;
    protected Ech echeancier;

    public static void main(String[] args) {
        try {
            MM1 programme = new MM1();
            programme.lancement(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lancement(String[] args) {

        this.lambda = Integer.parseInt(args[0]);
        this.mu = Integer.parseInt(args[1]);
        this.duree = Integer.parseInt(args[2]);
        if (Integer.parseInt(args[3]) == 0 || Integer.parseInt(args[3]) == 1) {
            this.debug = Integer.parseInt(args[3]);
        } else {
            System.out.println("Valeur debug inconnue");
        }

        echeancier = new Ech(duree, lambda, mu, debug);
    }
}
