### Tools
Download jsonld-java-tool from :
`git@github.com:jsonld-java/jsonld-java-tools.git`
JSON-LD playground: https://json-ld.org/playground/

### Running JSON-LD parser on "labels" taxonomy

Getting the JSON for the taxonomy
```shell
wget https://static.openfoodfacts.org/data/taxonomies/labels.json
```

```shell
~/src/jsonld-java-tools/jsonldplayground \
  --inputFile $HOME/ontologies/openfoodfacts.labels.pp.json \
  --context openfoodfacts.skos.context.jsonld \
  --process tordf \
  --format turtle > openfoodfacts.labels.jsonld.ttl
```

### Ingredients taxonomy
Here is a test file that you can put in Development JON-LD playgroud (https://json-ld.org/playground-dev/) :

[openfoodfacts.ingredients.test.jsonld](openfoodfacts.ingredients.test.jsonld)

NOTES
- the declaration `"@container": "@index"` is essential to interpret each JSON block as triples
- currently it produces blank nodes, but we want to control URI produced

Here is a message I sent to IRC #json-ld :

```
Wed May 16 01:51:29 2018
<jmvanel> Hi !        "@container": "@id"   does not seem to be implemeted in development  playground , cf 
<jmvanel>      https://json-ld.org/spec/latest/json-ld/#node-identifier-indexing
<jmvanel> Correct ?
<jmvanel> ( processor says :      @context @container value must be one of the following: @list, @set, @index, @language
<jmvanel> Using instead        "@container": "@index"     creates blank nodes .
```

### Running JSON-LD parser on ingredients taxonomy
The Java processor is not yet up-to-date with the latest JSON-LD spec.

```shell
wget https://static.openfoodfacts.org/data/taxonomies/ingredients.json
ingredientsToTTL.sh
```

