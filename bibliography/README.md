This is an example of processing a human readable text structured by lines and punctuation separators.
The corresponding PDF document is here:
https://independent.academia.edu/BernardLEBRETON

The .docx file has been prepared by docx2txt :
```shell
$ docx2txt 73-Savoie-2020.10.27a.docx 
$ ls -l docx2txt 73-Savoie-2020.10.27a.*
-rw-rw-r-- 1 jmv jmv 88759 nov.   1 18:46 73-Savoie-2020.10.27a.docx
-rw-rw-r-- 1 jmv jmv 53070 nov.   1 18:56 73-Savoie-2020.10.27a.txt
```

Run with:
```shell
scalac *.scala
scala PlainTextBibliography Biospeologia-Bibliographia/73-Savoie-2020.10.27a.articles.txt \
      > 73-Savoie-2020.10.27a.articles.txt.tsv
```

The resulting TSV file is ready to be RDF-ized by semantic-forms .


