# Converting anything to RDF

See [Semantization: different methods](https://github.com/jmvanel/semantic_forms/wiki/Semantization#different-methods)

## JSON-LD context for GoGoCarto

JSON-LD for GoGoCarto JSON API, see https://pixelhumain.github.io/GoGoCartoJs/docs/dataset-element-configuration.html

We are waiting for implementation of feature nested-properties in JSON-LD 1.1 :
https://json-ld.org/spec/latest/json-ld/#nested-properties

### Running example

~/src/jsonld-java-tools/jsonldplayground --inputFile gogo.jsonld --process tordf --format turtle
