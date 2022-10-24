package no.oslomet.cs.algdat.Oblig2;

public class Main {

    public static void main(String[]args){

        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();

        liste.leggInn(4);  // ny verdi i tom liste
        liste.leggInn(2);  // ny verdi legges forrest
        liste.leggInn(6);  // ny verdi legges bakerst
        liste.leggInn(3);  // ny verdi nest forrest
        liste.leggInn(5);  // ny verdi nest bakerst
        liste.leggInn(1);  // ny verdi forrest
        liste.leggInn(7);  // ny verdi legges bakerst
        System.out.println(liste);
        System.out.println(liste.subliste(1,3));
    }

}
