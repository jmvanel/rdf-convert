Work in progress

# Goal and context
inaturalist.org is a collaborative web site for nature observations.

inaturalist.org API
https://api.inaturalist.org/v1/
source code is here :
https://github.com/inaturalist/iNaturalistAPI

Through semantic web technology (SPARQL queries), the goal is to reuse data from other open sources, especially to
- make custom maps from several sources
- leverage on semantic web tools (SPARQL) and propose other UI's than the ones provided originally
  - find other naturalists
  - find observations based on taxonomy, etc

# Test with with generic JSON-LD web service
Running latest Titanium Json-Ld processor.

http://semantic-forms.cc:1952/json2rdf?src=https://api.inaturalist.org/v1/observations/5414154&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/inaturalist.org/inat.context.jsonld

# Test with Titanium through Scala in REPL

```shell
cat build.sbt
libraryDependencies += "org.glassfish" % "jakarta.json" % "1.1.6"
```

In Scala REPL
```
import  com.apicatalog.jsonld._
def jsonLDtoRDF3(uri: String, context: String) = {
  import scala.collection.JavaConverters._
  val options = new JsonLdOptions()
  options.setExpandContext(context)
  val tr = JsonLd.toRdf( uri ).
    options(options).
    get.toList.asScala
  for ( e <- tr )
    println ( e.getSubject + " <" + e.getPredicate + "> " + e.getObject )
}
def jsonLDtoRDF(uri: String) = {
  import scala.collection.JavaConverters._
  val tr = JsonLd.toRdf( uri ).get.toList.asScala
  for ( e <- tr )
    println ( e.getSubject + " <" + e.getPredicate + "> " + e.getObject )
}

try { jsonLDtoRDF3( inatdir + "observation.jmv.json" , inatdir + "inat.context.jsonld") } catch { case e: JsonLdError => e.getCode }

try { jsonLDtoRDF( inatdir + "observation.jmv+context.json" ) } catch { case e: JsonLdError => e.getCode }
```

# The data
`observation.jmv.json` comes from JSON API:

```
wget -O observation.jmv.json https://api.inaturalist.org/v1/observations/5414154
```

# Current issues

- integer value for several id in the original JSON: not accepted by JSLD Playground nor by Titanium

An alternative is adding new JSON-LD `@id` keys in the current JSON, like this in /observations instance :

  `"@id" : "5414154" ,`

Also it will help to add this new JSON-LD `@id` key in /observations instances :
  `"@type": [ "nature:Observation", "dwc:HumanOrservation" , "dwc:Occurrence" ] ,

And this new JSON-LD `@id` key in /users instances :

  `"@type": [ "foaf:Person", "schema:Person" ] ,

Work n Progress ...


