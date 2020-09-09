# Converting anything to RDF

See [Semantization: different methods](https://github.com/jmvanel/semantic_forms/wiki/Semantization#different-methods)

Most conversions in this project involve writing a specific JSON-LD `@context` .
I made a generic JSON-LD web service based on Titamium Java processor:
`http://semantic-forms.cc:1952/json2rdf?src=<JSON URL>&context=<context URL>`


Example:
http://semantic-forms.cc:1952/json2rdf?src=https://api.github.com/users/jmvanel&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/github.com/context.jsonld

