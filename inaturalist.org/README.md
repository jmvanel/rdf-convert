Work in progress

Tested with Titanium and Scala REPL:
```
import  com.apicatalog.jsonld._
def jsonLDtoRDF(uri: String) = {
  import scala.collection.JavaConverters._
  val tr = JsonLd.toRdf( uri ).get.toList.asScala
  for ( e <- tr )
    println ( e.getSubject + " <" + e.getPredicate + "> " + e.getObject )
}
try { jsonLDtoRDF( inatdir + "observation.jmv.json" , inatdir + "inat.context.jsonld") } catch { case e: JsonLdError => e.getCode }
```

observation.jmv.json comes from JSON API:

```
wget -O observation.jmv.json https://api.inaturalist.org/v1/observations/5414154
```

Current issues

- integer value for one id in the original JSON: not accepted by JSLD Playground nor by Titanium
- problem with Titanium with "id" : "54141546"     at root level (works with JS-LD Playground)

