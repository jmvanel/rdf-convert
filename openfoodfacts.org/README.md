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

