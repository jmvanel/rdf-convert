~/src/jsonld-java-tools/jsonldplayground \
  --inputFile ingredients.json \
  --context openfoodfacts.ingredients.context.jsonld \
  --process tordf \
  --format turtle > openfoodfacts.ingredients.jsonld.ttl
rapper --count openfoodfacts.ingredients.jsonld.ttl
