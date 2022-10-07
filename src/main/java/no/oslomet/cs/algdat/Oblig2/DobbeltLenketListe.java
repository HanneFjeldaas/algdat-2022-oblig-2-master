package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }

    }
    // instansvariabler
    private Node hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen


    public DobbeltLenketListe() {
    }

    public DobbeltLenketListe(T[] a) {
        //Tabellen skal ikke være tom:
        Objects.requireNonNull(a);

        //Feilmelding om den er tom:
        if(a.length<1){
            System.out.println("Tabellen er tom");
        }

        for(T etObjekt : a){
            //hoppe over nullverdier i tabellen
            if(etObjekt==null){
                continue;
            }
            //putte data fra tabellen inn i en node
            Node temp = new Node("");
            temp.verdi = etObjekt;

            //sjekk om node-listen er tom
            if(hode==null){
                hode = temp;
            }
            else{
                //finne siste node
                Node siste = hode;
                while(siste.neste != null){
                    siste = siste.neste;
                }
                siste.neste = temp;
            }
        }
    }






    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }


    public int antall() {
        antall = 0;

        //sjekk om listen er tom
        if(hode == null){
            return antall;
        }

        //opprette hjelpenode, setter den på starten
        Node node;
        node = hode;

        while(node != null){    //så lenge node ikke er null
            antall++;           //øker antallet med 1
            node = node.neste;  //settes til neste node
        }
        return antall;
    }

    public boolean tom() {
        if(hode == null){
            return true;
        }
        return false;

    }


    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);

        //opprette en node med input-verdien:
        Node nyNode = new Node("");
        nyNode.verdi = verdi;

        if(hode == null){
            hode = hale = nyNode;
            nyNode.forrige = null;
            nyNode.neste = null;
        }
        else {
            Node node = new Node("");
            node = hode;
            while(node.neste != null){
                node = node.neste;
            }
            node.neste = nyNode;
            nyNode.forrige = node;
            hale = nyNode;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }


    //Oppgave 3 finnNode

    public Node<T> finnNode(int indeks){

        //Programkode 3.3.3 a) fra kompendiet
        if(indeks<2){
            Node<T> temp = hode;
            if(indeks == 0){
                return temp;
            }
            for(int i = 0; i<indeks;i++){
                temp = temp.neste;
            }
            return temp;
        }

        //søke fra hale og bakover:

        else {
            Node <T> temp = hale;
            if(indeks == antall-1){
                return temp;
            }
            else{
                temp = temp.forrige;
            }
            return temp;
        }
    }

    //hentet fra kompendiet, programkode 3.3.3 b)
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }


    public String toString() {
        StringBuilder ut = new StringBuilder();
        ut.append("[");
        Node node = hode;
        String fyll = "";
        if(hode == null){
            ut.append("]");
            return ut.toString();
        }
        while (node != null){
            ut.append(fyll + node.verdi);
            node = node.neste;
            fyll = ", ";
        }
        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        StringBuilder ut = new StringBuilder();
        ut.append("[");
        Node node = hale;
        String fyll = "";
        if(hale == null){
            ut.append("]");
            return ut.toString();
        }
        while (hale != null){
            ut.append(fyll + hale.verdi);
            hale = hale.forrige;
            fyll = ", ";
        }
        ut.append("]");
        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


