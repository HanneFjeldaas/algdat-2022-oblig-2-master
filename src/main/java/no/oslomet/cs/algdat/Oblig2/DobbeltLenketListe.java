package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
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
        this.antall = 0;
        this.hode = null;
        this.hale = null;
        this.endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        //Tabellen skal ikke være tom:
        Objects.requireNonNull(a, "Tabellen er tom");

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

    // fratilKontroll() fra kompendiet, Programkode 1.2.3 a)

    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                               // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor listen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                             // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }


    //3b - funker ikke
    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(this.antall, fra, til);

        Liste<T> nyListe = new DobbeltLenketListe<>();

        int start = fra;
        Node<T> current = hode;
        while(start>0){
            current=current.neste;
            start--;
        }
        for(int i = fra; i < til; i++){
            nyListe.leggInn(current.verdi);
            nyListe.antall();
            current = current.neste;
        }
        /*
        Node<T> current = finnNode(fra);
        Node<T> dupe = finnNode(fra);
        for(int i = fra; i<til; i++){
            dupe.neste = current.neste;
            dupe.forrige = current.forrige;
            dupe.verdi = current.verdi;
            nyListe.leggInn(dupe.verdi);
            current = current.neste;
        }

         */
        return nyListe;
    }

    @Override
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

    @Override
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


    //kode fra kompendiet, løsningsforslag til oppgave 2 i avsnitt 3.3.3.
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }


    //Oppgave 3a
    //for treg
    public Node<T> finnNode(int indeks){

        //Programkode 3.3.3 a) fra kompendiet
        Node<T> temp = hode;
        if(indeks<antall/2){
           // Node<T> temp = hode;
            if(indeks == 0){
                return temp;
            }
            for(int i = 0; i<indeks;i++){
                temp = temp.neste;
            }
        }

        //søke fra hale og bakover:

        else {
            temp = hale;
            while(indeks<antall-1){
                antall--;
                temp = temp.forrige;
            }
        }
        return temp;


    }

    //fra kompendiet, programkode 3.3.3 b)
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    //noe av koden fra kompendiet, løsningsforslag til oppgave 2 i avsnitt 3.3.3.
    public int indeksTil(T verdi) {

        if(verdi == null){
            return -1;
        }
        if(antall < 1){
            return -1;
        }
        Node current = hode;
        for(int i = 0; i<antall; i++){
            if(current.verdi.equals(verdi)){
                return i;
            }
            current = current.neste;
        }
        return -1;
    }

    //mye av koden er fra kompendiet, Programkode 3.3.3 b)
    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(indeks);
        Objects.requireNonNull(nyverdi);
        indeksKontroll(indeks, false);
        Node<T> temp = finnNode(indeks);
        T gammelVerdi = temp.verdi;
        temp.verdi = nyverdi;
        return gammelVerdi;
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


