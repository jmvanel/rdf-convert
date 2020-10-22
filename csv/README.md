This code was used to create several CVS rows from a single one.
It leverages on Apache Commons CSV library.
The input file is a herbarium file with several specimens per row, up to 4 :
```csv
occurrenceId,class,order,family,scientificName,infraspecificEpithet,variety,vernacularName,eventDate,verbatimLocality,eventDate2,verbatimLocality2,eventDate3,verbatimLocality3,eventDate4,verbatimLocality4
Numéro de planche,Embranchement,Ordre,Famille,Espèce,Sous-espèce,Variété,Noms vernaculaires,Date 1,Lieu 1,Date 2,Lieu 2,Date 3,Lieu 3,Date 4,Lieu 4
"0,0111",CRYPTOGAMES NON VASCULAIRES / MARCHANTIOPHYTA,MARCHANTIALES,MARCHANTIACEES,Marchantia polymorpha L.,,,Marchantie polymorphe.,2012-06-19,HTES-ALPES (05): > Briançon / bois des AYES (1800m),2003-07-06,ITALIE (It.): Dolomites / sentier Mt.Talm (1400m),,,,
"0,0112",CRYPTOGAMES NON VASCULAIRES / MARCHANTIOPHYTA,MARCHANTIALES,AYTONIACEES,Reboulia hemisphaerica(L.)Raddi,,,Marchantie hémisphérique,2010-04-29,MAROC(Ma): Moyen Atlas / env. de Zaouia-Ifrane (1215m),,,,,,
```

The header names follow Darwin Core recommendations.
In output line 3 is split in 2:
```csv
"0,0111-1",CRYPTOGAMES NON VASCULAIRES / MARCHANTIOPHYTA,MARCHANTIALES,MARCHANTIACEES,Marchantia polymorpha L.,,,Marchantie polymorphe.,2012-06-19,HTES-ALPES (05): > Briançon / bois des AYES (1800m),,,,,,
"0,0111-2",CRYPTOGAMES NON VASCULAIRES / MARCHANTIOPHYTA,MARCHANTIALES,MARCHANTIACEES,Marchantia polymorpha L.,,,Marchantie polymorphe.,2003-07-06,ITALIE (It.): Dolomites / sentier Mt.Talm (1400m),,,,,,
```
