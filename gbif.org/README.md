**Links**
- [API documentation on the GBIF website](https://www.gbif.org/developer/summary)
- Darwin Core RDF guide https://dwc.tdwg.org/rdf/
- https://en.wikipedia.org/wiki/Darwin_Core#Key_Projects_Using_Darwin_Core
- Darwin Core as a Vocabulary for Expressing Biodiversity Data as RDF - Baskauf, Steven J. Sachs, Joel https://ir.vanderbilt.edu/handle/1803/9296
- Darwin Core-based terms for expressing biodiversity data as RDF. Semantic WebJournal 7:629-243. http://dx.doi.org/10.3233/SW-150203
- https://www.researchgate.net/publication/309026488_Darwin-SW_Darwin_Core-based_terms_for_expressing_biodiversity_data_as_RDF
- http://bioimages.vanderbilt.edu/pages/standards.htm

**Data example**
```shell
wget -O - https://api.gbif.org/v1/occurrence/1258202889 | jq .
```

**Test with web service**
http://semantic-forms.cc:1952/json2rdf?src=https://api.gbif.org/v1/occurrence/1258202889&context=https://raw.githubusercontent.com/jmvanel/rdf-convert/master/gbif.org/context.jsonld

**See also**
Convert from a Darwin Core Archive into Darwin Core RDF : https://github.com/jmvanel/dwca-rdf

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

