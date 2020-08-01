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
diff -bw observations.jmv.json observation.jmv.json
```

Current issues

- integer value for one id in the original JSON: not accepted by JSLD Playground nor by Titanium
- problem with Titanium with "id" : "54141546"     at root lever (works with JSLD Playground)
