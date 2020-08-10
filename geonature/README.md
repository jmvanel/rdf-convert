## Statut
Travail en cours, besoin de concertations avec les gestionnaires de GeoNature.
cf https://github.com/PnX-SI/GeoNature/issues/996

## Données de test
Récupérer données JSON brutes:
```shell
curl 'http://demo.geonature.fr/geonature/api/occtax/releve/164'   -H 'Connection: keep-alive'   -H 'Pragma: no-cache' \
     -H 'Cache-Control: no-cache' -H 'Accept: application/json, text/plain, */*' \
     -H 'User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/83.0.4103.61 Chrome/83.0.4103.61 Safari/537.36' \
     -H 'Referer: http://demo.geonature.fr/geonature/' \
     -H 'Accept-Language: fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7,de;q=0.6,es-ES;q=0.5,es;q=0.4' \
     -H 'Cookie: token=eyJhbGciOiJIUzI1NiIsImlhdCI6MTU5NjcwMjM4MywiZXhwIjoxNTk3MzA3MTgzfQ.eyJpZF9yb2xlIjoxLCJub21fcm9sZSI6IkFkbWluaXN0cmF0ZXVyIiwicHJlbm9tX3JvbGUiOiJ0ZXN0IiwiaWRfYXBwbGljYXRpb24iOjMsImlkX29yZ2FuaXNtZSI6LTEsImlkZW50aWZpYW50IjoiYWRtaW4iLCJpZF9kcm9pdF9tYXgiOjF9.4mbwrP9iQ8gTHewWLvJMhl1NiFHnP2C_UQgiMBrni0o; session=.eJyrVvJ3dg5xjFCyqlYqLU4tik8uKi1LTQFxnZWslIyVdJRcoLQrlA6C0qFQOgxM19bWAgAPNhKx.Eg1atA.kvCblUrgeP-yZJ8RyZaS5eNf_PY'   --compressed   --insecure | jq . > occtax.json
```
Ensuite occtax.json a été augmenté avec quelques @id et des @type , dans le fichier [occtax.jsonld](occtax.jsonld) .

## Tester le JSON-LD
### Tester avec Titanium (Java)
```scala
val = geonatdir = file:///home/jmv/src/rdf-convert/geonature/"
try { jsonLDtoRDF3( geonatdir + "occtax.jsonld" , geonatdir + "context.jsonld")  } catch { case e: JsonLdError => e.getCode }
```

### Tester avec JSON-LD Playground
Dans https://json-ld.org/playground/
coller occtax.jsonld , puis le bloc "@context" à la racine depuis [context.jsonld](context.jsonld)

Résultat:

Les triplets obtenus par le JSON-LD Playground ont été mis en forme par l'outil historique de Tim B.L. , [CWM](https://www.w3.org/2000/10/swap/doc/cwm.html)

[occtax.cmw.ttl](occtax.cmw.ttl)

C'est la première étape : se mettre d'accord sur les @id et les @type .
