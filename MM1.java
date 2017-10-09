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
        if (Integer.parseInt(args[3]) == 0 || Integer.parseInt(args[3]) == 1) // Cas debug 0 = fonctionnement normal / Cas debug 1 = mode debug
        {
            this.debug = Integer.parseInt(args[3]);
            echeancier = new Ech(duree, lambda, mu, debug);
        } else if (Integer.parseInt(args[3]) == 2) // Cas debug 2 = Generation des logs
        {
            this.debug = Integer.parseInt(args[3]);
            try {
                int[] r;
                FileWriter writer;
                writer = new FileWriter("Logs/log.js", false);
                writer.append("var data = [");
                writer.close();

            } catch (IOException e) {
                System.out.println(e);
            }

            for (int i = 1; i < duree; i++) {
                i = i * 2;
                for (int j = 0; j < 5; j++)
                    echeancier = new Ech(i + j, lambda, mu, debug);
            }

            try {
                int[] r;
                FileWriter writer;
                writer = new FileWriter("Logs/log.js", true);
                writer.append("\n],\n\tconfig = {\n\t\tdata: data,\n\t\txkey: 'duree',\n\t\tykeys: ['sans_att'],\n\t\tgoals : [0.1666666666],\n\t\tgoalLineColors : ['red','red']\n\t\t,labels: ['sans_att'],\n\t\tfillOpacity: 0.8,\n\t\thideHover: 'auto',\n\t\tbehaveLikeLine: true,\n\t\tresize: true,\n\t\tpointFillColors:['#ffffff'],\n\t\tpointStrokeColors: ['black'],\n\t\tparseTime:false,\n\t\tlineColors:['black','black']\n\t};\nconfig.element = '" + "log1" + "';\nMorris.Line(config);");
                writer.close();

            } catch (IOException e) {
                System.out.println(e);
            }
        }

    }
}
