# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Hanne Fjeldaas, S320960, s320960@oslomet.no
* Thuc Van Do, S364535, s364535@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Hanne har gjort oppgave 1-4
* Thuc har gjort oppgave 5-8


# Oppgavebeskrivelse

OBS! Oppgave 3 er fortsatt for treg, men jeg så på det sammen med en TA (Gurjot) og vi fant ingen feil. Så jeg fikk beskjed om å la koden stå og levere som det var. 

Oppgave 1
Konstruktøren: bruker en løkke til å gå igjennom tabellen. Tilordner data 
fra tabellen til en node. Bruker tester til å finne ut hvor noden skal 
plasseres.

antall(), sjekker først om listen er tom. Hvis ikke loop gjennom listen
og øk antall hvor hver gang noden ikke er null. 

tom(), sjekk om hode er null, isåfall er listen tom. Hvis ikke er den ikke
tom. 

Oppgave 2 
toString(), starter listen med første hakeparentes. Om lista er tom, 
legges det kun til en hakeparentes på slutten. Oppretter en hjelpestring
til å få til å få komma på riktig plass. Så lenge det er flere noder igjen, 
printes et komma og noden.

omvendtString(), samme som toString bare baklengs, ved hjelp av forrige-pekere

boolean leggInn(), oppretter en node, sjekker om listen er tom og legger isåfall
den nye noden inn som hode, med nullpeker til forrige og neste. Hvis listen
ikke er tom, legges noden inn på slutten med pekere til tidligere hale.
Tidligere hale peker tilbake på ny hale.

Oppgave 3,

finnNode(). Metoden min er for treg, men har forsøkt både rekursiv og iterativt og
vet ikke helt hvordan jeg skal løse det ellers. 
Hvis indeks er mindre enn antall/2, begynner den å søke fra venstre side. 
Hvis ikke søker den fra halen og bakover med forrige-pekere.

hent() kaller på finnNode og indekskontroll. 

oppdater() finner noden som skal opprettes. Mellomlagrer gammel verdi, setter
inn ny verdi og returnerer gammel verdi. 

subliste(), finner først riktig plass å starte, looper gjennom listen og 
kopierer verdier over i ny liste. Returnerer liste. 
Fylte inn pekere til antall, hode, hale og endringer til dobbeltlenketliste-konstruktøren

Oppgave 4.

indeksTil(), returnerer -1 for null-verdier og for tomme lister.
Looper igjennom for å finne indeksen til verdi. 

boolean inneholder() bruker metoden indeksTil. Returnerer bare om indeks
ikke er -1, som vil si at verdien finnes i listen.

Oppgave 5
Sjekker først hvor node skal også plasserer den i rekkefølgen den skal gjennom If-else

Oppgave 6
Bruker If-else her også. Hvis det kun er 1 verdi i listen setter vi hode og hale lik 0. Ellers går vi gjennom listen.

Oppgave 7
Bruker for en while løkke og en for løkke som løper gjennom og nullstiller verdiene som skal fjernes.

Oppgave 8
Bruker next til å flytte en node framover. Gir feilmeldinger og returnerer iterator.
