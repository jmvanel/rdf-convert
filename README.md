# Converting anything to RDF

See [Semantization: different methods](https://github.com/jmvanel/semantic_forms/wiki/Semantization#different-methods)

## JSON-LD context for GoGoCarto

JSON-LD for GoGoCarto JSON API, see https://pixelhumain.github.io/GoGoCartoJs/docs/dataset-element-configuration.html

We are waiting for implementation of feature nested-properties in JSON-LD 1.1 :
https://json-ld.org/spec/latest/json-ld/#nested-properties

### Getting the HTML page

https://presdecheznous.fr/annuaire#/fiche/Solaris/Clz

### Getting the JSON from API

```shell
wget --header='X-Requested-With: XMLHttpRequest' \
  https://presdecheznous.fr/api/elements/Clz.jsonld
```

or

```shell
wget --header='X-Requested-With: XMLHttpRequest' \
  --header='Accept: application/ld+json' \
  https://presdecheznous.fr/api/elements/Clz
```

Add `--save-headers` to save HTTP headers.


### Running JSON-LD parser on example

```shell
~/src/jsonld-java-tools/jsonldplayground \
  --inputFile gogo.jsonld \
  --process tordf \
  --format turtle >  > gogo.jsonld.ttl
```
