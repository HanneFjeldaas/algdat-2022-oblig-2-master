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
    private Node hale;          // peker til den siste i listen
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

    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0) {
            throw new IndexOutOfBoundsException("fra verdien: " + fra + " er negativ!");
        }
        if (til > antall) {
            throw new IndexOutOfBoundsException("til verdien: " + til + " er utenfor listen!");
        }
        if (fra > til) {
            throw new IllegalArgumentException("fra verdien: " + fra + " er større enn til verdien: " + til + " = Ugyldig! Try again!");
        }
    }


    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);

        //ny liste til å sette kopi-nodene inn i:
        Liste<T> nyListe = new DobbeltLenketListe<>();

        //finner riktig plass å starte
        Node<T> current = finnNode(fra);

        //legger til verdier i listen
        for (int i = fra; i < til; i++) {
            nyListe.leggInn(current.verdi);
            current = current.neste;
    }
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
        return hode == null;

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
            Node node = hode;
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
        Objects.requireNonNull(verdi, "Ikke Gyldig Verdi!");

        //Node blir plassert i tom liste
        if (antall() == 0 && indeks == 0){
            Node<T> n = new Node<>(verdi, hode, hale);
            hode = hale = n;
        }
        //Node blir plassert fremst i liste
        else if (indeks == 0){
            Node<T> n = new Node<>(verdi, null, hode);
            hode.forrige = n;
            hode = n;
        }
        //Node blir plassert bakerst i liste
        else if (indeks == antall()){
            Node<T> n = new Node<>(verdi, hale, null);
            hale.neste = n;
            hale = n;
        }
        //Node blir plassert mellom to eksisterende i liste
        else {
            Node<T> flytter = finnNode(indeks);
            Node<T> fremst = flytter.forrige;
            Node<T> n = new Node<>(verdi, fremst, flytter);
            flytter.forrige = n;
            fremst.neste = n;
        }
        antall++;
        endringer++;
    }


    //kode fra kompendiet, løsningsforslag til oppgave 2 i avsnitt 3.3.3.
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }


    //Oppgave 3a
    // hjelpemetode fra kompendiet
    private Node<T> finnNode(int indeks)
    {
        Node<T> p;

        if (indeks <= antall / 2)
        {
            p = hode;
            for (int i = 0; i < indeks; i++)
            {
                p = p.neste;
            }
        }
        else
        {
            p = hale;
            for (int i = antall - 1; i > indeks; i--)
            {
                p = p.forrige;
            }
        }
        return p;
    }

    //fra kompendiet, programkode 3.3.3 b)
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }


    //noe av koden fra kompendiet, løsningsforslag til oppgave 2 i avsnitt 3.3.3.
    public int indeksTil(T verdi) {

        //returner -1 om man leter etter null-verdier
        if(verdi == null){
            return -1;
        }
        //returnerer -1 om listen er tom
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
        //ingen nullverdier tillatt:
        Objects.requireNonNull(nyverdi);

        indeksKontroll(indeks, false);
        //finner noden vi vil oppdatere
        Node<T> temp = finnNode(indeks);
        //trekker ut gammelverdi fra noden:
        T gammelVerdi = temp.verdi;
        //opddaterer noden til nyverdi:
        temp.verdi = nyverdi;
        endringer++;
        return gammelVerdi;
    }



    @Override
    public boolean fjern(T verdi) {
        if (antall()==0){
            return false;
        }
        if (verdi == null){return false;}

        Node<T> n = hode;
        boolean funnet = false;

        for (int i = 0; i < antall(); i++) {
            if (n.verdi.equals(verdi)){
                funnet = true;
                break;
            }
            n = n.neste;
        }

        if (funnet) {
            if (antall() == 1) {
                hode = hale = null;
            }

            else if (n.equals(hode)) {
                hode.neste.forrige = null;
                hode = hode.neste;
            }

            else if (n.equals(hale)) {
                hale.forrige.neste = null;
                hale = hale.forrige;
            }

            else {
                Node<T> foran = n.forrige;
                Node<T> bak = n.neste;
                foran.neste = bak;
                bak.forrige = foran;
            }
            endringer++;
            antall--;
            return true;

        } else {
            return false;
        }
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);
        if (antall() == 0){return null;}

        Node<T> fjernes = finnNode(indeks);
        T ut = fjernes.verdi;
        if (antall()==1){
            hode = hale = null;

        } else if (indeks == antall()-1){
            hale.forrige.neste = null;
            hale = hale.forrige;}

        else if (indeks == 0) {
            hode.neste.forrige = null;
            hode = hode.neste;

        } else {
            Node<T> foran = fjernes.forrige;
            Node<T> bak = fjernes.neste;
            foran.neste = bak;
            bak.forrige = foran;
        }
        antall--;
        endringer++;

        return ut;

    }

    @Override
    public void nullstill() {
        Node<T> forste = hode;
        Node<T> siste;
        while(forste != null){
            siste = forste.neste;
            forste.neste = null;
            forste.verdi = null;
            forste = siste;
        }
        antall = 0;
        hode = hale = null;
        endringer ++;

        /*
        Metode 2, kjører saktere enn første metode og beholder den derfor ikke
        for (Node<T> a = hode; a != null; a = a.neste) {
            fjern(0);
        }
         */


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
            ut.append(fyll).append(node.verdi);
            node = node.neste;
            fyll = ", ";
        }
        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        StringBuilder ut = new StringBuilder();
        ut.append("[");
        String fyll = "";
        if(hale == null){
            ut.append("]");
            return ut.toString();
        }
        while (hale != null){
            ut.append(fyll).append(hale.verdi);
            hale = hale.forrige;
            fyll = ", ";
        }
        ut.append("]");
        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (endringer != iteratorendringer){
                throw new ConcurrentModificationException("Endringer er gjort");
            }
            if(!hasNext()){
                throw new NoSuchElementException("Listen er tom");
            }
            fjernOK = true;
            T nyTemp = denne.verdi;
            denne = denne.neste;
            return nyTemp;

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


