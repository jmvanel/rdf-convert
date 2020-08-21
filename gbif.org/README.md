**Links**
https://www.gbif.org/developer/summary
Darwin Core RDF guide
https://dwc.tdwg.org/rdf/
https://en.wikipedia.org/wiki/Darwin_Core#Key_Projects_Using_Darwin_Core
Darwin Core as a Vocabulary for Expressing Biodiversity Data as RDF
Baskauf, Steven J. Sachs, Joel
https://ir.vanderbilt.edu/handle/1803/9296

**Data example**
```shell
wget -O - https://api.gbif.org/v1/occurrence/1258202889 | jq .
```

**Test with web service**
http://semantic-forms.cc:1952/json2rdf?src=https://api.gbif.org/v1/occurrence/1258202889&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/gbif.org/context.jsonld

**Test locally in Scala REPL in titanium-json-ld directory**
```
import com.apicatalog.jsonld._
import com.apicatalog.jsonld.api._
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
val gbifdata = "https://api.gbif.org/v1/occurrence/1258202889"
val gbifdir = "file:///home/jmv/src/rdf-convert/gbif.org/"
try { jsonLDtoRDF3( gbifdata, gbifdir + "context.jsonld")  } catch { case e: JsonLdError => e.getCode }
```

