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

import java.util.List;
import java.util.ListIterator;


public class Utile {

    // inter-arrivées ou durées de service selon param
    public static double loiExponentielle(double date, double param) {
        double random = randomDouble();
        return (date + (-Math.log(1 - random) / param));
    }

    public static double randomDouble() {
        return ThreadLocalRandom.current().nextDouble(0, 1);
    }

    // permet de faire un ajout de manière chronologique
    public static LinkedList < Evt > ajoutTrie(Evt e, LinkedList < Evt > l) {
        int pos = rechercheDichotomique(l, e.getDate(), 0, l.size() - 1);
        if (pos >= 0) {
            l.add(pos, e);
        }
        return l;
    }

    public static int rechercheDichotomique(LinkedList < Evt > l, double evt, int imin, int imax) {
        int imid;
        while (imin <= imax) {
            imid = (imin + imax) / 2;
            if (l.get(imid).getDate() < evt)
                imin = imid + 1;
            else if (l.get(imid).getDate() > evt)
                imax = imid - 1;
            else
                return imid;
        }
        return imin;
    }
}
