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

To use the @context, add this at beginning of ingredients.json:
```json
{
  "@id": "off:root",
  "@type": "off:Ingredients",
  "name": "All OFF ingredients",
  "@context":
    "https://github.com/jmvanel/rdf-convert/raw/master/openfoodfacts.org/openfoodfacts.ingredients.context.jsonld",

  "off-ingredients":
```
and a closing `}` at end of file of course.

NOTES
- this solution implies changing the nesting of the original ingredients.json by adding one level of nesting and a few lines at beginning
- the declaration `"@container": "@id"` is essential to interpret each JSON block as triples, see https://w3c.github.io/json-ld-syntax/#node-identifier-indexing
- to control URI produced, change the @base key

### Running JSON-LD parser on ingredients taxonomy
The Java processor is not yet up-to-date with the latest JSON-LD spec.

```shell
wget https://static.openfoodfacts.org/data/taxonomies/ingredients.json
ingredientsToTTL.sh
```

