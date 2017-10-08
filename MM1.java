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

        try {
            int[] r;
            FileWriter writer;
            writer = new FileWriter("log.js", true);
            writer.append("var data = [");

            //writer.flush();
            //writer.append("\n],\n\tconfig = {\n\t\tdata: data,\n\t\txkey: 't',\n\t\tykeys: ['or', 'argent','bronze'],\n\t\tlabels: ['or', 'argent','bronze'],\n\t\tfillOpacity: 0.8,\n\t\thideHover: 'auto',\n\t\tbehaveLikeLine: true,\n\t\tresize: true,\n\t\tpointFillColors:['#ffffff'],\n\t\tpointStrokeColors: ['black'],\n\t\tparseTime:false,\n\t\tlineColors:['#f4bf42','red','blue']\n\t};\nconfig.element = '" + "log1" + "';\nMorris.Line(config);");
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

        for ( int  i = 0 ; i < 10 ; i++)
          echeancier = new Ech(duree, lambda, mu, debug,i);

          try {
              int[] r;
              FileWriter writer;
              writer = new FileWriter("log.js", true);
              writer.append("\n],\n\tconfig = {\n\t\tdata: data,\n\t\txkey: 't',\n\t\tykeys: ['nbclient'],\n\t\tlabels: ['nbclient'],\n\t\tfillOpacity: 0.8,\n\t\thideHover: 'auto',\n\t\tbehaveLikeLine: true,\n\t\tresize: true,\n\t\tpointFillColors:['#ffffff'],\n\t\tpointStrokeColors: ['black'],\n\t\tparseTime:false,\n\t\tlineColors:['#f4bf42','red','blue']\n\t};\nconfig.element = '" + "log1" + "';\nMorris.Line(config);");
              writer.close();

          } catch (IOException e) {
              System.out.println(e);
          }


    }


}
