## Introduction
The well designed API is well suited to JSON-LD. The ID are already the right URL's; no need to add `@base` everywhere.

There a way to access repository content. There is work done elsewhere on RDF-izing package.json files. This allows to recover dependencies for Javascript projects.
See https://github.com/digitalbazaar/jsonld.js/issues/39

Alas getting repository keywords (called topics) is not done like other data , see https://developer.github.com/v3/search/#search-repositories

**QUESTION**
How could I convert     `"type": "User",`
into     `rdfs:type foaf:Person` ?

## Doc
https://developer.github.com/v3/

## Data examples
https://api.github.com/users/jmvanel
https://api.github.com/users/jmvanel/repos
https://api.github.com/repos/jmvanel/semantic_forms

## Test with SF

http://localhost:9000/json2rdf?src=https://api.github.com/users/jmvanel&context=file:///home/jmv/src/rdf-convert/github.com/context.jsonld
http://localhost:9000/json2rdf?src=https://api.github.com/repos/jmvanel/semantic_forms&context=file:///home/jmv/src/rdf-convert/github.com/context.jsonld

Or with

http://semantic-forms.cc:1952/json2rdf?src=https://api.github.com/users/jmvanel&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/github.com/context.jsonld
http://semantic-forms.cc:1952/json2rdf?src=https://api.github.com/repos/jmvanel/semantic_forms&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/github.com/context.jsonld

